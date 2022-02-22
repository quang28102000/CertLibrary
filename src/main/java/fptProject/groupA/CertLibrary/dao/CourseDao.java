package fptProject.groupA.CertLibrary.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fptProject.groupA.CertLibrary.persistence.Course;

public interface CourseDao extends JpaRepository<Course, Integer> {
	
//	Tổng số khóa học
	@Query(value = "SELECT count(c.course_id) FROM course AS c JOIN course_employee", nativeQuery = true)
	Integer numberOfCourses();

}
