package com.learning.EduQuestUI.Controller;

import java.util.List;
import com.learning.EduQuestUI.Service.CourseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.learning.EduQuestUI.Model.Course;

@Controller
@RequestMapping("/EduWeb")
public class EduQuestUIController {

    @Autowired
    public RestTemplate rest;

    @Autowired
    public CourseService courseService;

    //Getting Started (to test this method change @Controller to @Restcontroller)
    // @GetMapping("/getHi")
    // public ResponseEntity<String> getHi(){
    //       String url = "http://localhost:5050/EduQuest/coursesForInst/1" ;
    //     ResponseEntity<String> response = rest.getForEntity(url, String.class);
    //     return ResponseEntity.ok(response.getBody());        
    // }

    @GetMapping("/courses")
    public String getCourses(Model model) {
        List<Course> courses = courseService.getAllCourses();
        model.addAttribute("courses", courses);
        return "courses";  // Name of the Thymeleaf template
    }

}
