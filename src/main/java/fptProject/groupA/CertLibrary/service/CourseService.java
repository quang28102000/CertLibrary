package fptProject.groupA.CertLibrary.service;

import java.util.List;

import fptProject.groupA.CertLibrary.persistence.Course;
import fptProject.groupA.CertLibrary.persistence.CourseDto;
import fptProject.groupA.CertLibrary.persistence.CourseEmployee;
import fptProject.groupA.CertLibrary.persistence.CourseHomePageDto;
import fptProject.groupA.CertLibrary.persistence.Employee;
import fptProject.groupA.CertLibrary.persistence.Skill;

public interface CourseService {
	List<Course> getAll();

	List<Skill> getCourseSkills();

	Integer numberOfCourses();

	List<CourseDto> getCoursesDto();

	List<CourseHomePageDto> getCoursesHomePageDto();

	Course addCourseForEmployee(Course course, Employee employee);

	CourseEmployee addCourseEmployee(CourseEmployee courseEmployee);

	String deleteCourseEmployeeWithFlag(Integer courseId, Integer employeeId);

	String deleteCourseEmployee(Integer courseId, Integer employeeId);

	String updateCourseEmployee(CourseEmployee courseEmployee);

	String addCourse(Course theCourse);

	String addSkillOfACourse(Integer[] skillsId, String[] skillsName, Integer courseFlag);

	String addCourseDetail(Course theCourse, Integer courseLength, String image);

	String addCourseSkill(Course theCourse, Integer[] skillsId, Integer flag);

	String updateCourse(Course course);

	String updateCourseDetail(Course course, Integer courseLength);

	String deleteCourseSkill (Skill[] skills, Integer flag, Course course);
}
