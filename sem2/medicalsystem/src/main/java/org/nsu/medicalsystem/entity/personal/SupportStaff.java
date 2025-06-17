package org.nsu.medicalsystem.entity.personal;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.nsu.medicalsystem.entity.institution.MedicalInstitution;

@Entity
@Table(name = "SupportStaff")
@PrimaryKeyJoinColumn(name = "StaffID")
@Getter
@Setter
@NoArgsConstructor
public class SupportStaff extends Employee {
    @ManyToOne
    @JoinColumn(name = "SpecializationID", nullable = false)
    private Specialization specialization;

    @ManyToOne
    @JoinColumn(name = "InstitutionID", nullable = false)
    private MedicalInstitution institution;
}