package fptProject.groupA.CertLibrary.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fptProject.groupA.CertLibrary.dao.CourseDao;
import fptProject.groupA.CertLibrary.persistence.Course;
import fptProject.groupA.CertLibrary.persistence.CourseDto;
import fptProject.groupA.CertLibrary.persistence.CourseEmployee;
import fptProject.groupA.CertLibrary.persistence.CourseHomePageDto;
import fptProject.groupA.CertLibrary.persistence.Employee;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseDao courseDao;

	@Override
	@Transactional
	public List<Course> getAll() {
		return courseDao.getAll();
	}

	@Override
	@Transactional
	public Integer numberOfCourses() {
		return courseDao.numberOfCourses();
	}

	@Override
	public List<CourseDto> getCoursesDto() {
		return courseDao.getCoursesDto();
	}

	@Override
	public List<CourseHomePageDto> getCoursesHomePageDto() {
		return courseDao.getCoursesHomePageDto();
	}

	@Override
	public Course addCourseForEmployee(Course course, Employee employee) {
		return courseDao.addCourseForEmployee(course, employee);
	}

	@Override
	public CourseEmployee addCourseEmployee(CourseEmployee courseEmployee) {
		return courseDao.addCourseEmployee(courseEmployee);
	}

	@Override
	public String deleteCourseEmployeeWithFlag(Integer courseId, Integer employeeId) {
		return courseDao.deleteCourseEmployeeWithFlag(courseId, employeeId);
	}

	@Override
	public String updateCourseEmployee(CourseEmployee courseEmployee) {
		return courseDao.updateCourseEmployee(courseEmployee);
	}

	@Override
	public String deleteCourseEmployee(Integer courseId, Integer employeeId) {
		return courseDao.deleteCourseEmployee(courseId, employeeId);
	}

}
