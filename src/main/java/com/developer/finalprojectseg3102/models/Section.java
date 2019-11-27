package com.developer.finalprojectseg3102.models;

import java.util.List;

/**
 * Created by Parastoo on 11/13/2019.
 */
public class Section {

    private int section_id;
    private String section_name;
    private Course course;
    private int course_id;
    private User professor;
    private List<User> students;
    private int professor_id;

    public Section() {
    }

    public Section(String section_name, Course course, User professor, List<User> students) {
        this.section_name = section_name;
        this.course = course;
        this.professor = professor;
        this.students = students;
    }

    public int getSection_id() {
        return section_id;
    }

    public void setSection_id(int section_id) {
        this.section_id = section_id;
    }

    public String getSection_name() {
        return section_name;
    }

    public void setSection_name(String section_name) {
        this.section_name = section_name;
    }

    public Course getCourse() throws Exception {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public User getProfessor() throws Exception {
        return professor;
    }

    public void setProfessor(User professor) {
        this.professor = professor;
    }

    public int getProfessor_id() {
        return professor_id;
    }

    public void setProfessor_id(int professor_id) {
        this.professor_id = professor_id;
    }

    public List<User> getStudents() throws Exception {
        return students;
    }

    public void setStudents(List<User> students) {
        this.students = students;
    }
    
}
