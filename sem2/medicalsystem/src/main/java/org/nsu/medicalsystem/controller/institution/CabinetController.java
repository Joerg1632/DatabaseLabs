package org.nsu.medicalsystem.controller.institution;

import org.nsu.medicalsystem.controller.BaseController;
import org.nsu.medicalsystem.entity.institution.Cabinet;
import org.nsu.medicalsystem.service.institution.CabinetService;
import org.nsu.medicalsystem.service.institution.WardService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/cabinets")
public class CabinetController extends BaseController<Cabinet, Long> {

    private final CabinetService cabinetService;

    public CabinetController(CabinetService cabinetService) {
        super(cabinetService);
        this.cabinetService = cabinetService;
    }

}
