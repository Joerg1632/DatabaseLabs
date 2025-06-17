package org.nsu.medicalsystem.repository.visit;

import org.nsu.medicalsystem.entity.visit.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitRepository extends JpaRepository<Visit, Long> {
}