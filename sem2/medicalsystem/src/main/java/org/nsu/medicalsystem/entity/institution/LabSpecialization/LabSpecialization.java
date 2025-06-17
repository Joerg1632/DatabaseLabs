package org.nsu.medicalsystem.entity.institution.LabSpecialization;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.nsu.medicalsystem.entity.institution.Laboratory;

import java.io.Serializable;

@Entity
@Table(name = "LabSpecialization")
@Getter @Setter @NoArgsConstructor
public class LabSpecialization {
    @EmbeddedId
    private LabSpecializationId id;

    @ManyToOne
    @MapsId("labId")
    @JoinColumn(name = "LabID")
    private Laboratory lab;
}