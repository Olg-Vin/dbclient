package com.example.db.entity;

import java.util.Objects;


public class Employee extends Entity{
    private String pk_employee_passport;
    private String education;
    private String fk_fond_name;
    private String full_name;
    private String job_title;
    private String start_date;

    public Employee(String passport, String full_name, String job_title, String start_date, String education, String fond_name) {
        this.pk_employee_passport = passport;
        this.education = education;
        this.fk_fond_name = fond_name;
        this.full_name = full_name;
        this.job_title = job_title;
        this.start_date = start_date;
    }

    public Employee() {

    }


    public String getPk_employee_passport() {
        return pk_employee_passport;
    }

    public void setPk_employee_passport(String pk_employee_passport) {
        this.pk_employee_passport = pk_employee_passport;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getFk_fond_name() {
        return fk_fond_name;
    }

    public void setFk_fond_name(String fk_fond_name) {
        this.fk_fond_name = fk_fond_name;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getJob_title() {
        return job_title;
    }

    public void setJob_title(String job_title) {
        this.job_title = job_title;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "pk_employee_passport='" + pk_employee_passport + '\'' +
                ", education='" + education + '\'' +
                ", fk_fond_name='" + fk_fond_name + '\'' +
                ", full_name='" + full_name + '\'' +
                ", job_title='" + job_title + '\'' +
                ", start_date='" + start_date + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(pk_employee_passport, employee.pk_employee_passport) && Objects.equals(education, employee.education) && Objects.equals(fk_fond_name, employee.fk_fond_name) && Objects.equals(full_name, employee.full_name) && Objects.equals(job_title, employee.job_title) && Objects.equals(start_date, employee.start_date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pk_employee_passport, education, fk_fond_name, full_name, job_title, start_date);
    }

    @Override
    public String[] columns(){
        return new String[]{"passport", "full_name", "fond_name",
        "job_title", "education", "start_date"};
    }
}
