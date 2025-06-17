package org.nsu.medicalsystem.controller.security;

import org.nsu.medicalsystem.controller.BaseController;
import org.nsu.medicalsystem.entity.security.User;
import org.nsu.medicalsystem.service.security.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController extends BaseController<User, Long> {

    public UserController(UserService userService) {
        super(userService);
    }
}
