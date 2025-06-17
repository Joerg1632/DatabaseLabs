package org.nsu.medicalsystem.controller.personal;

import org.nsu.medicalsystem.controller.BaseController;
import org.nsu.medicalsystem.entity.personal.SupportStaff;
import org.nsu.medicalsystem.service.personal.SupportStaffService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/support-staff")
public class SupportStaffController extends BaseController<SupportStaff, Long> {

    private final SupportStaffService supportStaffService;

    public SupportStaffController(SupportStaffService service) {
        super(service);
        this.supportStaffService = service;
    }

    @GetMapping("/by-specialization")
    public ResponseEntity<Map<String, Object>> getStaffBySpecialization(
            @RequestParam Long specializationId,
            @RequestParam(required = false) Long institutionId) {

        List<SupportStaff> staff = supportStaffService.getStaffBySpecialization(specializationId, institutionId);
        Long total = supportStaffService.countStaffBySpecialization(specializationId, institutionId);

        Map<String, Object> response = new HashMap<>();
        response.put("staff", staff);
        response.put("total", total);

        return ResponseEntity.ok(response);
    }
}
