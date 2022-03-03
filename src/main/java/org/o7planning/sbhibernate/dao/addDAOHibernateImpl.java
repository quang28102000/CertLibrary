package org.o7planning.sbhibernate.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.o7planning.sbhibernate.presistence.CourseDto;
import org.o7planning.sbhibernate.presistence.InforPageDto;
import org.o7planning.sbhibernate.presistence.UserProfileDto;
import org.o7planning.sbhibernate.presistence.courseRegisterDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class addDAOHibernateImpl implements addDAO {

	private EntityManager entityManager;

	@Override
	public courseRegisterDto addNewCourse(courseRegisterDto registerDto) {
		Session openSession = entityManager.unwrap(Session.class).getSessionFactory().openSession();
		NativeQuery<?> query = openSession.createNativeQuery(
				"INSERT INTO certlibrary.course_employee(course_id, employee_id, status, start_date, isDeleted )"
						+ " values (:course_id, employee_id, :status, :start_date, '0');");
		query.setParameter("course_id", registerDto.COURSE_ID).setParameter("employee_id", registerDto.EMPLOYEE_ID)
				.setParameter("status", registerDto.STATUS).setParameter("start_date", registerDto.START_DATE);
		return registerDto;
	}

}
