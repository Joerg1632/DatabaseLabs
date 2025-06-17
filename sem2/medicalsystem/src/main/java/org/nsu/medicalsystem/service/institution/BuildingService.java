package org.nsu.medicalsystem.service.institution;

import org.nsu.medicalsystem.entity.institution.Building;
import org.nsu.medicalsystem.repository.institution.BuildingRepository;
import org.nsu.medicalsystem.service.BaseService;
import org.springframework.stereotype.Service;

@Service
public class BuildingService extends BaseService<Building, Long> {

    public BuildingService(BuildingRepository repository) {
        super(repository);
    }
}
