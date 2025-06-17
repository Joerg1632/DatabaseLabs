package org.nsu.medicalsystem.service.patient;

import org.nsu.medicalsystem.entity.patient.Patient;
import org.nsu.medicalsystem.repository.patient.PatientRepository;
import org.nsu.medicalsystem.service.BaseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.time.LocalDate;

@Service
public class PatientService extends BaseService<Patient, Long> {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository repository) {
        super(repository);
        this.patientRepository = repository;
    }

    public List<Object[]> getHospitalizedPatientsWithFilters(Long institutionId, Long departmentId, Long wardId) {
        return patientRepository.findHospitalizedPatientsWithFilters(institutionId, departmentId, wardId);
    }
}
