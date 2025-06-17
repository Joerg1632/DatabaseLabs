package org.nsu.medicalsystem.repository.institution;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.nsu.medicalsystem.entity.institution.Cabinet;
import org.nsu.medicalsystem.entity.institution.MedicalInstitution;
import org.nsu.medicalsystem.entity.visit.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CabinetRepository extends JpaRepository<Cabinet, Long> {


}