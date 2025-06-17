-- Медицинские учреждения (больницы и поликлиники)
CREATE TABLE Employee ( --Ok
          EmployeeID INT PRIMARY KEY AUTO_INCREMENT,
          FirstName VARCHAR(100) NOT NULL,
          LastName VARCHAR(100) NOT NULL,
          PassportNumber VARCHAR(20) UNIQUE NOT NULL,
          BirthDate DATE NOT NULL,
          HireDate DATE NOT NULL,
          Salary DECIMAL(10,2) NOT NULL
);

CREATE TABLE MedicalInstitution ( --ok
        InstitutionID INT PRIMARY KEY AUTO_INCREMENT,
        Name VARCHAR(255) NOT NULL,
        Type ENUM('Больница', 'Поликлиника') NOT NULL,
        AttachedHospitalID INT NULL,
        FOREIGN KEY (AttachedHospitalID) REFERENCES MedicalInstitution(InstitutionID)
);

-- Пребывание в стационаре
CREATE TABLE HospitalStay ( --ok
          StayID INT PRIMARY KEY AUTO_INCREMENT,
          PatientID INT NOT NULL,
          WardID INT NOT NULL,
          AdmitDate DATE NOT NULL,
          DischargeDate DATE NULL,
          CurrentState TEXT,
          Temperature DECIMAL(4,2),
          PrimaryDoctorID INT NOT NULL,
          FOREIGN KEY (PatientID) REFERENCES Patient(PatientID),
          FOREIGN KEY (WardID) REFERENCES Ward(WardID),
          FOREIGN KEY (PrimaryDoctorID) REFERENCES Doctor(DoctorID)
);

-- Корпуса больниц
CREATE TABLE Building ( --ok
      BuildingID INT PRIMARY KEY AUTO_INCREMENT,
      InstitutionID INT NOT NULL,
      FOREIGN KEY (InstitutionID) REFERENCES MedicalInstitution(InstitutionID)
);

-- Отделения в корпусах
CREATE TABLE Department ( --ok
        DepartmentID INT PRIMARY KEY AUTO_INCREMENT,
        BuildingID INT NOT NULL,
        Specialization VARCHAR(100) NOT NULL,
        FOREIGN KEY (BuildingID) REFERENCES Building(BuildingID)
);

-- FreeBeds удалено, будет рассчитываться динамически
CREATE TABLE Ward ( --ok
          WardID INT PRIMARY KEY AUTO_INCREMENT,
          DepartmentID INT NOT NULL,
          TotalBeds INT NOT NULL CHECK (TotalBeds > 0),
          FOREIGN KEY (DepartmentID) REFERENCES Department(DepartmentID)
);

-- HazardCoefficient и ExtendedVacation вынесены в отдельную таблицу
CREATE TABLE Specialization ( --ok
            SpecializationID INT PRIMARY KEY AUTO_INCREMENT,
            Name VARCHAR(50) UNIQUE NOT NULL,
            CanPerformOperations BOOLEAN NOT NULL DEFAULT FALSE
);

-- Врачи (наследуют Employee)
CREATE TABLE Doctor ( --ok
        DoctorID INT PRIMARY KEY,
        SpecializationID INT NOT NULL,
        Degree ENUM('Кандидат', 'Доктор') NULL,
        Title ENUM('Доцент', 'Профессор') NULL,
        Experience INT CHECK (Experience >= 0),
        FOREIGN KEY (DoctorID) REFERENCES Employee(EmployeeID),
        FOREIGN KEY (SpecializationID) REFERENCES Specialization(SpecializationID)
);

-- WorkType заменен на автоматическое определение через MedicalInstitution.Type
CREATE TABLE DoctorWork (
        DoctorID INT NOT NULL,
        InstitutionID INT NOT NULL,
        PRIMARY KEY (DoctorID, InstitutionID),
        FOREIGN KEY (DoctorID) REFERENCES Doctor(DoctorID),
        FOREIGN KEY (InstitutionID) REFERENCES MedicalInstitution(InstitutionID)
);

-- Обслуживающий персонал (наследуют Employee)
CREATE TABLE SupportStaff ( --ok
          StaffID INT PRIMARY KEY,
          SpecializationID INT NOT NULL,
          InstitutionID INT NOT NULL,
          FOREIGN KEY (StaffID) REFERENCES Employee(EmployeeID),
          FOREIGN KEY (InstitutionID) REFERENCES MedicalInstitution(InstitutionID),
          FOREIGN KEY (SpecializationID) REFERENCES Specialization(SpecializationID)
);

-- CurrentHospitalID удален, определяется через HospitalStay
CREATE TABLE Patient ( --ok
         PatientID INT PRIMARY KEY AUTO_INCREMENT,
         FirstName VARCHAR(100) NOT NULL,
         LastName VARCHAR(100) NOT NULL,
         BirthDate DATE NOT NULL,
         Gender ENUM('М', 'Ж') NOT NULL,
         PolyclinicID INT,
         FOREIGN KEY (PolyclinicID) REFERENCES MedicalInstitution(InstitutionID)
);

-- История болезни
CREATE TABLE MedicalHistory ( --ok
        HistoryID INT PRIMARY KEY AUTO_INCREMENT,
        PatientID INT NOT NULL,
        Entry TEXT NOT NULL,
        EntryDate DATE NOT NULL,
        FOREIGN KEY (PatientID) REFERENCES Patient(PatientID)
);

