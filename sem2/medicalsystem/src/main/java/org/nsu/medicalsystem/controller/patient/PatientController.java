package org.nsu.medicalsystem.controller.patient;

import org.nsu.medicalsystem.controller.BaseController;
import org.nsu.medicalsystem.entity.patient.Patient;
import org.nsu.medicalsystem.service.patient.PatientService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController extends BaseController<Patient, Long> {

    private final PatientService patientService;

    public PatientController(PatientService service) {
        super(service);
        this.patientService = service;
    }

    @GetMapping("/hospital-stays")
    public List<Object[]> getHospitalStaysInfo(
            @RequestParam Long institutionId,
            @RequestParam(required = false) Long departmentId,
            @RequestParam(required = false) Long wardId
    ) {
        return patientService.getHospitalizedPatientsWithFilters(institutionId, departmentId, wardId);
    }

}
