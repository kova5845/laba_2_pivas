/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laba2;

/**
 *
 * @author alexey
 */
public class Personal {
    String faculty;
    String department;
    String name;
    String academicRank;
    String academicDegree;
    int experience;
    
    public Personal(){
        
    }

    public Personal(String faculty, String department, String name, String academicRank, String academicDegree, int experience) {
        this.faculty = faculty;
        this.department = department;
        this.name = name;
        this.academicRank = academicRank;
        this.academicDegree = academicDegree;
        this.experience = experience;
    }

    public String getAcademicDegree() {
        return academicDegree;
    }

    public String getAcademicRank() {
        return academicRank;
    }

    public String getDepartment() {
        return department;
    }

    public String getFaculty() {
        return faculty;
    }

    public int getExperience() {
        return experience;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setAcademicRank(String academicRank) {
        this.academicRank = academicRank;
    }

    public void setAcademicDegree(String academicDegree) {
        this.academicDegree = academicDegree;
    }
}
