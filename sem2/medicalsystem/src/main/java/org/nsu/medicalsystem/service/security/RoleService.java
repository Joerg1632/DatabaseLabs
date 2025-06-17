package org.nsu.medicalsystem.service.security;

import lombok.RequiredArgsConstructor;
import org.nsu.medicalsystem.entity.procedure.Surgery;
import org.nsu.medicalsystem.entity.security.Role;
import org.nsu.medicalsystem.repository.procedure.SurgeryRepository;
import org.nsu.medicalsystem.repository.security.RoleRepository;
import org.nsu.medicalsystem.service.BaseService;
import org.springframework.stereotype.Service;

@Service
public class RoleService extends BaseService<Role, Integer> {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository repository) {
        super(repository);
        this.roleRepository = repository;
    }

    public Role getRoleById(Integer id) {
        return roleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Role not found with id: " + id));
    }
}
