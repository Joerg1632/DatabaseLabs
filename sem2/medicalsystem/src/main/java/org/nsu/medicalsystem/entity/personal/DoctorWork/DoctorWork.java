package org.nsu.medicalsystem.entity.personal.DoctorWork;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.nsu.medicalsystem.entity.institution.MedicalInstitution;
import org.nsu.medicalsystem.entity.personal.Doctor;

import java.io.Serializable;

@Entity
@Table(name = "DoctorWork")
@Getter @Setter @NoArgsConstructor
public class DoctorWork {
    @EmbeddedId
    private DoctorWorkId id;

    @ManyToOne
    @MapsId("doctorId")
    @JoinColumn(name = "DoctorID", nullable = false)
    private Doctor doctor;

    @ManyToOne
    @MapsId("institutionId")
    @JoinColumn(name = "InstitutionID", nullable = false)
    private MedicalInstitution institution;
}