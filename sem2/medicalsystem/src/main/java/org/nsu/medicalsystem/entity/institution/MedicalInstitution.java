package org.nsu.medicalsystem.entity.institution;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "MedicalInstitution")
@Getter
@Setter
@NoArgsConstructor
public class MedicalInstitution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "InstitutionID")
    private Long id;

    @Column(nullable = false, length = 255)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private InstitutionType type;

    @ManyToOne
    @JoinColumn(name = "AttachedHospitalID")
    private MedicalInstitution attachedHospital;

    public enum InstitutionType {
        Больница, Поликлиника
    }
}