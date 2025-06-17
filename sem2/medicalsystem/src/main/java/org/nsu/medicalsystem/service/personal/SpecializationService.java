package org.nsu.medicalsystem.service.personal;

import org.nsu.medicalsystem.entity.personal.Specialization;
import org.nsu.medicalsystem.repository.personal.SpecializationRepository;
import org.nsu.medicalsystem.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

@Service
public class SpecializationService extends BaseService<Specialization, Long> {

    private final SpecializationRepository specializationRepository;

    public SpecializationService(SpecializationRepository repository, SpecializationRepository specializationRepository) {
        super(repository);
        this.specializationRepository = specializationRepository;
    }

    public Page<Specialization> getSpecializations(Pageable pageable) {
        return specializationRepository.findAll(pageable);
    }
}