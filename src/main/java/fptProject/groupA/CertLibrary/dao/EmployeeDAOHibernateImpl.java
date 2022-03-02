 package fptProject.groupA.CertLibrary.dao;


import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.CalendarDateType;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fptProject.groupA.CertLibrary.persistence.EmployeeDto;
import fptProject.groupA.CertLibrary.persistence.UserProfileDto;

@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDao {

	private static final String GET_USER_PROFILE_DTOS = "SELECT e.employee_id AS " + UserProfileDto.ID + ", "
			+ "e.full_name AS " + UserProfileDto.FULL_NAME + ", "
			+ "TRIM(e.email) AS " + UserProfileDto.EMAIL + ", \n"
	+ "		GROUP_CONCAT(DISTINCT(es.skill_name) SEPARATOR ';') " + UserProfileDto.SKILLS + ", \n"
	+ "		GROUP_CONCAT(DISTINCT(c.course_tittle) SEPARATOR ';') " + UserProfileDto.COURSES + " \n"
	+ "	FROM employee AS e JOIN course_employee AS ce \n"
	+ "	 ON e.employee_id = ce.employee_id JOIN employee_skills AS es \n"
	+ "	 ON e.employee_id = es.employee_id JOIN course AS c \n"
	+ "	 ON ce.course_id = c.course_id "
	+ "  WHERE e.employee_id = :employeeId";
	
	private static final String GET_EMPLOYEE_DTOS = 
			"SELECT e.employee_id AS id, e.full_name AS fullName, e.email AS email, \r\n"
			+ "ce.status AS status, CAST(ce.start_date AS DATE) AS startDate, CAST(ce.end_date AS DATE) AS endDate,\r\n"
			+ "c.course_tittle AS course, c.platform AS platform \r\n"
			+ "FROM employee AS e \r\n"
			+ "JOIN course_employee AS ce \r\n"
			+ " ON e.employee_id = ce.employee_id\r\n"
			+ "JOIN course AS c\r\n"
			+ " ON ce.course_id = c.course_id;";
	
	private static final String GET_SUBSCRIBED_EMPLOYESS_IN_LAST_7_DAYS_DTOS =
			"SELECT e.employee_id AS " + EmployeeDto.ID + ", "
				+ "e.full_name AS " + EmployeeDto.FULL_NAME + ", "
				+ "e.email AS " + EmployeeDto.EMAIL + ","
				+ "ce.status AS " + EmployeeDto.STATUS + ", "
				+ "c.course_tittle AS " + EmployeeDto.COURSE + ", "
				+ "c.platform AS " + EmployeeDto.PLATFORM + "\r\n"
			+ "FROM course_employee AS ce\r\n"
			+ "JOIN employee AS e\r\n"
			+ "ON ce.employee_id = e.employee_id\r\n"
			+ "JOIN course AS c\r\n"
			+ "ON ce.course_id = c.course_id\r\n"
			+ "WHERE status = 1 AND ce.start_date > DATE_SUB(CURRENT_DATE(), INTERVAL 7 day);";
	
	private EntityManager entityManager;
	
	@Autowired
	public EmployeeDAOHibernateImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}


	@SuppressWarnings("deprecation")
	@Override
	public UserProfileDto findEmployeeProfile(Integer id) {
		Session openSession = entityManager.unwrap(Session.class).getSessionFactory().openSession();
		
		NativeQuery<?> query = openSession.createNativeQuery(GET_USER_PROFILE_DTOS);
		
		query.addScalar(UserProfileDto.ID, StandardBasicTypes.INTEGER)
			 .addScalar(UserProfileDto.FULL_NAME, StandardBasicTypes.STRING)
			 .addScalar(UserProfileDto.EMAIL, StandardBasicTypes.STRING)
			 .addScalar(UserProfileDto.SKILLS, StandardBasicTypes.STRING)
			 .addScalar(UserProfileDto.COURSES, StandardBasicTypes.STRING)
				.setParameter("employeeId", id)
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
			.addScalar(EmployeeDto.START_DATE, StandardBasicTypes.DATE)
			.addScalar(EmployeeDto.END_DATE, StandardBasicTypes.DATE)
			.addScalar(EmployeeDto.COURSE, StandardBasicTypes.STRING)
			.addScalar(EmployeeDto.PLATFORM, StandardBasicTypes.STRING)
			.setResultTransformer(Transformers.aliasToBean(EmployeeDto.class));	
		return (List<EmployeeDto>)query.getResultList();
	}


	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<EmployeeDto> findSubscribedEmployeesInLast7Days() {
		Session openSession = entityManager.unwrap(Session.class).getSessionFactory().openSession();
		NativeQuery<?> query = openSession.createNativeQuery(GET_SUBSCRIBED_EMPLOYESS_IN_LAST_7_DAYS_DTOS);
		query.addScalar(EmployeeDto.ID, StandardBasicTypes.INTEGER)
		.addScalar(EmployeeDto.FULL_NAME, StandardBasicTypes.STRING)
		.addScalar(EmployeeDto.EMAIL, StandardBasicTypes.STRING)
		.addScalar(EmployeeDto.STATUS, StandardBasicTypes.INTEGER)
		.addScalar(EmployeeDto.PLATFORM, StandardBasicTypes.STRING)
		.setResultTransformer(Transformers.aliasToBean(EmployeeDto.class));	
	return (List<EmployeeDto>)query.getResultList();
	}

}
