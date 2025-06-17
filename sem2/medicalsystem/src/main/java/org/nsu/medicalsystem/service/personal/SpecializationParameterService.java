package org.nsu.medicalsystem.service.personal;

import org.nsu.medicalsystem.entity.personal.SpecializationParameter.SpecializationParameter;
import org.nsu.medicalsystem.entity.personal.SpecializationParameter.SpecializationParameterId;
import org.nsu.medicalsystem.repository.personal.SpecializationParameterRepository;
import org.nsu.medicalsystem.service.BaseService;
import org.springframework.stereotype.Service;

@Service
public class SpecializationParameterService extends BaseService<SpecializationParameter, SpecializationParameterId> {

    public SpecializationParameterService(SpecializationParameterRepository repository) {
        super(repository);
    }
}
