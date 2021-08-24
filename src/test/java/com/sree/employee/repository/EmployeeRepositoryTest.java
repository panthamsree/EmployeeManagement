package com.sree.employee.repository;

import com.sree.employee.entity.Employee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("test")
public class EmployeeRepositoryTest {

  @Autowired private EmployeeRepository repository;

  @Test
  public void should_create_new_user() {
    Employee emp = new Employee();
    emp.setFirstname("Jacks");
    emp.setLastname("Kulla");
    emp.setEmail("jacks@gmail.com");
    emp.setAddress("5000 W Rand");
    repository.save(emp);

    Optional<Employee> empByEmail = repository.findByEmail("jacks@gmail.com");
    assertThat(empByEmail.get()).isNotNull();

    // User{id=2, name='Tom', age=25}
    System.out.println(empByEmail.get().toString());
  }

  @Test
  public void testGetEmployee() {
    Employee employee = new Employee();
    employee.setFirstname("admin");
    employee.setLastname("admin");
    employee.setEmail("admin@gmail.com");
    employee.setAddress("400 W");
    repository.save(employee);
    Employee employee2 = repository.findByFirstname("admin");
    assertNotNull(employee);
    assertEquals(employee2.getFirstname(), employee.getFirstname());
    assertEquals(employee2.getLastname(), employee.getLastname());
  }

  @Test
  public void testDeleteEmployee() {
    Employee employee = new Employee();
    employee.setFirstname("admin");
    employee.setLastname("admin");
    employee.setEmail("admin@gmail.com");
    employee.setAddress("400 W");
    repository.save(employee);
    repository.delete(employee);
  }

  @Test
  public void findAllEmployees() {
    Employee employee = new Employee();
    employee.setFirstname("admin");
    employee.setLastname("admin");
    employee.setEmail("admin@gmail.com");
    employee.setAddress("400 W");
    repository.save(employee);
    assertNotNull(repository.findAll());
  }

  @Test
  public void deletByEmployeeIdTest() {
    Employee employee = new Employee();
    employee.setFirstname("admin");
    employee.setLastname("admin");
    employee.setEmail("admin@gmail.com");
    employee.setAddress("400 W");
    Employee emp = repository.save(employee);
    repository.deleteById(emp.getId());
  }
}