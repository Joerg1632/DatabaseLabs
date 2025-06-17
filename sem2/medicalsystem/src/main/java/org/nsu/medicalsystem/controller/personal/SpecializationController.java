package org.nsu.medicalsystem.controller.personal;

import org.nsu.medicalsystem.controller.BaseController;
import org.nsu.medicalsystem.entity.personal.Specialization;
import org.nsu.medicalsystem.service.personal.SpecializationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/specializations")
public class SpecializationController extends BaseController<Specialization, Long> {

    private final SpecializationService specializationService;

    public SpecializationController(SpecializationService service) {
        super(service);
        this.specializationService = service;
    }

    @GetMapping("/paged")
    public Page<Specialization> getPagedSpecializations(Pageable pageable) {
        return specializationService.getSpecializations(pageable);
    }
}
