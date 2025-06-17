package org.nsu.medicalsystem.service.institution;

import org.nsu.medicalsystem.entity.institution.Ward;
import org.nsu.medicalsystem.repository.institution.WardRepository;
import org.nsu.medicalsystem.service.BaseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WardService extends BaseService<Ward, Long> {

    private final WardRepository wardRepository;

    public WardService(WardRepository wardRepository) {
        super(wardRepository);
        this.wardRepository = wardRepository;
    }

}