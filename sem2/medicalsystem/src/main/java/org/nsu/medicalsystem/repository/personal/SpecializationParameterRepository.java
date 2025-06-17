package org.nsu.medicalsystem.repository.personal;

import org.nsu.medicalsystem.entity.personal.SpecializationParameter.SpecializationParameter;
import org.nsu.medicalsystem.entity.personal.SpecializationParameter.SpecializationParameterId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecializationParameterRepository extends JpaRepository<SpecializationParameter, SpecializationParameterId> {
}