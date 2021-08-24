package com.sree.employee.service;

import com.sree.employee.vo.EmployeeVO;

import java.util.List;

public interface EmployeeService {

    public EmployeeVO createEmployee(EmployeeVO empVO);

    public EmployeeVO getEmployee(Integer empId);

    public void deleteEmployee(Integer empId);

    public EmployeeVO updateEmployee(Integer empId , EmployeeVO updateEmpVO);

    public List<EmployeeVO> getAllEmployees();
}