package org.nsu.medicalsystem.service.institution;

import org.nsu.medicalsystem.entity.institution.LabContract.LabContract;
import org.nsu.medicalsystem.entity.institution.LabContract.LabContractId;
import org.nsu.medicalsystem.repository.institution.LabContractRepository;
import org.nsu.medicalsystem.service.BaseService;
import org.springframework.stereotype.Service;

@Service
public class LabContractService extends BaseService<LabContract, LabContractId> {

    public LabContractService(LabContractRepository repository) {
        super(repository);
    }
}
