package com.zhou.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.zhou.ssm.pojo.Employee;
import com.zhou.ssm.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Date:2022/9/14
 * Author:zhou
 * Description:
 * 查询所有的员工信息--->/employee-->get
 * 查询员工的分页信息--->/employee/page/1-->get
 * 根据id查询员工信息--->/employee/1-->get
 * 跳转到添加页面--->/to/add-->get
 * 添加员工信息--->/employee-->post
 * 修改员工信息--->/employee-->put
 * 删除员工信息--->/employee-->delete
 */
@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/employees",method = RequestMethod.GET)
    public String getAllEmployee(Model model){
        //查询所有的员工信息
        List<Employee> employees = employeeService.getAllEmployee();
        //将员工信息在请求域中共享
        model.addAttribute("employees",employees);
        //跳转到employees.html
        return "employees";
    }

    @RequestMapping(value = "/employees/page/{pageNum}",method = RequestMethod.GET)
    public String getEmployeePage(@PathVariable("pageNum") Integer pageNum,Model model){
        PageInfo<Employee> page = employeeService.getEmployeePage(pageNum);
        //将分页数据共享到请求域中
        model.addAttribute("page",page);
        return "employees";
    }

}
