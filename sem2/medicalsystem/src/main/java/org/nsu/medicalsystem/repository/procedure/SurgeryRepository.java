package org.nsu.medicalsystem.repository.procedure;

import org.nsu.medicalsystem.entity.patient.Patient;
import org.nsu.medicalsystem.entity.procedure.Surgery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SurgeryRepository extends JpaRepository<Surgery, Long> {

}

