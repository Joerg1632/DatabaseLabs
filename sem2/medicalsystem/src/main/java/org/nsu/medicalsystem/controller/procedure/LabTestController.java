package org.nsu.medicalsystem.controller.procedure;

import org.nsu.medicalsystem.controller.BaseController;
import org.nsu.medicalsystem.entity.procedure.LabTest;
import org.nsu.medicalsystem.service.procedure.LabTestService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/lab-tests")
public class LabTestController extends BaseController<LabTest, Long> {

    public LabTestController(LabTestService service) {
        super(service);
    }
}
