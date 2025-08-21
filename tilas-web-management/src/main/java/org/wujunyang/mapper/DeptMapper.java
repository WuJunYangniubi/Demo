package org.wujunyang.mapper;

import org.apache.ibatis.annotations.*;
import org.wujunyang.pojo.Dept;

import java.util.List;

@Mapper
public interface DeptMapper {
    /**
     * 查询所有部门数据
     */
    //方式一: 手动结果映射
    //    @Results({
    //            @Result(column = "create_time", property = "createTime"),
    //            @Result(column = "update_time", property = "updateTime")
    //    })

    //方式二: 起别名
    //@Select("select id, name, create_time createTime, update_time updateTime from dept order by update_time desc")
    @Select("select id, name, update_time updateTime from dept order by update_time desc")
    List<Dept> findAll();

    @Delete("delete from dept where id = #{id}")
    void delete(Integer id);

    @Insert("insert into dept(name, create_time, update_time) values(#{name}, #{createTime}, #{updateTime})")
    void add(Dept dept);

    @Select("select id, name, create_time, update_time from dept where id = #{id}")
    Dept get(Integer id);

    @Update("update dept set name = #{name}, update_time = #{updateTime} where id = #{id}")
    void update(Dept dept);

}
