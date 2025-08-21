package org.wujunyang.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.wujunyang.pojo.EmpExpr;

import java.util.List;

/**
 * 员工工作经历
 */
@Mapper
public interface EmpExprMapper {



    void insertBatch(List<EmpExpr> empExprs);

    void deleteByEmpId(@Param("empIds") List<Integer> empIds);
}
