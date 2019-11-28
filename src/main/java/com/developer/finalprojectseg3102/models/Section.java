package com.developer.finalprojectseg3102.models;

import java.util.List;

/**
 * Created by Parastoo on 11/13/2019.
 */
public class Section {

    private long section_id;
    private String section_name;
    private Course course;
    private long course_id;
    private User professor;
    private List<User> students;
    private long professor_id;

    public Section() {
    }

    public Section(String section_name, Course course, User professor, List<User> students) {
        this.section_name = section_name;
        this.course = course;
        this.professor = professor;
        this.students = students;
    }

    public long getSection_id() {
        return section_id;
    }

    public void setSection_id(long section_id) {
        this.section_id = section_id;
    }

    public String getSection_name() {
        return section_name;
    }

    public void setSection_name(String section_name) {
        this.section_name = section_name;
    }

    public Course getCourse(){
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public long getCourse_id() {
        return course_id;
    }

    public void setCourse_id(long course_id) {
        this.course_id = course_id;
    }

    public User getProfessor(){
        return professor;
    }

    public void setProfessor(User professor) {
        this.professor = professor;
    }

    public long getProfessor_id() {
        return professor_id;
    }

    public void setProfessor_id(long professor_id) {
        this.professor_id = professor_id;
    }

    public List<User> getStudents(){
        return students;
    }

    public void setStudents(List<User> students) {
        this.students = students;
    }

}
