package org.wujunyang.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.wujunyang.pojo.Emp;
import org.wujunyang.pojo.PageResult;
import org.wujunyang.pojo.Result;
import org.wujunyang.service.EmpService;

import java.util.List;
import java.util.Arrays;

@Slf4j
@RestController
public class EmpController {

    @Autowired
    private EmpService empService;
    @GetMapping("/emps")
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                      @RequestParam(defaultValue = "10") Integer pageSize,
                      @RequestParam(required = false) String name,
                      @RequestParam(required = false) Integer gender,
                      @RequestParam(required = false) String begin,
                      @RequestParam(required = false) String end) {
        log.info("分页查询: 第{}页，每页{}条，姓名：{}，性别：{}，开始日期：{}，结束日期：{}", 
                page, pageSize, name, gender, begin, end);
        PageResult<Emp> pageResult = empService.page(page, pageSize, name, gender, begin, end);
        return Result.success(pageResult);
    }

    @GetMapping("/emps/{id}")
    public Result get(@PathVariable Integer id) {
        log.info("查询员工: {}", id);
        Emp emp = empService.get(id);
        return Result.success(emp);
    }

// 新增员工
    @PostMapping("/emps")
    public Result insert(@RequestBody Emp  emp) {
        log.info("新增员工: {}", emp);
        empService.insert(emp);
        return Result.success();
    }
// 删除员工（批量删除和单个删除）
    @DeleteMapping("/emps")
    public Result delete(@RequestParam(value = "ids", required = false) String ids) {
        log.info("删除员工: {}", ids);
        if (ids == null || ids.isEmpty()) {
            return Result.error("请提供要删除的员工ID");
        }
        
        // 处理单个ID或批量ID
        List<Integer> idList;
        if (ids.contains(",")) {
            // 批量删除
            idList = Arrays.stream(ids.split(","))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .collect(java.util.stream.Collectors.toList());
        } else {
            // 单个删除
            idList = Arrays.asList(Integer.parseInt(ids));
        }
        
        empService.delete(idList);
        return Result.success();
    }
// 更新员工
    @PutMapping("/emps")
    public Result update(@RequestBody Emp emp) {
        log.info("更新员工: {}", emp);
        empService.update(emp);
        return Result.success();
    }

    @GetMapping("/emps/gender")
    public Result countByGender() {
        log.info("统计员工性别分布");
        return Result.success(empService.countByGender());
    }


}


