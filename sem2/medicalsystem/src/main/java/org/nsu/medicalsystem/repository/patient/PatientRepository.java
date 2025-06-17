package org.nsu.medicalsystem.repository.patient;

import jakarta.persistence.criteria.*;
import org.nsu.medicalsystem.entity.institution.Building;
import org.nsu.medicalsystem.entity.institution.Department;
import org.nsu.medicalsystem.entity.institution.MedicalInstitution;
import org.nsu.medicalsystem.entity.institution.Ward;
import org.nsu.medicalsystem.entity.patient.HospitalStay;
import org.nsu.medicalsystem.entity.patient.Patient;
import org.nsu.medicalsystem.entity.personal.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.nsu.medicalsystem.repository.personal.DoctorRepository.entityManager;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    //6
    public default List<Object[]> findHospitalizedPatientsWithFilters(Long institutionId, Long departmentId, Long wardId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = cb.createQuery(Object[].class);

        Root<HospitalStay> hs = query.from(HospitalStay.class);
        Join<HospitalStay, Patient> p = hs.join("patient");
        Join<HospitalStay, Doctor> d = hs.join("primaryDoctor");
        Join<HospitalStay, Ward> w = hs.join("ward");
        Join<Ward, Department> dept = w.join("department");
        Join<Department, Building> b = dept.join("building");
        Join<Building, MedicalInstitution> mi = b.join("institution");

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.equal(mi.get("id"), institutionId));

        if (departmentId != null) {
            predicates.add(cb.equal(dept.get("id"), departmentId));
        }

        if (wardId != null) {
            predicates.add(cb.equal(w.get("id"), wardId));
        }

        query.multiselect(p, hs.get("admitDate"), hs.get("currentState"), hs.get("temperature"), d);
        query.where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(query).getResultList();
    }

}






