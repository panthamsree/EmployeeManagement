package com.sree.employee.repository;

import com.sree.employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    public Optional<Employee> findByEmail(String emailId);

    Employee findByFirstname(String username);
}