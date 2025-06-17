package org.nsu.medicalsystem.entity.procedure;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.nsu.medicalsystem.entity.institution.Laboratory;
import org.nsu.medicalsystem.entity.patient.Patient;

import java.time.LocalDate;

@Entity
@Table(name = "LabTest")
@Getter @Setter @NoArgsConstructor
public class LabTest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TestID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "LabID", nullable = false)
    private Laboratory lab;

    @ManyToOne
    @JoinColumn(name = "PatientID", nullable = false)
    private Patient patient;

    @Column(nullable = false)
    private LocalDate testDate;

    @Column(nullable = false, length = 100)
    private String testType;
}