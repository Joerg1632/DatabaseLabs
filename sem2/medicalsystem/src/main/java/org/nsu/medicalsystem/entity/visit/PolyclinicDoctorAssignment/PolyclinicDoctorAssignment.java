package org.nsu.medicalsystem.entity.visit.PolyclinicDoctorAssignment;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.nsu.medicalsystem.entity.patient.Patient;
import org.nsu.medicalsystem.entity.personal.Doctor;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "PolyclinicDoctorAssignment")
@Getter @Setter @NoArgsConstructor
public class PolyclinicDoctorAssignment {
    @EmbeddedId
    private PolyclinicDoctorAssignmentId id;

    @ManyToOne
    @MapsId("patientId")
    @JoinColumn(name = "PatientID", nullable = false)
    private Patient patient;

    @ManyToOne
    @MapsId("doctorId")
    @JoinColumn(name = "DoctorID", nullable = false)
    private Doctor doctor;

    @Column(nullable = false)
    private LocalDate assignmentDate;
}

