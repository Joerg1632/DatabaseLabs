package org.nsu.medicalsystem.controller.institution;

import org.nsu.medicalsystem.controller.BaseController;
import org.nsu.medicalsystem.entity.institution.Department;
import org.nsu.medicalsystem.service.institution.DepartmentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController extends BaseController<Department, Long> {

    public DepartmentController(DepartmentService service) {
        super(service);
    }
}
