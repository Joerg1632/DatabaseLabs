package org.nsu.medicalsystem.repository.institution;

import org.nsu.medicalsystem.entity.institution.MedicalInstitution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalInstitutionRepository extends JpaRepository<MedicalInstitution, Long> {
}