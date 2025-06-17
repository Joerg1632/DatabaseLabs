package org.nsu.medicalsystem.entity.institution;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Building")
@Getter @Setter @NoArgsConstructor
public class Building {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BuildingID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "InstitutionID", nullable = false)
    private MedicalInstitution institution;

    @OneToMany(mappedBy = "building", cascade = CascadeType.ALL)
    private Set<Department> departments = new HashSet<>();
}