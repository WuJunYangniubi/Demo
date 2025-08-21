package org.wujunyang.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.wujunyang.pojo.Dept;
import org.wujunyang.pojo.Result;
import org.wujunyang.service.DeptService;

import java.util.List;

@Slf4j
//@RequestMapping("/depts")
@RestController
public class DeptController {

    //private static final Logger log = LoggerFactory.getLogger(DeptController.class); //固定的

    @Autowired
    private DeptService deptService;

    /**
     * 查询部门列表
     */
    //@RequestMapping(value = "/depts", method = RequestMethod.GET) //method: 指定请求方式
    @GetMapping("/depts")
    public Result list() {
        //System.out.println("查询全部部门数据");
        log.info("查询全部部门数据");
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
    }
    /**
     * 删除部门
     */
    @DeleteMapping("/depts")
    public Result delete(@RequestParam("id") Integer id){

        log.info("删除部门: {}", id);
        deptService.delete(id);
        return Result.success();
    }
    /**
     * 新增部门
     */
    @PostMapping("/depts")
    public Result add(@RequestBody Dept dept){
        log.info("新增部门: {}", dept);

        deptService.add(dept);
        return Result.success();
    }

    /**
     * 查询部门
     */
    @GetMapping("/depts/{id}")
    public Result get(@PathVariable("id") Integer id){
        log.info("查询部门: {}", id);
        Dept dept = deptService.get(id);
        return Result.success(dept);
    }

    /**
     * 修改部门
     */

    @PutMapping("/depts")
    public Result update(@RequestBody Dept dept){
        log.info("修改部门: {}", dept);
        deptService.update(dept);
        return Result.success();
    }

}