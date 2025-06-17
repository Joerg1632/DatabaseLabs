package org.nsu.medicalsystem.service.personal;

import org.nsu.medicalsystem.entity.personal.Doctor;
import org.nsu.medicalsystem.entity.personal.SupportStaff;
import org.nsu.medicalsystem.repository.personal.SupportStaffRepository;
import org.nsu.medicalsystem.service.BaseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupportStaffService extends BaseService<SupportStaff, Long> {

    private final SupportStaffRepository supportStaffRepository;

    public SupportStaffService(SupportStaffRepository repository) {
        super(repository);
        this.supportStaffRepository = repository;
    }

    public List<SupportStaff> getStaffBySpecialization(Long specializationId, Long institutionId) {
        if (institutionId == null) {
            return supportStaffRepository.findBySpecializationId(specializationId);
        } else {
            return supportStaffRepository.findBySpecializationIdAndInstitutionId(specializationId, institutionId);
        }
    }

    public Long countStaffBySpecialization(Long specializationId, Long institutionId) {
        if (institutionId == null) {
            return supportStaffRepository.countBySpecializationId(specializationId);
        } else {
            return supportStaffRepository.countBySpecializationIdAndInstitutionId(specializationId, institutionId);
        }
    }
}