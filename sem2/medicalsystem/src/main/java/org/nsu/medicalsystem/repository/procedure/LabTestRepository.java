package org.nsu.medicalsystem.repository.procedure;

import org.nsu.medicalsystem.entity.procedure.LabTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabTestRepository extends JpaRepository<LabTest, Long> {
}