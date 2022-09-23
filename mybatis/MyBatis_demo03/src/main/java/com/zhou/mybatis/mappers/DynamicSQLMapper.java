package com.zhou.mybatis.mappers;

import com.zhou.mybatis.pojo.Emp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DynamicSQLMapper {

    /*
    查询员工信息
     */
    List<Emp> getEmpByCondition(Emp emp);

    /*
    测试choose、when、otherwise
     */
    List<Emp> getEmpByChoose(Emp emp);

    /*
    通过数组实现批量删除
     */
    int deleteMoreByArray(@Param("eids") Integer[] eids);

    /*
    通过list集合实现批量添加
     */
    int insertMoreByList(@Param("emps") List<Emp> emps);
}
