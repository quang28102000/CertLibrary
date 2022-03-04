package org.o7planning.sbhibernate.controller;

import java.util.List;

import org.o7planning.sbhibernate.presistence.CourseDto;
import org.o7planning.sbhibernate.presistence.InforPageDto;
import org.o7planning.sbhibernate.presistence.courseRegisterDto;
import org.o7planning.sbhibernate.service.CourseService;
import org.o7planning.sbhibernate.service.InforPageService;
import org.o7planning.sbhibernate.service.addService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/course")
public class addPageController {
	
	@Autowired
	public addService addService;
	
	@Autowired
	public CourseService courseService;

	@PostMapping("/addCourseRegister")
    public ResponseEntity<courseRegisterDto> addEmployee(@RequestBody courseRegisterDto CourseRegisterDto) {
    	courseRegisterDto dto = addService.addCourseRegis(CourseRegisterDto);
//    	System.out.println(CourseRegisterDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
    
    @PostMapping("/addCourse")
    public ResponseEntity<CourseDto> addCourse(@RequestBody CourseDto courseDto) {
    	CourseDto dto = courseService.addCourse(courseDto);
//    	System.out.println(courseDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
}
