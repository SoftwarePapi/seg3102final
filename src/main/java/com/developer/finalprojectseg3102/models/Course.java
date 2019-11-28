package com.developer.finalprojectseg3102.models;

import java.util.List;

/**
 * Created by Parastoo on 11/13/2019.
 */
public class Course {

    private long course_id;
    private String course_code;
    private List<Section> sections;

    public Course(){

    }

    public long getCourse_id() {
        return course_id;
    }

    public void setCourse_id(long course_id) {
        this.course_id = course_id;
    }

    public String getCourseCode(){
        return course_code;
    }

    public void setCourseCode(String course_code){
        this.course_code = course_code;
    }

    public List<Section> getSections(int course_id) throws Exception {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }
}
