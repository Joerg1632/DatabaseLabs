package org.nsu.medicalsystem.service.personal;

import org.nsu.medicalsystem.entity.personal.Employee;
import org.nsu.medicalsystem.repository.personal.EmployeeRepository;
import org.nsu.medicalsystem.service.BaseService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService extends BaseService<Employee, Long> {

    public EmployeeService(EmployeeRepository repository) {
        super(repository);
    }
}