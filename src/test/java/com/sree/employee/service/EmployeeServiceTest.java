package com.sree.employee.service;

import com.sree.employee.entity.Employee;
import com.sree.employee.exception.EmployeeAlreadyExistsException;
import com.sree.employee.exception.EmployeeNotFoundException;
import com.sree.employee.mapper.EmployeeMapper;
import com.sree.employee.repository.EmployeeRepository;
import com.sree.employee.vo.EmployeeVO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
// @SpringBootTest
class EmployeeServiceTest {

  @InjectMocks private EmployeeServiceImpl employeeServiceImpl;

  @Mock private EmployeeRepository employeeRepository;

  @InjectMocks private EmployeeMapper mapper;

  @Mock private EmployeeMapper employeeMapper;

  @Test
  void createEmployeeSuccess() {

    EmployeeVO employeeVO = new EmployeeVO();
    employeeVO.setId(101);
    employeeVO.setFirstname("James");
    employeeVO.setLastname("Kulla");
    employeeVO.setEmail("james@gmail.com");
    employeeVO.setAddress("400 W Rand");

    String emil = "test@gmail.com";

    Optional<Employee> emp = Optional.of(mapper.toEmployee(employeeVO));
    when(employeeRepository.findByEmail(emil)).thenReturn(emp);
    when(employeeMapper.toEmployeeVO(
            employeeRepository.save(employeeMapper.toEmployee(employeeVO))))
        .thenReturn(employeeVO);
    employeeServiceImpl.createEmployee(employeeVO);
  }

  @Test
  void createEmployeeFailure() {

    EmployeeVO employeeVO = new EmployeeVO();
    employeeVO.setId(101);
    employeeVO.setFirstname("James");
    employeeVO.setLastname("Kulla");
    employeeVO.setEmail("james@gmail.com");
    employeeVO.setAddress("400 W Rand");

    Optional<Employee> emp = Optional.of(mapper.toEmployee(employeeVO));
    when(employeeRepository.findByEmail(employeeVO.getEmail())).thenReturn(emp);
    when(employeeMapper.toEmployeeVO(
            employeeRepository.save(employeeMapper.toEmployee(employeeVO))))
        .thenReturn(employeeVO);

    assertThrows(
        EmployeeAlreadyExistsException.class,
        () -> {
          employeeServiceImpl.createEmployee(employeeVO);
        });

    ;
  }

  @Test
  void getEmployeeFound() {

    EmployeeVO employeeVO = new EmployeeVO();
    employeeVO.setId(101);
    employeeVO.setFirstname("James");
    employeeVO.setLastname("Kulla");
    employeeVO.setEmail("james@gmail.com");
    employeeVO.setAddress("400 W Rand");

    Optional<Employee> emp = Optional.of(mapper.toEmployee(employeeVO));
    when(employeeRepository.findById(12345)).thenReturn(emp);
    employeeServiceImpl.getEmployee(12345);
    assertNotNull(employeeVO);
    assertEquals("James", employeeVO.getFirstname());
  }

  @Test
  void getEmployeeNotFound() {

    Optional<Employee> emp = Optional.empty();
    when(employeeRepository.findById(12345)).thenReturn(emp);
    assertThrows(
        EmployeeNotFoundException.class,
        () -> {
          employeeServiceImpl.getEmployee(12345);
        });
  }

  @Test
  void deleteEmployee() {}

  @Test
  void updateEmployeeSuccess() {

    EmployeeVO employeeVO = new EmployeeVO();
    employeeVO.setId(1234);
    employeeVO.setFirstname("James");
    employeeVO.setLastname("Kulla");
    employeeVO.setEmail("james@gmail.com");
    employeeVO.setAddress("400 W Rand");
    Optional<Employee> emp = Optional.of(mapper.toEmployee(employeeVO));
    when(employeeRepository.findById(1234)).thenReturn(emp);
    employeeServiceImpl.updateEmployee(1234, employeeVO);
  }

  @Test
  void updateEmployeeFailed() {

    EmployeeVO employeeVO = new EmployeeVO();
    employeeVO.setId(1234);
    employeeVO.setFirstname("James");
    employeeVO.setLastname("Kulla");
    employeeVO.setEmail("james@gmail.com");
    employeeVO.setAddress("400 W Rand");
    Optional<Employee> emp = Optional.empty();
    assertThrows(
        EmployeeNotFoundException.class,
        () -> {
          employeeServiceImpl.updateEmployee(1234, employeeVO);
        });
  }

  @Test
  void getAllEmployees() {}
}