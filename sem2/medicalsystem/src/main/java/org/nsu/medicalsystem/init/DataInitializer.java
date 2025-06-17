//package org.nsu.medicalsystem.init;
//
//import lombok.RequiredArgsConstructor;
//import org.nsu.medicalsystem.entity.institution.*;
//import org.nsu.medicalsystem.entity.patient.*;
//import org.nsu.medicalsystem.entity.personal.Doctor;
//import org.nsu.medicalsystem.repository.institution.BuildingRepository;
//import org.nsu.medicalsystem.repository.institution.DepartmentRepository;
//import org.nsu.medicalsystem.repository.institution.MedicalInstitutionRepository;
//import org.nsu.medicalsystem.repository.institution.WardRepository;
//import org.nsu.medicalsystem.repository.patient.HospitalStayRepository;
//import org.nsu.medicalsystem.repository.patient.PatientRepository;
//import org.nsu.medicalsystem.repository.personal.DoctorRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.math.BigDecimal;
//import java.time.LocalDate;
//
//@Component
//@RequiredArgsConstructor
//public class DataInitializer implements CommandLineRunner {
//
//    private final MedicalInstitutionRepository institutionRepo;
//    private final BuildingRepository buildingRepo;
//    private final DepartmentRepository departmentRepo;
//    private final WardRepository wardRepo;
//    private final DoctorRepository doctorRepo;
//    private final PatientRepository patientRepo;
//    private final HospitalStayRepository stayRepo;
//
//    @Override
//    public void run(String... args) {
//        // Чтобы не дублировать при каждом запуске
//        if (institutionRepo.count() > 0) return;
//
//        // Пример инициализации
//        MedicalInstitution inst1 = new MedicalInstitution();
//        inst1.setName("Городская больница №1");
//        inst1.setType(MedicalInstitution.InstitutionType.valueOf("Больница"));
//        institutionRepo.save(inst1);
//
//        MedicalInstitution inst2 = new MedicalInstitution();
//        inst2.setName("Городская больница №2");
//        inst2.setType(MedicalInstitution.InstitutionType.valueOf("Больница"));
//        institutionRepo.save(inst2);
//
//        Building building1 = new Building();
//        building1.setInstitution(inst1);
//        buildingRepo.save(building1);
//
//        Building building2 = new Building();
//        building2.setInstitution(inst2);
//        buildingRepo.save(building2);
//
//        Department department = new Department();
//        department.setBuilding(building1);
//        department.setSpecialization("Терапия");
//        departmentRepo.save(department);
//
//        Department department2 = new Department();
//        department2.setBuilding(building1);
//        department2.setSpecialization("Хирургия");
//        departmentRepo.save(department2);
//
//        Ward ward1 = new Ward();
//        ward1.setDepartment(department);
//        ward1.setTotalBeds(10);
//        wardRepo.save(ward1);
//
//        Ward ward2 = new Ward();
//        ward1.setDepartment(department);
//        ward1.setTotalBeds(15);
//        wardRepo.save(ward1);
//
//        Ward ward3 = new Ward();
//        ward3.setDepartment(department2);
//        ward3.setTotalBeds(20);
//        wardRepo.save(ward3);
//
//        Doctor doctor = new Doctor();
//        doctor.setFirstName("Иван");
//        doctor.setLastName("Иванов");
//        doctor.setBirthDate(LocalDate.of(1985, 5, 12));
//        doctor.setHireDate(LocalDate.of(2020, 3, 10));
//        doctor.setInstitutions(inst1);
//        doctor.setSpecialization("Терапевт"); // если есть своё поле
//        doctorRepo.save(doctor);
//
//        Patient patient = new Patient();
//        patient.setFirstName("Алексей");
//        patient.setLastName("Петров");
//        patientRepo.save(patient);
//
//        HospitalStay stay = new HospitalStay();
//        stay.setPatient(patient);
//        stay.setWard(ward);
//        stay.setPrimaryDoctor(doctor);
//        stay.setAdmitDate(LocalDate.now());
//        stay.setCurrentState("Стабильное");
//        stay.setTemperature(BigDecimal.valueOf(36.8));
//        stayRepo.save(stay);
//    }
//}
