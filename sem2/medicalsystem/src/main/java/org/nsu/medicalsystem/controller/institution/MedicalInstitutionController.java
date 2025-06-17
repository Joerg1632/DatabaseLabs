package org.nsu.medicalsystem.controller.institution;

import org.nsu.medicalsystem.controller.BaseController;
import org.nsu.medicalsystem.entity.institution.MedicalInstitution;
import org.nsu.medicalsystem.service.institution.MedicalInstitutionService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/medical-institutions")
public class MedicalInstitutionController extends BaseController<MedicalInstitution, Long> {

    public MedicalInstitutionController(MedicalInstitutionService service) {
        super(service);
    }
}
