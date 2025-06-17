package org.nsu.medicalsystem.service.visit;

import org.nsu.medicalsystem.entity.visit.Appointment;
import org.nsu.medicalsystem.repository.visit.AppointmentRepository;
import org.nsu.medicalsystem.service.BaseService;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService extends BaseService<Appointment, Long> {

    public AppointmentService(AppointmentRepository repository) {
        super(repository);
    }
}
