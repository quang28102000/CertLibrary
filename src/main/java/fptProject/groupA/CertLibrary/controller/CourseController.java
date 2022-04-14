package fptProject.groupA.CertLibrary.controller;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import fptProject.groupA.CertLibrary.persistence.Course;
import fptProject.groupA.CertLibrary.persistence.CourseDto;
import fptProject.groupA.CertLibrary.persistence.CourseEmployee;
import fptProject.groupA.CertLibrary.persistence.CourseHomePageDto;
import fptProject.groupA.CertLibrary.persistence.Skill;
import fptProject.groupA.CertLibrary.service.CourseService;
import fptProject.groupA.CertLibrary.service.EmployeeService;

@RestController
@RequestMapping("/course")
public class CourseController {

	@Autowired
	public EmployeeService employeeService;

	@Autowired
	public CourseService courseService;
	
//	OK
	@GetMapping("/coursesDto")
	public ResponseEntity<List<CourseDto>> getCoursesDto() {
		List<CourseDto> courses = courseService.getCoursesDto();
		courses.forEach(c -> System.out.println(c));
		return new ResponseEntity<List<CourseDto>>(courses, HttpStatus.OK);
	}
	
//	OK
	@GetMapping("/coursesHomePageDto")
	public ResponseEntity<List<CourseHomePageDto>> getCoursesHomePageDto() {
		List<CourseHomePageDto> courses = courseService.getCoursesHomePageDto();
		return new ResponseEntity<List<CourseHomePageDto>>(courses, HttpStatus.OK);
	}
	
//	OK
	@GetMapping("/courseSkills")
	public ResponseEntity<List<Skill>> getCourseSkills() {
		List<Skill> courses = courseService.getCourseSkills();
		return new ResponseEntity<>(courses, HttpStatus.OK);
	};
	
//	
	@PutMapping("/updateCourse")
	public ResponseEntity<String> updateCourse(@RequestBody String jsonText) 
			throws JsonMappingException, JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.readTree(jsonText);
		
		// Course
		Integer courseId = node.get("course_id").asInt();
		String courseName = node.get("course_name").asText();
		String category = node.get("category").asText();
		String platform = node.get("platform").asText();
		
		// CourseDetail
		Integer totalLength = node.get("totalLength").asInt();
		
		Course course = new Course(courseId, courseName, platform, category);
		
		// Skills
		Integer skillFlag = node.get("skillFlag").asInt();
		Integer[] skillId = StreamSupport.stream(node.get("skills").get("skill_id").spliterator(), false)
										.map(skillObj -> mapper.convertValue(skillObj, Integer.class))
										.toArray(Integer[]::new);
		String[] skillName = StreamSupport.stream(node.get("skills").get("skill_name").spliterator(), false)
										  .map(skillObj -> mapper.convertValue(skillObj, String.class))
										  .toArray(String[]::new);
		Skill[] skills = new Skill[skillId.length];
		
		for(int i = 0; i < skillId.length; i++) {
			Skill skill = new Skill(skillId[i], skillName[i]);
			skills[i] = skill;
		}
		
		courseService.updateCourse(course);
		courseService.updateCourseDetail(course, totalLength);
		courseService.deleteCourseSkill(skills, skillFlag, course);
		courseService.addCourseSkill(course, skillId, skillFlag);
		
		String noti = "";
		
		return new ResponseEntity<String>(noti, HttpStatus.OK);
	}


	@PostMapping("/addCourseRegister")
	public ResponseEntity<CourseEmployee> addCourseRegisterForEmployee
					(@RequestBody String jsonText) throws JsonMappingException, JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.readTree(jsonText);

		CourseEmployee theCourseEmployee = mapper.convertValue(node.get("courseEmployee"), CourseEmployee.class);
		courseService.addCourseEmployee(theCourseEmployee);
		return new ResponseEntity<CourseEmployee>(theCourseEmployee, HttpStatus.CREATED);
	};
	
	@PostMapping("/addCourse")
	public ResponseEntity<Course> addCourse
					(@RequestBody String jsonText) throws JsonMappingException, JsonProcessingException {	
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.readTree(jsonText);
		
		// Course
		Integer courseId = node.get("course_id").asInt();
		String tittle = node.get("tittle").asText();
		String platform = node.get("platform").asText();
		String category = node.get("category").asText();
		
		// CourseDetail
		Integer courseLength = node.get("courseLength").asInt();
		String image = node.get("image").asText();
		
		Course theCourse = new Course(courseId, tittle, platform, category);
		
		// skills
		String[] skillsName = StreamSupport.stream(node.get("skills").get("skill_name").spliterator(), false)
											.map(jsonObj -> mapper.convertValue(jsonObj, String.class))
											.toArray(String[]::new);
		Integer[] skillsId = StreamSupport
								.stream(node.get("skills").get("skill_id").spliterator(), false)
								.map(jsonObj -> mapper.convertValue(jsonObj, Integer.class))
								.toArray(Integer[]::new);
		Integer skillFlag = node.get("skill_flag").asInt();
		
		String notiCourse = courseService.addCourse(theCourse);
		String notiCourseDetail = courseService.addCourseDetail(theCourse, courseLength, image);
		String notiSkill  = courseService.addSkillOfACourse(skillsId, skillsName, skillFlag);
		String notiCourseSkill = courseService.addCourseSkill(theCourse, skillsId, skillFlag);
		
		System.out.println(notiCourse);
		System.out.println(notiCourseDetail);
		System.out.println(notiSkill);
		System.out.println(notiCourseSkill);
		
		return new ResponseEntity<Course>(theCourse, HttpStatus.CREATED);
	};
 }
