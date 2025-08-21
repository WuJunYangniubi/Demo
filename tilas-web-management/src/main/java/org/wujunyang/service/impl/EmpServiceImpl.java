package org.wujunyang.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.wujunyang.Utils.JwtUtils;
import org.wujunyang.Utils.ThreadLocalUtil;
import org.wujunyang.mapper.EmpExprMapper;
import org.wujunyang.mapper.EmpMapper;
import org.wujunyang.pojo.Emp;
import org.wujunyang.pojo.LoginInfo;
import org.wujunyang.pojo.PageResult;
import org.wujunyang.service.EmpService;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Slf4j
@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;
    
    @Autowired
    private EmpExprMapper empExprMapper;
    @Override
    public PageResult<Emp> page(Integer page, Integer pageSize, String name, Integer gender, String begin, String end) {
        // 设置默认值，避免空指针异常
        int pageNum = page == null ? 1 : page;
        int size = pageSize == null ? 10 : pageSize;

        // 使用PageHelper进行分页查询
        Page<Emp> pageResult = PageHelper.startPage(pageNum, size);
        // 执行查询 - PageHelper会自动对下面的查询进行分页
        List<Emp> empList = empMapper.list(name, gender, begin, end);
        // 封装结果
        return new PageResult<>(pageResult.getTotal(), empList);
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void insert(Emp emp) {
        // 业务逻辑验证
        if (emp.getUsername() != null && !emp.getUsername().trim().isEmpty()) {
            Emp existingEmp = empMapper.selectByUsername(emp.getUsername());
            if (existingEmp != null) {
                throw new RuntimeException("用户名已存在，请使用其他用户名");
            }
        }
        
        if (emp.getPhone() != null && !emp.getPhone().trim().isEmpty()) {
            Emp existingEmp = empMapper.selectByPhone(emp.getPhone());
            if (existingEmp != null) {
                throw new RuntimeException("手机号已存在，请使用其他手机号");
            }
        }
        
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);

        // 检查exprList是否为null，避免空指针异常
        if (emp.getExprList() != null && !emp.getExprList().isEmpty()) {
            emp.getExprList().forEach(empExpr -> {
                empExpr.setEmpId(emp.getId());
            });
            // 注入EmpExprMapper
            empExprMapper.insertBatch(emp.getExprList());
        }
    }
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void delete(List<Integer> ids) {
        empMapper.delete(ids);
        empExprMapper.deleteByEmpId(ids);
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);
        empExprMapper.deleteByEmpId(Arrays.asList(emp.getId()));
        if(!CollectionUtils.isEmpty(emp.getExprList())){

            emp.getExprList().forEach(empExpr -> {
                empExpr.setEmpId(emp.getId());
            });
            empExprMapper.insertBatch(emp.getExprList());
        }

    }

    @Override
    public Emp get(Integer id) {
        return empMapper.get(id);
    }

    @Override
    public List<Map<String, Integer>> countByGender() {
        return empMapper.countByGender();
    }

    @Override
    public LoginInfo login(Emp emp) {
         Emp e =   empMapper.selectByUsernameAndPassword(emp);
        if(e!= null){
            log.info("员工登录成功: {}", e);
            Map<String,Object> Claims = new HashMap<>();
            Claims.put("id",e.getId());
            Claims.put("username",e.getUsername());
            String jwt = JwtUtils.generateJwt(Claims);
            log.info("登录ID",ThreadLocalUtil.getUserId());
            return new LoginInfo(e.getId(),e.getUsername(),e.getName(),jwt);
        }
        return null;
    }

}
