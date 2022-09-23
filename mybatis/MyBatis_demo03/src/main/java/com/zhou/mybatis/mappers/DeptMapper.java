package com.zhou.mybatis.mappers;

import com.zhou.mybatis.pojo.Dept;
import org.apache.ibatis.annotations.Param;

public interface DeptMapper {
    //分布查询第二步：通过did查询员工的部门信息
    Dept getEmpAndDeptByStepTwo(@Param("did") Integer did);

    /*
    获取部门以及部门中所有的员工信息
     */
    Dept getDeptAndEmp(@Param("did") Integer did);
}
