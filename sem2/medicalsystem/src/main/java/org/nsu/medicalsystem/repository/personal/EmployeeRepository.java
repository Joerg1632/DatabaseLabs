package org.nsu.medicalsystem.repository.personal;

import org.nsu.medicalsystem.entity.personal.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
