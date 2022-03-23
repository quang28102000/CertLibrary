package fptProject.groupA.CertLibrary.dao;

import java.util.List;

import fptProject.groupA.CertLibrary.persistence.Course;
import fptProject.groupA.CertLibrary.persistence.CourseDto;
import fptProject.groupA.CertLibrary.persistence.CourseEmployee;
import fptProject.groupA.CertLibrary.persistence.CourseHomePageDto;
import fptProject.groupA.CertLibrary.persistence.Employee;

public interface CourseDao {
	List<Course> getAll();

	Integer numberOfCourses();

	List<CourseDto> getCoursesDto();

	List<CourseHomePageDto> getCoursesHomePageDto();

	Course addCourseForEmployee(Course course, Employee employee);

	String addCourse(Course theCourse);

	String addCourseDetail(Course theCourse, Integer courseLength, String image);

	String addSkillOfACourse(Integer[] skillsId, String[] skillsName, Integer courseFlag);	
	
	CourseEmployee addCourseEmployee(CourseEmployee courseEmployee);

	String addCourseSkill(Course theCourse, Integer[] skillsId);
	
	String deleteCourseEmployeeWithFlag(Integer courseId, Integer employeeId);
	
	String deleteCourseEmployee(Integer courseId, Integer employeeId);
	
	String updateCourseEmployee(CourseEmployee courseEmployee);
	
}
