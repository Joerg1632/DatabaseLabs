package org.nsu.medicalsystem.service.procedure;

import org.nsu.medicalsystem.entity.procedure.LabTest;
import org.nsu.medicalsystem.repository.procedure.LabTestRepository;
import org.nsu.medicalsystem.service.BaseService;
import org.springframework.stereotype.Service;

@Service
public class LabTestService extends BaseService<LabTest, Long> {

    public LabTestService(LabTestRepository repository) {
        super(repository);
    }
}
