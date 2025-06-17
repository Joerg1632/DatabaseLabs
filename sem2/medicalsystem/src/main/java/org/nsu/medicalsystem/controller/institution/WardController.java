package org.nsu.medicalsystem.controller.institution;

import org.nsu.medicalsystem.controller.BaseController;
import org.nsu.medicalsystem.entity.institution.Ward;
import org.nsu.medicalsystem.service.institution.WardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wards")
public class WardController extends BaseController<Ward, Long> {

    private final WardService wardService;

    public WardController(WardService wardService) {
        super(wardService);
        this.wardService = wardService;
    }


}