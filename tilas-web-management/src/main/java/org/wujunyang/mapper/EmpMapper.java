package org.wujunyang.mapper;

import org.apache.ibatis.annotations.*;
import org.wujunyang.pojo.Emp;

import java.util.List;
import java.util.Map;

/**
 * 员工信息
 */
@Mapper
public interface EmpMapper {
    @MapKey("gender")
   List<Map<String, Integer>> countByGender();



    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into emp(username, password, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time) " +
            "values" +
            "(#{username}, #{password}, #{name}, #{gender}, #{phone}, #{job}, #{salary}, #{image}, #{entryDate}, #{deptId}, #{createTime}, #{updateTime})")
    void insert(Emp emp);


    @Select("select count(*) from emp e left join dept d on e.dept_id = d.id")
    public long count();

    /**
     * 查询员工列表
     * 注意：使用PageHelper时，不需要在SQL中添加LIMIT语句，PageHelper会自动处理
     */
    List<Emp> list(@Param("name") String name, @Param("gender") Integer gender, 
                   @Param("begin") String begin, @Param("end") String end);

    void delete(@Param("ids") List<Integer> ids);

    void updateById(Emp emp);

    Emp get(Integer id);

    Emp selectByUsernameAndPassword(Emp emp);
    
    /**
     * 根据用户名查询员工
     */
    @Select("select id from emp where username = #{username}")
    Emp selectByUsername(String username);
    
    /**
     * 根据手机号查询员工
     */
    @Select("select id from emp where phone = #{phone}")
    Emp selectByPhone(String phone);
}
