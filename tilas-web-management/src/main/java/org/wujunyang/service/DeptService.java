package org.wujunyang.service;

import org.wujunyang.pojo.Dept;

import java.util.List;

public interface DeptService {
    /**
     * 查询所有部门
     */
    List<Dept> findAll();

    /**
     * 根据ID删除部门
     */

    void delete(Integer id);


    void add(Dept dept);

    Dept get(Integer id);

    void update(Dept dept);
}
