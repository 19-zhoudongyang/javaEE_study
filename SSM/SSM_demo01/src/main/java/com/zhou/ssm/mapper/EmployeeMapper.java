package com.zhou.ssm.mapper;

import com.github.pagehelper.PageInfo;
import com.zhou.ssm.pojo.Employee;

import java.util.List;

public interface EmployeeMapper {
    /*
    查询所有的员工信息
     */
    List<Employee> getAllEmployee();

}
