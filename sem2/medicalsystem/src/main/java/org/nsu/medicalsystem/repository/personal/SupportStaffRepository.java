package org.nsu.medicalsystem.repository.personal;

import org.nsu.medicalsystem.entity.personal.SupportStaff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupportStaffRepository extends JpaRepository<SupportStaff, Long> {

//2
    List<SupportStaff> findBySpecializationId(Long specializationId);

    List<SupportStaff> findBySpecializationIdAndInstitutionId(Long specializationId, Long institutionId);

    Long countBySpecializationId(Long specializationId);

    Long countBySpecializationIdAndInstitutionId(Long specializationId, Long institutionId);
}
