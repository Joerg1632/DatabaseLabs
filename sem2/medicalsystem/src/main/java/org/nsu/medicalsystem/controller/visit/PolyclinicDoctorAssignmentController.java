package org.nsu.medicalsystem.controller.visit;

import org.nsu.medicalsystem.controller.BaseController;
import org.nsu.medicalsystem.entity.visit.PolyclinicDoctorAssignment.PolyclinicDoctorAssignment;
import org.nsu.medicalsystem.entity.visit.PolyclinicDoctorAssignment.PolyclinicDoctorAssignmentId;
import org.nsu.medicalsystem.service.visit.PolyclinicDoctorAssignmentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/polyclinic-doctor-assignments")
public class PolyclinicDoctorAssignmentController extends BaseController<PolyclinicDoctorAssignment, PolyclinicDoctorAssignmentId> {

    public PolyclinicDoctorAssignmentController(PolyclinicDoctorAssignmentService service) {
        super(service);
    }
}
