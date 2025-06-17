package org.nsu.medicalsystem.entity.procedure;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.nsu.medicalsystem.entity.patient.Patient;
import org.nsu.medicalsystem.entity.personal.Doctor;

import java.time.LocalDate;

@Entity
@Table(name = "Surgery")
@Getter
@Setter
@NoArgsConstructor
public class Surgery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SurgeryID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "PatientID", nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "DoctorID", nullable = false)
    private Doctor doctor;

    @Column(nullable = false)
    private LocalDate surgeryDate;

    @Column(nullable = false)
    private Boolean isFatal = false;

    @Lob
    private String result;
}