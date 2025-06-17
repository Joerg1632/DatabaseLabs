package org.nsu.medicalsystem.entity.patient;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.nsu.medicalsystem.entity.institution.Ward;
import org.nsu.medicalsystem.entity.personal.Doctor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "HospitalStay")
@Getter
@Setter
@NoArgsConstructor
public class HospitalStay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "StayID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "PatientID", nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "WardID", nullable = false)
    private Ward ward;

    @Column(nullable = false)
    private LocalDate admitDate;

    private LocalDate dischargeDate;

    @Lob
    private String currentState;

    @Column(precision = 4, scale = 2)
    private BigDecimal temperature;

    @ManyToOne
    @JoinColumn(name = "PrimaryDoctorID", nullable = false)
    private Doctor primaryDoctor;
}