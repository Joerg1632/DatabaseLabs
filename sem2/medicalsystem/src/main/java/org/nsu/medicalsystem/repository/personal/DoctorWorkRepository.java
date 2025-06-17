package org.nsu.medicalsystem.repository.personal;

import org.nsu.medicalsystem.entity.personal.DoctorWork.DoctorWork;
import org.nsu.medicalsystem.entity.personal.DoctorWork.DoctorWorkId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorWorkRepository extends JpaRepository<DoctorWork, DoctorWorkId> {
}