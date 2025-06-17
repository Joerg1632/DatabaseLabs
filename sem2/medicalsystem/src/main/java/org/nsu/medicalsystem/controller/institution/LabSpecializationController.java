package org.nsu.medicalsystem.controller.institution;

import org.nsu.medicalsystem.controller.BaseController;
import org.nsu.medicalsystem.entity.institution.LabSpecialization.LabSpecialization;
import org.nsu.medicalsystem.entity.institution.LabSpecialization.LabSpecializationId;
import org.nsu.medicalsystem.service.institution.LabSpecializationService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/lab-specializations")
public class LabSpecializationController extends BaseController<LabSpecialization, LabSpecializationId> {

    public LabSpecializationController(LabSpecializationService service) {
        super(service);
    }
}
