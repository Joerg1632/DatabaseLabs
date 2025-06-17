package org.nsu.medicalsystem.service.institution;

import org.nsu.medicalsystem.entity.institution.Cabinet;
import org.nsu.medicalsystem.repository.institution.CabinetRepository;
import org.nsu.medicalsystem.repository.institution.WardRepository;
import org.nsu.medicalsystem.service.BaseService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CabinetService extends BaseService<Cabinet, Long> {

    private final CabinetRepository cabinetRepository;

    public CabinetService(CabinetRepository cabinetRepository) {
        super(cabinetRepository);
        this.cabinetRepository = cabinetRepository;
    }

}
