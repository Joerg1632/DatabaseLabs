package org.nsu.medicalsystem.entity.institution;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.nsu.medicalsystem.entity.institution.LabContract.LabContract;
import org.nsu.medicalsystem.entity.institution.LabSpecialization.LabSpecialization;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Laboratory")
@Getter
@Setter
@NoArgsConstructor
public class Laboratory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LabID")
    private Long id;

    @Column(nullable = false, length = 255)
    private String name;

    @OneToMany(mappedBy = "lab")
    private Set<LabSpecialization> specializations = new HashSet<>();

    @OneToMany(mappedBy = "lab")
    private Set<LabContract> contracts = new HashSet<>();
}