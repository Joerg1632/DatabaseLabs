package org.nsu.medicalsystem.entity.visit.PolyclinicDoctorAssignment;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter @Setter @NoArgsConstructor
public class PolyclinicDoctorAssignmentId implements Serializable {
    @Column(name = "PatientID")
    private Long patientId;

    @Column(name = "DoctorID")
    private Long doctorId;
}
