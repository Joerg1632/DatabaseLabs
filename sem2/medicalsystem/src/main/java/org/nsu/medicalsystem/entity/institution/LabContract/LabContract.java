package org.nsu.medicalsystem.entity.institution.LabContract;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.nsu.medicalsystem.entity.institution.Laboratory;
import org.nsu.medicalsystem.entity.institution.MedicalInstitution;

@Entity
@Table(name = "LabContract")
@Getter @Setter @NoArgsConstructor
public class LabContract {
    @EmbeddedId
    private LabContractId id;

    @ManyToOne
    @MapsId("labId")
    @JoinColumn(name = "LabID")
    private Laboratory lab;

    @ManyToOne
    @MapsId("institutionId")
    @JoinColumn(name = "InstitutionID")
    private MedicalInstitution institution;
}