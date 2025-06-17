//package org.nsu.medicalsystem.entity;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import java.io.Serializable;
//import java.math.BigDecimal;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.HashSet;
//import java.util.Set;
//
//@Entity
//@Table(name = "Building")
//@Getter @Setter @NoArgsConstructor
//public class Building {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "BuildingID")
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "InstitutionID", nullable = false)
//    private MedicalInstitution institution;
//
//    @OneToMany(mappedBy = "building", cascade = CascadeType.ALL)
//    private Set<Department> departments = new HashSet<>();
//}
//@Entity
//@Table(name = "Cabinet")
//@Getter @Setter @NoArgsConstructor
//public class Cabinet {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "CabinetID")
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "PolyclinicID", nullable = false)
//    private MedicalInstitution polyclinic;
//
//    @Column(nullable = false, length = 10)
//    private String number;
//
//    @OneToMany(mappedBy = "cabinet")
//    private Set<Visit> visits = new HashSet<>();
//}
//@Entity
//@Table(name = "Department")
//@Getter
//@Setter
//@NoArgsConstructor
//public class Department {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "DepartmentID")
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "BuildingID", nullable = false)
//    private Building building;
//
//    @Column(nullable = false, length = 100)
//    private String specialization;
//
//    @OneToMany(mappedBy = "department")
//    private Set<Ward> wards = new HashSet<>();
//}
//@Entity
//@Table(name = "Doctor")
//@PrimaryKeyJoinColumn(name = "DoctorID")
//@Getter
//@Setter
//@NoArgsConstructor
//public class Doctor extends Employee {
//    @ManyToOne
//    @JoinColumn(name = "SpecializationID", nullable = false)
//    private Specialization specialization;
//
//    @Enumerated(EnumType.STRING)
//    private Degree degree;
//
//    @Enumerated(EnumType.STRING)
//    private Title title;
//
//    private Integer experience;
//
//    @ManyToMany
//    @JoinTable(name = "DoctorWork",
//            joinColumns = @JoinColumn(name = "DoctorID"),
//            inverseJoinColumns = @JoinColumn(name = "InstitutionID"))
//    private Set<MedicalInstitution> institutions = new HashSet<>();
//
//    public enum Degree {
//        Кандидат, Доктор
//    }
//
//    public enum Title {
//        Доцент, Профессор
//    }
//}
//@Entity
//@Table(name = "Employee")
//@Inheritance(strategy = InheritanceType.JOINED)
//@Getter
//@Setter
//@NoArgsConstructor
//public class Employee {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "EmployeeID")
//    private Long id;
//
//    @Column(nullable = false, length = 100)
//    private String firstName;
//
//    @Column(nullable = false, length = 100)
//    private String lastName;
//
//    @Column(nullable = false, unique = true, length = 20)
//    private String passportNumber;
//
//    @Column(nullable = false)
//    private LocalDate birthDate;
//
//    @Column(nullable = false)
//    private LocalDate hireDate;
//
//    @Column(nullable = false, precision = 10, scale = 2)
//    private BigDecimal salary;
//}
//@Entity
//@Table(name = "HospitalStay")
//@Getter
//@Setter
//@NoArgsConstructor
//public class HospitalStay {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "StayID")
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "PatientID", nullable = false)
//    private Patient patient;
//
//    @ManyToOne
//    @JoinColumn(name = "WardID", nullable = false)
//    private Ward ward;
//
//    @Column(nullable = false)
//    private LocalDate admitDate;
//
//    private LocalDate dischargeDate;
//
//    @Lob
//    private String currentState;
//
//    @Column(precision = 4, scale = 2)
//    private BigDecimal temperature;
//
//    @ManyToOne
//    @JoinColumn(name = "PrimaryDoctorID", nullable = false)
//    private Doctor primaryDoctor;
//}
//@Entity
//@Table(name = "LabContract")
//@Getter @Setter @NoArgsConstructor
//public class LabContract {
//    @EmbeddedId
//    private LabContractId id;
//
//    @ManyToOne
//    @MapsId("labId")
//    @JoinColumn(name = "LabID")
//    private Laboratory lab;
//
//    @ManyToOne
//    @MapsId("institutionId")
//    @JoinColumn(name = "InstitutionID")
//    private MedicalInstitution institution;
//}
//@Entity
//@Table(name = "Laboratory")
//@Getter
//@Setter
//@NoArgsConstructor
//public class Laboratory {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "LabID")
//    private Long id;
//
//    @Column(nullable = false, length = 255)
//    private String name;
//
//    @OneToMany(mappedBy = "lab")
//    private Set<LabSpecialization> specializations = new HashSet<>();
//
//    @OneToMany(mappedBy = "lab")
//    private Set<LabContract> contracts = new HashSet<>();
//}
//@Embeddable
//@Getter @Setter @NoArgsConstructor
//public class LabContractId implements Serializable {
//    @Column(name = "LabID")
//    private Long labId;
//
//    @Column(name = "InstitutionID")
//    private Long institutionId;
//}
//@Entity
//@Table(name = "LabSpecialization")
//@Getter @Setter @NoArgsConstructor
//public class LabSpecialization {
//    @EmbeddedId
//    private LabSpecializationId id;
//
//    @ManyToOne
//    @MapsId("labId")
//    @JoinColumn(name = "LabID")
//    private Laboratory lab;
//}
//@Embeddable
//@Getter
//@Setter
//@NoArgsConstructor
//public class LabSpecializationId implements Serializable {
//    @Column(name = "LabID")
//    private Long labId;
//
//    @Column(name = "Specialization", length = 50)
//    private String specialization;
//}
//@Entity
//@Table(name = "LabTest")
//@Getter @Setter @NoArgsConstructor
//public class LabTest {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "TestID")
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "LabID", nullable = false)
//    private Laboratory lab;
//
//    @ManyToOne
//    @JoinColumn(name = "PatientID", nullable = false)
//    private Patient patient;
//
//    @Column(nullable = false)
//    private LocalDate testDate;
//
//    @Column(nullable = false, length = 100)
//    private String testType;
//}
//@Entity
//@Table(name = "MedicalInstitution")
//@Getter
//@Setter
//@NoArgsConstructor
//public class MedicalInstitution {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "InstitutionID")
//    private Long id;
//
//    @Column(nullable = false, length = 255)
//    private String name;
//
//    @Enumerated(EnumType.STRING)
//    @Column(nullable = false, length = 20)
//    private InstitutionType type;
//
//    @ManyToOne
//    @JoinColumn(name = "AttachedHospitalID")
//    private MedicalInstitution attachedHospital;
//
//    public enum InstitutionType {
//        Больница, Поликлиника
//    }
//}
//@Entity
//@Table(name = "Patient")
//@Getter
//@Setter
//@NoArgsConstructor
//public class Patient {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "PatientID")
//    private Long id;
//
//    @Column(nullable = false, length = 100)
//    private String firstName;
//
//    @Column(nullable = false, length = 100)
//    private String lastName;
//
//    @Column(nullable = false)
//    private LocalDate birthDate;
//
//    @Enumerated(EnumType.STRING)
//    @Column(nullable = false, length = 1)
//    private Gender gender;
//
//    @ManyToOne
//    @JoinColumn(name = "PolyclinicID")
//    private MedicalInstitution polyclinic;
//
//    @OneToMany(mappedBy = "patient")
//    private Set<HospitalStay> hospitalStays = new HashSet<>();
//
//    public enum Gender {
//        М, Ж
//    }
//}
//@Entity
//@Table(name = "Specialization")
//@Getter
//@Setter
//@NoArgsConstructor
//public class Specialization {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "SpecializationID")
//    private Long id;
//
//    @Column(nullable = false, unique = true, length = 50)
//    private String name;
//
//    @Column(nullable = false)
//    private Boolean canPerformOperations = false;
//}
//@Entity
//@Table(name = "SupportStaff")
//@PrimaryKeyJoinColumn(name = "StaffID")
//@Getter
//@Setter
//@NoArgsConstructor
//public class SupportStaff extends Employee {
//    @Column(nullable = false, length = 50)
//    private String position;
//
//    @ManyToOne
//    @JoinColumn(name = "InstitutionID", nullable = false)
//    private MedicalInstitution institution;
//}
//@Entity
//@Table(name = "Surgery")
//@Getter
//@Setter
//@NoArgsConstructor
//public class Surgery {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "SurgeryID")
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "PatientID", nullable = false)
//    private Patient patient;
//
//    @ManyToOne
//    @JoinColumn(name = "DoctorID", nullable = false)
//    private Doctor doctor;
//
//    @Column(nullable = false)
//    private LocalDate surgeryDate;
//
//    @Column(nullable = false)
//    private Boolean isFatal = false;
//
//    @Lob
//    private String result;
//}
//@Entity
//@Table(name = "UserAccount")
//@Getter
//@Setter
//@NoArgsConstructor
//public class UserAccount {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "UserID")
//    private Long id;
//
//    @Column(nullable = false, unique = true, length = 50)
//    private String username;
//
//    @Column(nullable = false, length = 255)
//    private String passwordHash;
//
//    @Enumerated(EnumType.STRING)
//    @Column(nullable = false, length = 20)
//    private Role role;
//
//    @ManyToOne
//    @JoinColumn(name = "EmployeeID")
//    private Employee employee;
//
//    @ManyToOne
//    @JoinColumn(name = "PatientID")
//    private Patient patient;
//
//    public enum Role {
//        ADMIN, DOCTOR, PATIENT
//    }
//}
//@Entity
//@Table(name = "Visit")
//@Getter @Setter @NoArgsConstructor
//public class Visit {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "VisitID")
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "CabinetID", nullable = false)
//    private Cabinet cabinet;
//
//    @ManyToOne
//    @JoinColumn(name = "PatientID", nullable = false)
//    private Patient patient;
//
//    @ManyToOne
//    @JoinColumn(name = "DoctorID", nullable = false)
//    private Doctor doctor;
//
//    @Column(nullable = false)
//    private LocalDateTime visitDate;
//}
//@Entity
//@Table(name = "Ward")
//@Getter @Setter @NoArgsConstructor
//public class Ward {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "WardID")
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "DepartmentID", nullable = false)
//    private Department department;
//
//    @Column(nullable = false)
//    private Integer totalBeds;
//
//    @OneToMany(mappedBy = "ward")
//    private Set<HospitalStay> hospitalStays = new HashSet<>();
//}
//@Entity
//@Table(name = "Appointment")
//@Getter @Setter @NoArgsConstructor
//public class Appointment {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "AppointmentID")
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "PatientID", nullable = false)
//    private Patient patient;
//
//    @ManyToOne
//    @JoinColumn(name = "DoctorID", nullable = false)
//    private Doctor doctor;
//
//    @Column(nullable = false)
//    private LocalDate appointmentDate;
//}
//@Embeddable
//@Getter @Setter @NoArgsConstructor
//class DoctorWorkId implements Serializable {
//    @Column(name = "DoctorID")
//    private Long doctorId;
//
//    @Column(name = "InstitutionID")
//    private Long institutionId;
//}
//
//@Entity
//@Table(name = "DoctorWork")
//@Getter @Setter @NoArgsConstructor
//public class DoctorWork {
//    @EmbeddedId
//    private DoctorWorkId id;
//
//    @ManyToOne
//    @MapsId("doctorId")
//    @JoinColumn(name = "DoctorID", nullable = false)
//    private Doctor doctor;
//
//    @ManyToOne
//    @MapsId("institutionId")
//    @JoinColumn(name = "InstitutionID", nullable = false)
//    private MedicalInstitution institution;
//}
//@Entity
//@Table(name = "MedicalHistory")
//@Getter @Setter @NoArgsConstructor
//public class MedicalHistory {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "HistoryID")
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "PatientID", nullable = false)
//    private Patient patient;
//
//    @Lob
//    @Column(nullable = false)
//    private String entry;
//
//    @Column(nullable = false)
//    private LocalDate entryDate;
//}
//@Entity
//@Table(name = "PolyclinicDoctorAssignment")
//@Getter @Setter @NoArgsConstructor
//public class PolyclinicDoctorAssignment {
//    @EmbeddedId
//    private PolyclinicDoctorAssignmentId id;
//
//    @ManyToOne
//    @MapsId("patientId")
//    @JoinColumn(name = "PatientID", nullable = false)
//    private Patient patient;
//
//    @ManyToOne
//    @MapsId("doctorId")
//    @JoinColumn(name = "DoctorID", nullable = false)
//    private Doctor doctor;
//
//    @Column(nullable = false)
//    private LocalDate assignmentDate;
//}
//public class PolyclinicDoctorAssignmentId implements Serializable {
//    @Column(name = "PatientID")
//    private Long patientId;
//
//    @Column(name = "DoctorID")
//    private Long doctorId;
//}
//@Entity
//@Table(name = "SpecializationParameter")
//@Getter @Setter @NoArgsConstructor
//public class SpecializationParameter {
//    @EmbeddedId
//    private SpecializationParameterId id;
//
//    @Column(nullable = false, length = 100)
//    private String parameterValue;
//
//    @ManyToOne
//    @MapsId("specializationId")
//    @JoinColumn(name = "SpecializationID", nullable = false)
//    private Specialization specialization;
//}
//@Embeddable
//@Getter @Setter @NoArgsConstructor
//public class SpecializationParameterId implements Serializable {
//    @Column(name = "SpecializationID")
//    private Long specializationId;
//
//    @Column(name = "ParameterName", length = 50)
//    private String parameterName;
//}
//@Entity
//@Table(name = "Vacation")
//@Getter @Setter @NoArgsConstructor
//public class Vacation {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "VacationID")
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "DoctorID", nullable = false)
//    private Doctor doctor;
//
//    @Column(nullable = false)
//    private LocalDate startDate;
//
//    @Column(nullable = false)
//    private LocalDate endDate;
//}