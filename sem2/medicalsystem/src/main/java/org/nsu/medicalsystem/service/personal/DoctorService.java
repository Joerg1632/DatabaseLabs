package org.nsu.medicalsystem.service.personal;

import org.nsu.medicalsystem.entity.personal.Doctor;
import org.nsu.medicalsystem.repository.personal.DoctorRepository;
import org.nsu.medicalsystem.service.BaseService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DoctorService extends BaseService<Doctor, Long> {

    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository repository) {
        super(repository);
        this.doctorRepository = repository;
    }

    public List<Doctor> getDoctorsBySpecialization(Long specializationId, Long institutionId) {
        if (institutionId == null) {
            return doctorRepository.findBySpecializationId(specializationId);
        } else {
            return doctorRepository.findBySpecializationIdAndInstitutionsId(specializationId, institutionId);
        }
    }

    public Long countDoctorsBySpecialization(Long specializationId, Long institutionId) {
        if (institutionId == null) {
            return doctorRepository.countBySpecializationId(specializationId);
        } else {
            return doctorRepository.countBySpecializationIdAndInstitutionsId(specializationId, institutionId);
        }
    }

    public List<Doctor> getDoctorsBySpecializationAndMinExperience(Long specializationId, Integer minExperience, Long institutionId) {
        if (institutionId == null) {
            return doctorRepository.findBySpecializationIdAndExperienceGreaterThanEqual(specializationId, minExperience);
        } else {
            return doctorRepository.findBySpecializationIdAndInstitutionsIdAndExperienceGreaterThanEqual(specializationId, institutionId, minExperience);
        }
    }

    public long countDoctorsBySpecializationAndMinExperience(Long specializationId, Integer minExperience, Long institutionId) {
        if (institutionId == null) {
            return doctorRepository.countBySpecializationIdAndExperienceGreaterThanEqual(specializationId, minExperience);
        } else {
            return doctorRepository.countBySpecializationIdAndInstitutionsIdAndExperienceGreaterThanEqual(specializationId, institutionId, minExperience);
        }
    }

    public Map<String, Object> getDoctorsBySpecializationAndMinOperations(Long specializationId, Long institutionId, Long minOperations) {
        List<Doctor> doctors = doctorRepository.findDoctorsBySpecializationAndMinOperations(specializationId, institutionId, minOperations);
        long count = doctorRepository.countDoctorsBySpecializationAndMinOperations(specializationId, institutionId, minOperations);

        Map<String, Object> response = new HashMap<>();
        response.put("doctors", doctors);
        response.put("count", count);
        return response;
    }

    public List<Doctor> getQualifiedDoctors(Long specId, Long institutionId) {
        return doctorRepository.findDoctorsBySpecAndDegreeAndTitle(specId, institutionId);
    }

    public long countQualifiedDoctors(Long specId, Long institutionId) {
        return doctorRepository.countDoctorsBySpecAndDegreeAndTitle(specId, institutionId);
    }

}
