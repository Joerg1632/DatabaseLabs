package org.nsu.medicalsystem.entity.personal.SpecializationParameter;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter @Setter @NoArgsConstructor
public class SpecializationParameterId implements Serializable {
    @Column(name = "SpecializationID")
    private Long specializationId;

    @Column(name = "ParameterName", length = 50)
    private String parameterName;
}
