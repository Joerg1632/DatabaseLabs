package org.nsu.medicalsystem.service.visit;

import org.nsu.medicalsystem.entity.visit.Visit;
import org.nsu.medicalsystem.repository.visit.VisitRepository;
import org.nsu.medicalsystem.service.BaseService;
import org.springframework.stereotype.Service;

@Service
public class VisitService extends BaseService<Visit, Long> {

    public VisitService(VisitRepository repository) {
        super(repository);
    }
}
