package com.sree.employee.service;

import com.sree.employee.entity.Employee;
import com.sree.employee.exception.EmployeeAlreadyExistsException;
import com.sree.employee.exception.EmployeeNotFoundException;
import com.sree.employee.mapper.EmployeeMapper;
import com.sree.employee.repository.EmployeeRepository;
import com.sree.employee.vo.EmployeeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

  @Autowired private EmployeeRepository employeeRepository;

  @Autowired private EmployeeMapper employeeMapper;

  @Override
  public EmployeeVO createEmployee(EmployeeVO empVO) {
    Optional<Employee> emp = employeeRepository.findByEmail(empVO.getEmail());
    if (emp.isPresent() && emp.get().getEmail().equals(empVO.getEmail())) {
      throw new EmployeeAlreadyExistsException(empVO.getEmail());
    }
    return employeeMapper.toEmployeeVO(employeeRepository.save(employeeMapper.toEmployee(empVO)));
  }

  @Override
  public EmployeeVO getEmployee(Integer empId) {
    Optional<Employee> emp = employeeRepository.findById(empId);
    if (emp.isPresent()) {
      return employeeMapper.toEmployeeVO(emp.get());
    } else {
      throw new EmployeeNotFoundException(empId);
    }
  }

  @Override
  public void deleteEmployee(Integer empId) {
    Optional<Employee> emp = employeeRepository.findById(empId);
    if (emp.isPresent()) {
      employeeRepository.deleteById(empId);
    } else {
      throw new EmployeeNotFoundException(empId);
    }
  }

  @Override
  public EmployeeVO updateEmployee(Integer empId, EmployeeVO updateEmpVO) {
    Optional<Employee> emp = employeeRepository.findById(empId);
    Employee employee = null;
    if (emp.isPresent()) {
      employee = emp.get();
      employeeRepository.save(employeeMapper.updateEmployee(updateEmpVO, employee));
    } else {
      throw new EmployeeNotFoundException(empId);
    }
    return employeeMapper.toEmployeeVO(employee);
  }

  @Override
  public List<EmployeeVO> getAllEmployees() {
    List<EmployeeVO> voList = new ArrayList<>();
    List<Employee> empList = employeeRepository.findAll();
    if (empList.isEmpty()) {
      return voList;
    } else {
      for (Employee emp : empList) {
        voList.add(employeeMapper.toEmployeeVO(emp));
      }
    }
    return voList;
  }
    }
