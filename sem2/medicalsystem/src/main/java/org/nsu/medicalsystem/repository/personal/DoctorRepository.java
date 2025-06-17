package org.nsu.medicalsystem.repository.personal;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.nsu.medicalsystem.entity.institution.MedicalInstitution;
import org.nsu.medicalsystem.entity.patient.HospitalStay;
import org.nsu.medicalsystem.entity.personal.Doctor;
import org.nsu.medicalsystem.entity.procedure.Surgery;
import org.nsu.medicalsystem.entity.visit.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

//1
    List<Doctor> findBySpecializationId(Long specializationId);

    List<Doctor> findBySpecializationIdAndInstitutionsId(Long specializationId, Long institutionId);

    Long countBySpecializationId(Long specializationId);

    Long countBySpecializationIdAndInstitutionsId(Long specializationId, Long institutionId);


//2
    List<Doctor> findBySpecializationIdAndExperienceGreaterThanEqual(Long specializationId, int minExperience);

    List<Doctor> findBySpecializationIdAndInstitutionsIdAndExperienceGreaterThanEqual(
            Long specializationId, Long institutionId, int minExperience);

    long countBySpecializationIdAndExperienceGreaterThanEqual(Long specializationId, int minExperience);

    long countBySpecializationIdAndInstitutionsIdAndExperienceGreaterThanEqual(
            Long specializationId, Long institutionId, int minExperience);

//3
    @PersistenceContext
    EntityManager entityManager = null;

    default List<Doctor> findDoctorsBySpecializationAndMinOperations(Long specializationId, Long institutionId, Long minOperations) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Doctor> query = cb.createQuery(Doctor.class);
        Root<Doctor> doctor = query.from(Doctor.class);

        Join<Doctor, MedicalInstitution> institutionJoin = doctor.join("institutions");
        Join<Doctor, Surgery> surgeryJoin = doctor.join("surgeries", JoinType.LEFT);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.equal(doctor.get("specialization").get("id"), specializationId));

        if (institutionId != null) {
            predicates.add(cb.equal(institutionJoin.get("id"), institutionId));
        }

        query.groupBy(doctor);
        query.having(cb.greaterThanOrEqualTo(cb.count(surgeryJoin.get("id")), minOperations));
        query.select(doctor).where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(query).getResultList();
    }

    default long countDoctorsBySpecializationAndMinOperations(Long specializationId, Long institutionId, Long minOperations) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = cb.createQuery(Long.class);
        Root<Doctor> doctor = query.from(Doctor.class);

        Join<Doctor, MedicalInstitution> institutionJoin = doctor.join("institutions");
        Join<Doctor, Surgery> surgeryJoin = doctor.join("surgeries", JoinType.LEFT);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.equal(doctor.get("specialization").get("id"), specializationId));
        if (institutionId != null) {
            predicates.add(cb.equal(institutionJoin.get("id"), institutionId));
        }

        query.groupBy(doctor);
        query.having(cb.greaterThanOrEqualTo(cb.count(surgeryJoin.get("id")), minOperations));
        query.select(cb.count(doctor)).where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(query).getSingleResult();
    }
//5
    default List<Doctor> findDoctorsBySpecAndDegreeAndTitle(Long specializationId, Long institutionId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Doctor> query = cb.createQuery(Doctor.class);
        Root<Doctor> doctor = query.from(Doctor.class);

        Join<Object, Object> institutionsJoin = doctor.join("institutions");

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.equal(doctor.get("specialization").get("id"), specializationId));
        predicates.add(doctor.get("academicDegree").in("Кандидат медицинских наук", "Доктор медицинских наук"));
        predicates.add(doctor.get("academicTitle").in("Доцент", "Профессор"));

        if (institutionId != null) {
            predicates.add(cb.equal(institutionsJoin.get("id"), institutionId));
        }

        query.select(doctor).where(predicates.toArray(new Predicate[0])).distinct(true);

        return entityManager.createQuery(query).getResultList();
    }

    default long countDoctorsBySpecAndDegreeAndTitle(Long specializationId, Long institutionId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = cb.createQuery(Long.class);
        Root<Doctor> doctor = query.from(Doctor.class);

        Join<Object, Object> institutionsJoin = doctor.join("institutions");

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.equal(doctor.get("specialization").get("id"), specializationId));
        predicates.add(doctor.get("academicDegree").in("Кандидат", "Доктор"));
        predicates.add(doctor.get("academicTitle").in("Доцент", "Профессор"));

        if (institutionId != null) {
            predicates.add(cb.equal(institutionsJoin.get("id"), institutionId));
        }

        query.select(cb.countDistinct(doctor)).where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(query).getSingleResult();
    }

}
