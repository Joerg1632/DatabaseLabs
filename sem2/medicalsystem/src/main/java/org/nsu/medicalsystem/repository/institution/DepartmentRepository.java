package org.nsu.medicalsystem.repository.institution;

import org.nsu.medicalsystem.entity.institution.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
