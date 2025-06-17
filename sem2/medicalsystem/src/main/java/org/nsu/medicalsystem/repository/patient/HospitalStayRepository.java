package org.nsu.medicalsystem.repository.patient;

import org.nsu.medicalsystem.entity.patient.HospitalStay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalStayRepository extends JpaRepository<HospitalStay, Long> {
}
