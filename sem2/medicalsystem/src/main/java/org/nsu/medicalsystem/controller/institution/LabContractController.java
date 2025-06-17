package org.nsu.medicalsystem.controller.institution;

import org.nsu.medicalsystem.controller.BaseController;
import org.nsu.medicalsystem.entity.institution.LabContract.LabContract;
import org.nsu.medicalsystem.entity.institution.LabContract.LabContractId;
import org.nsu.medicalsystem.service.institution.LabContractService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/lab-contracts")
public class LabContractController extends BaseController<LabContract, LabContractId> {

    public LabContractController(LabContractService service) {
        super(service);
    }
}
