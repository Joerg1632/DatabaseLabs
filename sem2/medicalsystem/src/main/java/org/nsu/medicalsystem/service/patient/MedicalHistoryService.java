package org.nsu.medicalsystem.service.patient;

import org.nsu.medicalsystem.entity.patient.MedicalHistory;
import org.nsu.medicalsystem.repository.patient.MedicalHistoryRepository;
import org.nsu.medicalsystem.service.BaseService;
import org.springframework.stereotype.Service;

@Service
public class MedicalHistoryService extends BaseService<MedicalHistory, Long> {

    public MedicalHistoryService(MedicalHistoryRepository repository) {
        super(repository);
    }
}
