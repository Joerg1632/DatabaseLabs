package org.nsu.medicalsystem.service.institution;

import org.nsu.medicalsystem.entity.institution.Laboratory;
import org.nsu.medicalsystem.repository.institution.LaboratoryRepository;
import org.nsu.medicalsystem.repository.personal.DoctorRepository;
import org.nsu.medicalsystem.service.BaseService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LaboratoryService extends BaseService<Laboratory, Long> {

    private final LaboratoryRepository laboratoryRepository;

    public LaboratoryService(LaboratoryRepository repository) {
        super(repository);
        this.laboratoryRepository = repository;
    }


}
