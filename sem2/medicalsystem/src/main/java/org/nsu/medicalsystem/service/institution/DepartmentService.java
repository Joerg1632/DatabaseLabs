package org.nsu.medicalsystem.service.institution;

import org.nsu.medicalsystem.entity.institution.Department;
import org.nsu.medicalsystem.repository.institution.DepartmentRepository;
import org.nsu.medicalsystem.service.BaseService;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService extends BaseService<Department, Long> {

    public DepartmentService(DepartmentRepository repository) {
        super(repository);
    }
}
