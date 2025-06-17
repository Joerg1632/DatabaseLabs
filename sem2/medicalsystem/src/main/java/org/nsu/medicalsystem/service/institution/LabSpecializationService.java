package org.nsu.medicalsystem.service.institution;

import org.nsu.medicalsystem.entity.institution.LabSpecialization.LabSpecialization;
import org.nsu.medicalsystem.entity.institution.LabSpecialization.LabSpecializationId;
import org.nsu.medicalsystem.repository.institution.LabSpecializationRepository;
import org.nsu.medicalsystem.service.BaseService;
import org.springframework.stereotype.Service;

@Service
public class LabSpecializationService extends BaseService<LabSpecialization, LabSpecializationId> {

    public LabSpecializationService(LabSpecializationRepository repository) {
        super(repository);
    }
}
