package org.nsu.medicalsystem.repository.institution;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import org.nsu.medicalsystem.entity.institution.Building;
import org.nsu.medicalsystem.entity.institution.Department;
import org.nsu.medicalsystem.entity.institution.MedicalInstitution;
import org.nsu.medicalsystem.entity.institution.Ward;
import org.nsu.medicalsystem.entity.patient.HospitalStay;
import org.springframework.data.jpa.repository.JpaRepository;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Subquery;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface WardRepository extends JpaRepository<Ward, Long> {

}
