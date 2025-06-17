package org.nsu.medicalsystem.service.patient;

import org.nsu.medicalsystem.entity.patient.HospitalStay;
import org.nsu.medicalsystem.repository.patient.HospitalStayRepository;
import org.nsu.medicalsystem.service.BaseService;
import org.springframework.stereotype.Service;

@Service
public class HospitalStayService extends BaseService<HospitalStay, Long> {

    public HospitalStayService(HospitalStayRepository repository) {
        super(repository);
    }
}
