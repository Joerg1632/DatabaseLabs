package org.nsu.medicalsystem.entity.institution.LabSpecialization;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter @Setter @NoArgsConstructor
public class LabSpecializationId implements Serializable {
    @Column(name = "LabID")
    private Long labId;

    @Column(name = "Specialization", length = 50)
    private String specialization;
}
