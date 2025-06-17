package org.nsu.medicalsystem.repository.visit;

import org.nsu.medicalsystem.entity.visit.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}