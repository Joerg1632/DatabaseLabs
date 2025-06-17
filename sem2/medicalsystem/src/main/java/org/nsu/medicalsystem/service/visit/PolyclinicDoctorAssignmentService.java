package org.nsu.medicalsystem.service.visit;

import org.nsu.medicalsystem.entity.visit.PolyclinicDoctorAssignment.PolyclinicDoctorAssignment;
import org.nsu.medicalsystem.entity.visit.PolyclinicDoctorAssignment.PolyclinicDoctorAssignmentId;
import org.nsu.medicalsystem.repository.visit.PolyclinicDoctorAssignmentRepository;
import org.nsu.medicalsystem.service.BaseService;
import org.springframework.stereotype.Service;

@Service
public class PolyclinicDoctorAssignmentService extends BaseService<PolyclinicDoctorAssignment, PolyclinicDoctorAssignmentId> {

    public PolyclinicDoctorAssignmentService(PolyclinicDoctorAssignmentRepository repository) {
        super(repository);
    }
}
