package org.nsu.medicalsystem.controller.personal;

import org.nsu.medicalsystem.controller.BaseController;
import org.nsu.medicalsystem.entity.personal.SpecializationParameter.SpecializationParameter;
import org.nsu.medicalsystem.entity.personal.SpecializationParameter.SpecializationParameterId;
import org.nsu.medicalsystem.service.personal.SpecializationParameterService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/specialization-parameters")
public class SpecializationParameterController extends BaseController<SpecializationParameter, SpecializationParameterId> {

    public SpecializationParameterController(SpecializationParameterService service) {
        super(service);
    }
}
