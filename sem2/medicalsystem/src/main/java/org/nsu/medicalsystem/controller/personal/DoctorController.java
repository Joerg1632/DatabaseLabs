package org.nsu.medicalsystem.controller.personal;

import org.nsu.medicalsystem.controller.BaseController;
import org.nsu.medicalsystem.entity.personal.Doctor;
import org.nsu.medicalsystem.service.personal.DoctorService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController extends BaseController<Doctor, Long> {

    private final DoctorService doctorService;

    public DoctorController(DoctorService service) {
        super(service);
        this.doctorService = service;
    }

    @GetMapping("/by-specialization")
    public ResponseEntity<Map<String, Object>> getDoctorsBySpecialization(
            @RequestParam Long specializationId,
            @RequestParam(required = false) Long institutionId) {

        List<Doctor> doctors = doctorService.getDoctorsBySpecialization(specializationId, institutionId);
        Long total = doctorService.countDoctorsBySpecialization(specializationId, institutionId);

        Map<String, Object> response = new HashMap<>();
        response.put("doctors", doctors);
        response.put("total", total);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/by-experience")
    public ResponseEntity<Map<String, Object>> getDoctorsByExperience(
            @RequestParam Long specializationId,
            @RequestParam int minExperience,
            @RequestParam(required = false) Long institutionId
    ) {
        List<Doctor> doctors = doctorService.getDoctorsBySpecializationAndMinExperience(specializationId, minExperience, institutionId);
        long total = doctorService.countDoctorsBySpecializationAndMinExperience(specializationId, minExperience, institutionId);

        Map<String, Object> response = new HashMap<>();
        response.put("doctors", doctors);
        response.put("total", total);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/by-specialization-min-operations")
    public Map<String, Object> getDoctorsBySpecializationAndMinOperations(
            @RequestParam Long specializationId,
            @RequestParam(required = false) Long institutionId,
            @RequestParam Long minOperations
    ) {
        return doctorService.getDoctorsBySpecializationAndMinOperations(specializationId, institutionId, minOperations);
    }

    @GetMapping("/qualified")
    public ResponseEntity<Map<String, Object>> getQualifiedDoctors(
            @RequestParam Long specializationId,
            @RequestParam(required = false) Long institutionId
    ) {
        List<Doctor> doctors = doctorService.getQualifiedDoctors(specializationId, institutionId);
        long count = doctorService.countQualifiedDoctors(specializationId, institutionId);

        Map<String, Object> result = new HashMap<>();
        result.put("count", count);
        result.put("doctors", doctors);

        return ResponseEntity.ok(result);
    }
}
