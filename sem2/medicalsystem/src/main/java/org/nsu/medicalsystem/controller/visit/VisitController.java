package org.nsu.medicalsystem.controller.visit;

import org.nsu.medicalsystem.controller.BaseController;
import org.nsu.medicalsystem.entity.visit.Visit;
import org.nsu.medicalsystem.service.visit.VisitService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/visits")
public class VisitController extends BaseController<Visit, Long> {

    public VisitController(VisitService service) {
        super(service);
    }
}
