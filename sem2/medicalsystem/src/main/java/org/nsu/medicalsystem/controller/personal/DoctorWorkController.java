package org.nsu.medicalsystem.controller.personal;

import org.nsu.medicalsystem.controller.BaseController;
import org.nsu.medicalsystem.entity.personal.DoctorWork.DoctorWork;
import org.nsu.medicalsystem.entity.personal.DoctorWork.DoctorWorkId;
import org.nsu.medicalsystem.service.personal.DoctorWorkService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/doctor-works")
public class DoctorWorkController extends BaseController<DoctorWork, DoctorWorkId> {

    public DoctorWorkController(DoctorWorkService service) {
        super(service);
    }
}
