package org.nsu.medicalsystem.controller.institution;

import org.nsu.medicalsystem.controller.BaseController;
import org.nsu.medicalsystem.entity.institution.Building;
import org.nsu.medicalsystem.service.institution.BuildingService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/buildings")
public class BuildingController extends BaseController<Building, Long> {

    public BuildingController(BuildingService service) {
        super(service);
    }
}
