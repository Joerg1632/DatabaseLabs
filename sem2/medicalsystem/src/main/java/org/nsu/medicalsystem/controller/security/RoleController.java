package org.nsu.medicalsystem.controller.security;

import org.nsu.medicalsystem.controller.BaseController;
import org.nsu.medicalsystem.entity.security.Role;
import org.nsu.medicalsystem.service.security.RoleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/roles")
public class RoleController extends BaseController<Role, Integer> {

    public RoleController(RoleService service) {
        super(service);
    }
}
