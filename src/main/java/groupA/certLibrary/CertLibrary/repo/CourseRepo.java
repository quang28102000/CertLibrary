package groupA.certLibrary.CertLibrary.repo;

import groupA.certLibrary.CertLibrary.model.Course;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepo extends JpaRepository<Course, Integer> {
	
//	1: Đã đăng ký
	@Query(value = "SELECT COUNT(c.course_id) FROM course_employee AS c WHERE c.status = 1", nativeQuery = true)
	Integer registeredCoursesByEmployee();	
	
//	2: Chưa đăng ký
	@Query(value = "SELECT COUNT(c.course_id) FROM course_employee AS c WHERE c.status = 2", nativeQuery = true)
	Integer unregisteredCoursesByEmployee();	
	
//  3: Đã hoàn thành
	@Query(value = "SELECT COUNT(c.course_id) FROM course_employee AS c WHERE c.status = 3", nativeQuery = true)
	Integer completeCoursesByEmployee();	
	
//	4: Chưa hoàn thành khóa học
	@Query(value = "SELECT COUNT(c.course_id) FROM course_employee AS c WHERE c.status = 4", nativeQuery = true)
	Integer incompleteCoursesByEmployee();	
	
//	Số người đăng ký gần nhất trong 7 ngày
	@Query(value = "SELECT COUNT(*) FROM course_employee WHERE status = 1  AND start_date > DATE_SUB(CURRENT_DATE(), INTERVAL 7 day)", nativeQuery = true)
	Integer numberOfEmployeesInLast7days();	
	
//	Tổng số khóa học
	@Query(value="SELECT count(c.course_id) FROM course AS c JOIN course_employee", nativeQuery = true)
	Integer numberOfCourses();
	
}
