/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laba2;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author alexey
 */
public class Personal {

    private SimpleStringProperty faculty;
    private SimpleStringProperty department;
    private SimpleStringProperty name;
    private SimpleStringProperty surname;
    private SimpleStringProperty patronymic;
    private SimpleStringProperty academicRank;
    private SimpleStringProperty academicDegree;
    private SimpleIntegerProperty experience;

    public Personal() {

    }

    public Personal(String faculty, String department, String name,
            String surname, String patronymic, String academicRank,
            String academicDegree, Integer experience) {
        this.faculty = new SimpleStringProperty(faculty);
        this.department = new SimpleStringProperty(department);
        this.name = new SimpleStringProperty(name);
        this.surname = new SimpleStringProperty(surname);
        this.patronymic = new SimpleStringProperty(patronymic);
        this.academicRank = new SimpleStringProperty(academicRank);
        this.academicDegree = new SimpleStringProperty(academicDegree);
        this.experience = new SimpleIntegerProperty(experience);
    }

    public String getAcademicDegree() {
        return academicDegree.get();
    }

    public String getAcademicRank() {
        return academicRank.get();
    }

    public String getDepartment() {
        return department.get();
    }

    public Integer getExperience() {
        return experience.get();
    }

    public String getFaculty() {
        return faculty.get();
    }

    public String getName() {
        return name.get();
    }

    public String getPatronymic() {
        return patronymic.get();
    }

    public String getSurname() {
        return surname.get();
    }

    public void setAcademicDegree(String academicDegree) {
        this.academicDegree.set(academicDegree);
    }

    public void setAcademicRank(String academicRank) {
        this.academicRank.set(academicRank);
    }

    public void setDepartment(String department) {
        this.department.set(department);
    }

    public void setExperience(int experience) {
        this.experience.set(experience);
    }

    public void setFaculty(String faculty) {
        this.faculty.set(faculty);
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setPatronymic(String patronymic) {
        this.patronymic.set(patronymic);
    }

    public void setSurname(String surname) {
        this.surname.set(surname);
    }

}
