package org.nsu.medicalsystem.repository.patient;

import org.nsu.medicalsystem.entity.patient.MedicalHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalHistoryRepository extends JpaRepository<MedicalHistory, Long> {
}

