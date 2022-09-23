package com.zhou.ssm.service;

import com.github.pagehelper.PageInfo;
import com.zhou.ssm.pojo.Employee;

import java.util.List;

public interface EmployeeService {

    /*
    获取所有员工信息
     */
    public List<Employee> getAllEmployee();


    /*
    获取员工的分页信息
     */
    PageInfo<Employee> getEmployeePage(Integer pageNum);
}
