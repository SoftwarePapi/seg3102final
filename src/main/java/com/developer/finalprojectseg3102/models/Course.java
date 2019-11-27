package com.developer.finalprojectseg3102.models;

import com.developer.finalprojectseg3102.dao.SectionDAO;

import java.util.List;

/**
 * Created by Parastoo on 11/13/2019.
 */
public class Course {

    private int course_id;
    private String course_code;
    private List<Section> sections;

    public Course(){

    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public String getCourseCode(){
        return course_code;
    }

    public void setCourseCode(String course_code){
        this.course_code = course_code;
    }

    public List<Section> getSections(int course_id) throws Exception {
        List<Section> sections = SectionDAO.retrieveSections();

        for(int i=0; i<sections.size(); i++){
            Section section = sections.get(i);
            if(section.getCourse_id() == course_id){
                sections.add(section);
            }
        }
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }
}
