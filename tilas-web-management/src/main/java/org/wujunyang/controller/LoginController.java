package org.wujunyang.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.wujunyang.pojo.Emp;
import org.wujunyang.pojo.LoginInfo;
import org.wujunyang.pojo.Result;
import org.wujunyang.service.EmpService;

import java.util.Locale;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    EmpService empService;
    @PostMapping("/login")
    public Result login(@RequestBody Emp emp, Locale locale){
        log.info("员工登录: {}", emp);
        LoginInfo loginInf = empService.login(emp);
        if(loginInf!= null){
            return Result.success(loginInf);
        }
        return Result.error("用户名或密码错误");


    }

}
