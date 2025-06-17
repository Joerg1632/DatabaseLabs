package org.nsu.medicalsystem.entity.personal.SpecializationParameter;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.nsu.medicalsystem.entity.personal.Specialization;

import java.io.Serializable;

@Entity
@Table(name = "SpecializationParameter")
@Getter @Setter @NoArgsConstructor
public class SpecializationParameter {
    @EmbeddedId
    private SpecializationParameterId id;

    @Column(nullable = false, length = 100)
    private String parameterValue;

    @ManyToOne
    @MapsId("specializationId")
    @JoinColumn(name = "SpecializationID", nullable = false)
    private Specialization specialization;
}

