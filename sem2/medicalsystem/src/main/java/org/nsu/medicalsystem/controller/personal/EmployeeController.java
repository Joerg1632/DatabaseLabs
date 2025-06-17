package org.nsu.medicalsystem.controller.personal;

import org.nsu.medicalsystem.controller.BaseController;
import org.nsu.medicalsystem.entity.personal.Employee;
import org.nsu.medicalsystem.service.personal.EmployeeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController extends BaseController<Employee, Long> {

    public EmployeeController(EmployeeService service) {
        super(service);
    }
}
