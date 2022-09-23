package com.zhou.mybatis.mappers;

import com.zhou.mybatis.pojo.Emp;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface EmpMapper {
    /*
    查询所有员工信息
     */
    List<Emp> getAllEmp();

    /*
    查询员工以及员工对应的部门信息
     */
    Emp getEmpAndDept(@Param("eid") Integer eid);

    /*
    通过分步查询员工以及员工所对应的部门信息
     */
    //分布查询第一步：查询员工信息
    Emp getEmpAndDeptByStepOne(@Param("eid") Integer eid);

}
