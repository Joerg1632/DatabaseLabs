package org.nsu.medicalsystem.entity.personal;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.nsu.medicalsystem.entity.institution.MedicalInstitution;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Doctor")
@PrimaryKeyJoinColumn(name = "DoctorID")
@Getter
@Setter
@NoArgsConstructor
public class Doctor extends Employee {
    @ManyToOne
    @JoinColumn(name = "SpecializationID", nullable = false)
    private Specialization specialization;

    @Enumerated(EnumType.STRING)
    private Degree degree;

    @Enumerated(EnumType.STRING)
    private Title title;

    private Integer experience;

    @ManyToMany
    @JoinTable(name = "DoctorWork",
            joinColumns = @JoinColumn(name = "DoctorID"),
            inverseJoinColumns = @JoinColumn(name = "InstitutionID"))
    private Set<MedicalInstitution> institutions = new HashSet<>();

    public enum Degree {
        Кандидат, Доктор
    }

    public enum Title {
        Доцент, Профессор
    }
}