CREATE TABLE Appointment ( --ok
         AppointmentID INT PRIMARY KEY AUTO_INCREMENT,
         PatientID INT NOT NULL,
         DoctorID INT NOT NULL,
         AppointmentDate DATE NOT NULL,
         FOREIGN KEY (PatientID) REFERENCES Patient(PatientID),
         FOREIGN KEY (DoctorID) REFERENCES Doctor(DoctorID)
);

-- Операции
CREATE TABLE Surgery ( --ok
         SurgeryID INT PRIMARY KEY AUTO_INCREMENT,
         PatientID INT NOT NULL,
         DoctorID INT NOT NULL,
         SurgeryDate DATE NOT NULL,
         IsFatal BOOLEAN NOT NULL DEFAULT FALSE,
         Result TEXT,
         FOREIGN KEY (PatientID) REFERENCES Patient(PatientID),
         FOREIGN KEY (DoctorID) REFERENCES Doctor(DoctorID)
);

-- Лаборатории
CREATE TABLE Laboratory ( --ok
        LabID INT PRIMARY KEY AUTO_INCREMENT,
        Name VARCHAR(255) NOT NULL
);

-- Профили лабораторий
CREATE TABLE LabSpecialization ( --ok
       LabID INT NOT NULL,
       Specialization VARCHAR(50) NOT NULL,
       PRIMARY KEY (LabID, Specialization),
       FOREIGN KEY (LabID) REFERENCES Laboratory(LabID)
);

-- Договоры с лабораториями
CREATE TABLE LabContract ( --ok
         LabID INT NOT NULL,
         InstitutionID INT NOT NULL,
         PRIMARY KEY (LabID, InstitutionID),
         FOREIGN KEY (LabID) REFERENCES Laboratory(LabID),
         FOREIGN KEY (InstitutionID) REFERENCES MedicalInstitution(InstitutionID)
);

-- Кабинеты поликлиник
CREATE TABLE Cabinet ( --ok
         CabinetID INT PRIMARY KEY AUTO_INCREMENT,
         PolyclinicID INT NOT NULL,
         Number VARCHAR(10) NOT NULL,
         FOREIGN KEY (PolyclinicID) REFERENCES MedicalInstitution(InstitutionID)
);

-- Обновленная таблица посещений с привязкой к врачу
CREATE TABLE Visit ( --ok
           VisitID INT PRIMARY KEY AUTO_INCREMENT,
           CabinetID INT NOT NULL,
           PatientID INT NOT NULL,
           DoctorID INT NOT NULL,
           VisitDate DATETIME NOT NULL,
           FOREIGN KEY (CabinetID) REFERENCES Cabinet(CabinetID),
           FOREIGN KEY (PatientID) REFERENCES Patient(PatientID),
           FOREIGN KEY (DoctorID) REFERENCES Doctor(DoctorID)
);

-- Лабораторные анализы
CREATE TABLE LabTest ( --ok
         TestID INT PRIMARY KEY AUTO_INCREMENT,
         LabID INT NOT NULL,
         PatientID INT NOT NULL,
         TestDate DATE NOT NULL,
         TestType VARCHAR(100) NOT NULL,
         FOREIGN KEY (LabID) REFERENCES Laboratory(LabID),
         FOREIGN KEY (PatientID) REFERENCES Patient(PatientID)
);

CREATE TABLE SpecializationParameter ( --ok
         SpecializationID INT NOT NULL,
         ParameterName VARCHAR(50) NOT NULL,
         ParameterValue VARCHAR(100) NOT NULL,
         PRIMARY KEY (SpecializationID, ParameterName),
         FOREIGN KEY (SpecializationID) REFERENCES Specialization(SpecializationID)
);

-- Назначения в поликлинике (многие-ко-многим)
CREATE TABLE PolyclinicDoctorAssignment ( --ok
        PatientID INT NOT NULL,
        DoctorID INT NOT NULL,
        AssignmentDate DATE NOT NULL,
        PRIMARY KEY (PatientID, DoctorID),
        FOREIGN KEY (PatientID) REFERENCES Patient(PatientID),
        FOREIGN KEY (DoctorID) REFERENCES Doctor(DoctorID)
);

-- Отпуска врачей
CREATE TABLE Vacation (
      VacationID INT PRIMARY KEY AUTO_INCREMENT,
      DoctorID INT NOT NULL,
      StartDate DATE NOT NULL,
      EndDate DATE NOT NULL,
      FOREIGN KEY (DoctorID) REFERENCES Doctor(DoctorID)
);

CREATE TABLE UserAccount (
         UserID INT PRIMARY KEY AUTO_INCREMENT,
         Username VARCHAR(50) UNIQUE NOT NULL,
         PasswordHash VARCHAR(255) NOT NULL,
         Role ENUM('ADMIN', 'DOCTOR', 'PATIENT') NOT NULL,
         EmployeeID INT,
         PatientID INT,
         FOREIGN KEY (EmployeeID) REFERENCES Employee(EmployeeID),
         FOREIGN KEY (PatientID) REFERENCES Patient(PatientID)
);
