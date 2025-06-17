package org.nsu.medicalsystem.controller.patient;

import org.nsu.medicalsystem.controller.BaseController;
import org.nsu.medicalsystem.entity.patient.HospitalStay;
import org.nsu.medicalsystem.service.patient.HospitalStayService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hospital-stays")
public class HospitalStayController extends BaseController<HospitalStay, Long> {

    public HospitalStayController(HospitalStayService service) {
        super(service);
    }
}
