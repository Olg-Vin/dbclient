package com.example.db.entity;

import java.util.Objects;

public class Employee extends Entity{
    Object object;
    private String passport;
    private String education;
    private String fond_name;
    private String full_name;
    private String job_title;
    private String start_date;

    public Employee(String passport, String education, String fond_name, String full_name, String job_title, String start_date) {
        this.passport = passport;
        this.education = education;
        this.fond_name = fond_name;
        this.full_name = full_name;
        this.job_title = job_title;
        this.start_date = start_date;
    }

    public Employee() {

    }


    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getFond_name() {
        return fond_name;
    }

    public void setFond_name(String fond_name) {
        this.fond_name = fond_name;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(passport, employee.passport) && Objects.equals(education, employee.education) && Objects.equals(fond_name, employee.fond_name) && Objects.equals(full_name, employee.full_name) && Objects.equals(job_title, employee.job_title) && Objects.equals(start_date, employee.start_date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(passport, education, fond_name, full_name, job_title, start_date);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "passport='" + passport + '\'' +
                ", education='" + education + '\'' +
                ", fond_name='" + fond_name + '\'' +
                ", full_name='" + full_name + '\'' +
                ", job_title='" + job_title + '\'' +
                ", start_date='" + start_date + '\'' +
                '}';
    }
    @Override
    public String[] columns(){
        return new String[]{"passport", "education", "fond_name",
        "full_name", "job_title", "start_date"};
    }
}
