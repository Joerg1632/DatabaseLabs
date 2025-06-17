package org.nsu.medicalsystem.controller.personal;

import org.nsu.medicalsystem.controller.BaseController;
import org.nsu.medicalsystem.entity.personal.Vacation;
import org.nsu.medicalsystem.service.personal.VacationService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vacations")
public class VacationController extends BaseController<Vacation, Long> {

    public VacationController(VacationService service) {
        super(service);
    }
}
