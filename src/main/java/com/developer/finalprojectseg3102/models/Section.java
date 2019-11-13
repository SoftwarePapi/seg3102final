package com.developer.finalprojectseg3102.models;

import java.util.List;

/**
 * Created by Parastoo on 11/13/2019.
 */
public class Section {

    private String section_name;
    private Course course;
    private User professor;
    private List<User> students;


    public Section(String section_name, Course course, User professor, List<User> students) {
        this.section_name = section_name;
        this.course = course;
        this.professor = professor;
        this.students = students;
    }

    public String getSection_name() {
        return section_name;
    }

    public void setSection_name(String section_name) {
        this.section_name = section_name;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public User getProfessor() {
        return professor;
    }

    public void setProfessor(User professor) {
        this.professor = professor;
    }

    public List<User> getStudents() {
        return students;
    }

    public void setStudents(List<User> students) {
        this.students = students;
    }
}
