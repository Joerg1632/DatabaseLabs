package org.nsu.medicalsystem.controller.visit;

import org.nsu.medicalsystem.controller.BaseController;
import org.nsu.medicalsystem.entity.visit.Appointment;
import org.nsu.medicalsystem.service.visit.AppointmentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController extends BaseController<Appointment, Long> {

    public AppointmentController(AppointmentService service) {
        super(service);
    }
}
