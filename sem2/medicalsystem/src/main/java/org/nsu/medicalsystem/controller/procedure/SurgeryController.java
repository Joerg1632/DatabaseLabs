package org.nsu.medicalsystem.controller.procedure;

import org.nsu.medicalsystem.controller.BaseController;
import org.nsu.medicalsystem.entity.patient.Patient;
import org.nsu.medicalsystem.entity.procedure.Surgery;
import org.nsu.medicalsystem.service.personal.DoctorService;
import org.nsu.medicalsystem.service.procedure.SurgeryService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/surgeries")
public class SurgeryController extends BaseController<Surgery, Long> {

    private final SurgeryService surgeryService;

    public SurgeryController(SurgeryService service) {
        super(service);
        this.surgeryService = service;
    }
}
