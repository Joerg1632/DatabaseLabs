package org.nsu.medicalsystem.controller.patient;

import org.nsu.medicalsystem.controller.BaseController;
import org.nsu.medicalsystem.entity.patient.MedicalHistory;
import org.nsu.medicalsystem.service.patient.MedicalHistoryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/medical-histories")
public class MedicalHistoryController extends BaseController<MedicalHistory, Long> {

    public MedicalHistoryController(MedicalHistoryService service) {
        super(service);
    }
}
