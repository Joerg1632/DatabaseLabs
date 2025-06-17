package org.nsu.medicalsystem.service.personal;

import org.nsu.medicalsystem.entity.personal.Vacation;
import org.nsu.medicalsystem.repository.personal.VacationRepository;
import org.nsu.medicalsystem.service.BaseService;
import org.springframework.stereotype.Service;

@Service
public class VacationService extends BaseService<Vacation, Long> {

    public VacationService(VacationRepository repository) {
        super(repository);
    }
}