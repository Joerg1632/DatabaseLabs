package org.nsu.medicalsystem.entity.visit;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.nsu.medicalsystem.entity.institution.Cabinet;
import org.nsu.medicalsystem.entity.patient.Patient;
import org.nsu.medicalsystem.entity.personal.Doctor;

import java.time.LocalDateTime;

@Entity
@Table(name = "Visit")
@Getter @Setter @NoArgsConstructor
public class Visit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VisitID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "CabinetID", nullable = false)
    private Cabinet cabinet;

    @ManyToOne
    @JoinColumn(name = "PatientID", nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "DoctorID", nullable = false)
    private Doctor doctor;

    @Column(nullable = false)
    private LocalDateTime visitDate;
}