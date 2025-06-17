package org.nsu.medicalsystem.repository.personal;

import org.antlr.v4.runtime.misc.NotNull;
import org.nsu.medicalsystem.entity.patient.Patient;
import org.nsu.medicalsystem.entity.personal.Specialization;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNullApi;
import org.springframework.stereotype.Repository;


@Repository
public interface SpecializationRepository extends JpaRepository<Specialization, Long> {

    Page<Specialization> findAll(Pageable pageable);
}
