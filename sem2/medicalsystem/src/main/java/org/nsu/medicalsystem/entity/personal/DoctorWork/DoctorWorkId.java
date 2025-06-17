package org.nsu.medicalsystem.entity.personal.DoctorWork;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter @Setter @NoArgsConstructor
public class DoctorWorkId implements Serializable {
    @Column(name = "DoctorID")
    private Long doctorId;

    @Column(name = "InstitutionID")
    private Long institutionId;
}
