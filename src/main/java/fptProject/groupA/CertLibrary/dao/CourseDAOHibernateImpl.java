package fptProject.groupA.CertLibrary.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fptProject.groupA.CertLibrary.persistence.Course;

@Repository
public class CourseDAOHibernateImpl implements CourseDao {
	
	private EntityManager entityManager;
	
	// constructor injection
	@Autowired
	public CourseDAOHibernateImpl (EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	// Get all the courses
	@Override
	public List<Course> getAll() {
		Session currentSession = entityManager.unwrap(Session.class);
		return currentSession.createNamedQuery(Course.GET_ALL, Course.class).getResultList();
	}

//	Tổng số khóa học
	@Override
	public Integer numberOfCourses() {
		Session currentSession = entityManager.unwrap(Session.class);
		String sql = "SELECT count(c.course_id) FROM course AS c JOIN course_employee\"";
		return currentSession.createQuery(sql, Integer.class).getFirstResult();
	}

}
