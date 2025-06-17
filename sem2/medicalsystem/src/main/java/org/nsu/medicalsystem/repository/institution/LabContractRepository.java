package org.nsu.medicalsystem.repository.institution;

import org.nsu.medicalsystem.entity.institution.LabContract.LabContract;
import org.nsu.medicalsystem.entity.institution.LabContract.LabContractId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabContractRepository extends JpaRepository<LabContract, LabContractId> {
}