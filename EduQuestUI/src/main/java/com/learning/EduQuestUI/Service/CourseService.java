package com.learning.EduQuestUI.Service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.learning.EduQuestUI.Model.Course;

@Service
public class CourseService {

     @Autowired
    private RestTemplate restTemplate;

    public List<Course> getAllCourses() {
        String url = "http://localhost:5050/EduQuest/getAllCourse";
        Course[] courses = restTemplate.getForObject(url, Course[].class);
        return Arrays.asList(courses);
    }
}
