Script for diagnoDB creation
create table DiagnosisDB(
    DIAGNOSIS_NUMBER int primary key,
    CONTROL_NUMBER INTEGER references medicaldb(CONTROL_NUMBER),
    DIAGNOSIS varchar(255)
);
