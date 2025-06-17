package org.nsu.medicalsystem.service.institution;

import org.nsu.medicalsystem.entity.institution.MedicalInstitution;
import org.nsu.medicalsystem.repository.institution.MedicalInstitutionRepository;
import org.nsu.medicalsystem.service.BaseService;
import org.springframework.stereotype.Service;

@Service
public class MedicalInstitutionService extends BaseService<MedicalInstitution, Long> {

    public MedicalInstitutionService(MedicalInstitutionRepository repository) {
        super(repository);
    }
}
