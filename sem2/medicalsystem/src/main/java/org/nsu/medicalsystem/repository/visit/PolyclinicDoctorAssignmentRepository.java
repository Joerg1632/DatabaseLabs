package org.nsu.medicalsystem.repository.visit;

import org.nsu.medicalsystem.entity.visit.PolyclinicDoctorAssignment.PolyclinicDoctorAssignment;
import org.nsu.medicalsystem.entity.visit.PolyclinicDoctorAssignment.PolyclinicDoctorAssignmentId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PolyclinicDoctorAssignmentRepository extends JpaRepository<PolyclinicDoctorAssignment, PolyclinicDoctorAssignmentId> {
}