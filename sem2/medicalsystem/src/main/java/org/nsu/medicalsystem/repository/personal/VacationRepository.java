package org.nsu.medicalsystem.repository.personal;

import org.nsu.medicalsystem.entity.personal.Vacation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VacationRepository extends JpaRepository<Vacation, Long> {
}
