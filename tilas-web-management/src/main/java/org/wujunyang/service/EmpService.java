package org.wujunyang.service;

import org.springframework.stereotype.Service;
import org.wujunyang.pojo.Emp;
import org.wujunyang.pojo.LoginInfo;
import org.wujunyang.pojo.PageResult;

import java.util.List;
import java.util.Map;

@Service
public interface EmpService {

    PageResult<Emp> page(Integer page, Integer pageSize, String name, Integer gender, String begin, String end);

    void insert(Emp emp);

    void delete(List<Integer> id);

    void update(Emp emp);


    Emp get(Integer id);

    List<Map<String,Integer>> countByGender();

    LoginInfo login(Emp emp);

}
