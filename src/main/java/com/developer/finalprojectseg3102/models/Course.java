package com.developer.finalprojectseg3102.models;

import java.util.List;

/**
 * Created by Parastoo on 11/13/2019.
 */
public class Course {

    private String course_code;
    private List<Section> sections;

    public Course(){

    }

    public String getCourseCode(){
        return course_code;
    }

    public void setCourseCode(String course_code){
        this.course_code = course_code;
    }
}
