package org.nsu.medicalsystem.repository.institution;

import org.nsu.medicalsystem.entity.institution.LabSpecialization.LabSpecialization;
import org.nsu.medicalsystem.entity.institution.LabSpecialization.LabSpecializationId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabSpecializationRepository extends JpaRepository<LabSpecialization, LabSpecializationId> {
}