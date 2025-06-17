package org.nsu.medicalsystem.entity.institution.LabContract;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;

@Embeddable
@Getter @Setter @NoArgsConstructor
public class LabContractId implements Serializable {
    @Column(name = "LabID")
    private Long labId;

    @Column(name = "InstitutionID")
    private Long institutionId;
}
