package com.sree.employee.mapper;

import com.sree.employee.entity.Employee;
import com.sree.employee.vo.EmployeeVO;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

  public Employee toEmployee(EmployeeVO empVO) {
    ModelMapper modelMapper = new ModelMapper();
    return modelMapper.map(empVO, Employee.class);
  }

  public EmployeeVO toEmployeeVO(Employee emp) {
    ModelMapper modelMapper = new ModelMapper();
    return modelMapper.map(emp, EmployeeVO.class);
  }

  public Employee updateEmployee(EmployeeVO sourceObject, Employee destinationObject) {
    ModelMapper mapper = new ModelMapper();
    mapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
    mapper.map(sourceObject, destinationObject);
    return destinationObject;
  }
    }
