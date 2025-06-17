package org.nsu.medicalsystem.controller.institution;

import org.nsu.medicalsystem.controller.BaseController;
import org.nsu.medicalsystem.entity.institution.Laboratory;
import org.nsu.medicalsystem.service.institution.LaboratoryService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/laboratories")
public class LaboratoryController extends BaseController<Laboratory, Long> {

    private final LaboratoryService laboratoryService;

    public LaboratoryController(LaboratoryService service) {
        super(service);
        this.laboratoryService = service;
    }


}
