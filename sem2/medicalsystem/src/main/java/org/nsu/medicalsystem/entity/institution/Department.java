package org.nsu.medicalsystem.entity.institution;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Department")
@Getter
@Setter
@NoArgsConstructor
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DepartmentID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "BuildingID", nullable = false)
    private Building building;

    @Column(nullable = false, length = 100)
    private String specialization;

    @OneToMany(mappedBy = "department")
    private Set<Ward> wards = new HashSet<>();
}