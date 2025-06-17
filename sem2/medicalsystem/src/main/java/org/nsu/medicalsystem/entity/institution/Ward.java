package org.nsu.medicalsystem.entity.institution;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.nsu.medicalsystem.entity.patient.HospitalStay;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Ward")
@Getter @Setter @NoArgsConstructor
public class Ward {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "WardID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "DepartmentID", nullable = false)
    private Department department;

    @Column(nullable = false)
    private Integer totalBeds;

    @OneToMany(mappedBy = "ward")
    private Set<HospitalStay> hospitalStays = new HashSet<>();
}
