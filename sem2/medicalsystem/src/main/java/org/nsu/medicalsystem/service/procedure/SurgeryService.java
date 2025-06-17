package org.nsu.medicalsystem.service.procedure;

import org.nsu.medicalsystem.entity.patient.Patient;
import org.nsu.medicalsystem.entity.procedure.Surgery;
import org.nsu.medicalsystem.repository.patient.PatientRepository;
import org.nsu.medicalsystem.repository.procedure.SurgeryRepository;
import org.nsu.medicalsystem.service.BaseService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SurgeryService extends BaseService<Surgery, Long> {

    private final SurgeryRepository surgeryRepository;

    public SurgeryService(SurgeryRepository repository) {
        super(repository);
        this.surgeryRepository = repository;
    }

}
