package org.nsu.medicalsystem.service.personal;

import org.nsu.medicalsystem.entity.personal.DoctorWork.DoctorWork;
import org.nsu.medicalsystem.entity.personal.DoctorWork.DoctorWorkId;
import org.nsu.medicalsystem.repository.personal.DoctorWorkRepository;
import org.nsu.medicalsystem.service.BaseService;
import org.springframework.stereotype.Service;

@Service
public class DoctorWorkService extends BaseService<DoctorWork, DoctorWorkId> {

    public DoctorWorkService(DoctorWorkRepository repository) {
        super(repository);
    }
}
