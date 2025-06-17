package org.nsu.medicalsystem.entity.institution;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.nsu.medicalsystem.entity.visit.Visit;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Cabinet")
@Getter @Setter @NoArgsConstructor
public class Cabinet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CabinetID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "PolyclinicID", nullable = false)
    private MedicalInstitution polyclinic;

    @Column(nullable = false, length = 10)
    private String number;

    @OneToMany(mappedBy = "cabinet")
    private Set<Visit> visits = new HashSet<>();
}