package fptProject.groupA.CertLibrary.dao;


import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fptProject.groupA.CertLibrary.persistence.EmployeeDto;
import fptProject.groupA.CertLibrary.persistence.UserProfileDto;

@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDao {

	private static final String GET_USER_PROFILE_DTOS = 
			"SELECT e.employee_id AS " + UserProfileDto.ID + ", "
					+ "e.full_name AS " + UserProfileDto.FULL_NAME + ", "
					+ "TRIM(e.email) AS " + UserProfileDto.EMAIL + ", \n"
			+ "		GROUP_CONCAT(DISTINCT(es.skill_name) SEPARATOR ';') " + UserProfileDto.SKILLS + ", \n"
			+ "		GROUP_CONCAT(DISTINCT(c.course_tittle) SEPARATOR ';') " + UserProfileDto.COURSES + " \n"
			+ "	FROM employee AS e JOIN course_employee AS ce \n"
			+ "	 ON e.employee_id = ce.employee_id JOIN employee_skills AS es \n"
			+ "	 ON e.employee_id = es.employee_id JOIN course AS c \n"
			+ "	 ON ce.course_id = c.course_id \n";
	
	private static final String GET_EMPLOYEE_DTOS = 
			"SELECT e.employee_id AS " + EmployeeDto.ID 
			+ ", e.full_name AS " + EmployeeDto.FULL_NAME 
			+ ", e.email AS " + EmployeeDto.EMAIL 
			+ ", ce.status AS " + EmployeeDto.STATUS 
			+ ", c.platform AS " + EmployeeDto.PLATFORM + " \r\n"
			+ "FROM employee AS e \r\n"
			+ "JOIN course_employee AS ce \r\n"
			+ " ON e.employee_id = ce.employee_id\r\n"
			+ "JOIN course AS c\r\n"
			+ " ON ce.course_id = c.course_id;";
	
	private EntityManager entityManager;
	
	@Autowired
	public EmployeeDAOHibernateImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}


	@SuppressWarnings("deprecation")
	@Override
	public UserProfileDto findEmployeeProfile() {
		Session openSession = entityManager.unwrap(Session.class).getSessionFactory().openSession();
		
		NativeQuery<?> query = openSession.createNativeQuery(GET_USER_PROFILE_DTOS);
		
		query.addScalar(UserProfileDto.ID, StandardBasicTypes.INTEGER)
			 .addScalar(UserProfileDto.FULL_NAME, StandardBasicTypes.STRING)
			 .addScalar(UserProfileDto.EMAIL, StandardBasicTypes.STRING)
			 .addScalar(UserProfileDto.SKILLS, StandardBasicTypes.STRING)
			 .addScalar(UserProfileDto.COURSES, StandardBasicTypes.STRING)
			 .setResultTransformer(Transformers.aliasToBean(UserProfileDto.class));
		
		return (UserProfileDto) query.getSingleResult();
	}


	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<EmployeeDto> getEmployees() {
		Session openSession = entityManager.unwrap(Session.class).getSessionFactory().openSession();
		NativeQuery<?> query = openSession.createNativeQuery(GET_EMPLOYEE_DTOS);
		query.addScalar(EmployeeDto.ID, StandardBasicTypes.INTEGER)
			.addScalar(EmployeeDto.FULL_NAME, StandardBasicTypes.STRING)
			.addScalar(EmployeeDto.EMAIL, StandardBasicTypes.STRING)
			.addScalar(EmployeeDto.STATUS, StandardBasicTypes.INTEGER)
			.addScalar(EmployeeDto.PLATFORM, StandardBasicTypes.STRING)
			.setResultTransformer(Transformers.aliasToBean(EmployeeDto.class));	
		return (List<EmployeeDto>)query.getResultList();
	}

}
