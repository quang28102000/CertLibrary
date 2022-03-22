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

import fptProject.groupA.CertLibrary.persistence.EmployeeCourseDto;
import fptProject.groupA.CertLibrary.persistence.EmployeeDto;
import fptProject.groupA.CertLibrary.persistence.UserProfileDto;

@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDao {
	
	private static final String GET_EMPLOYEE_INFO_DTOS = 
			"SELECT e.full_name AS " + EmployeeDto.FULL_NAME 
			+ ", e.email AS " + EmployeeDto.EMAIL 
			+ ", e.profile_image AS " + EmployeeDto.PROFILE_IMAGE + ", \r\n"
			+ "GROUP_CONCAT(es.skill_name SEPARATOR ';') " + EmployeeDto.SKILLS + " \r\n"
			+ "FROM employee AS e\r\n"
			+ "JOIN employee_skills AS es\r\n"
			+ "ON e.employee_id = es.employee_id\r\n"
			+ "GROUP BY e.employee_id;";

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
			"SELECT e.employee_id AS employeeId, \r\n"
			+ "e.full_name AS fullName, e.email AS email, ce.status AS status, \r\n"
			+ "CAST(ce.start_date AS DATE) AS startDate, CAST(ce.end_date AS DATE) AS endDate, \r\n"
			+ "c.course_id AS courseId, ce.cert_link AS certLink,\r\n"
			+ "c.course_tittle AS course, \r\n"
			+ "cd.course_length AS courseLength,\r\n"
			+ "c.platform AS platform,\r\n"
			+ "c.category AS category \r\n"
			+ "FROM employee AS e \r\n"
			+ "JOIN course_employee AS ce \r\n"
			+ " ON e.employee_id = ce.employee_id\r\n"
			+ "JOIN course AS c\r\n"
			+ " ON ce.course_id = c.course_id\r\n"
			+ "JOIN course_detail AS cd\r\n"
			+ " ON c.course_id = cd.course_id WHERE ce.is_deleted = 0";
	
	private static final String GET_SUBSCRIBED_EMPLOYESS_IN_LAST_7_DAYS_DTOS =
			"SELECT e.employee_id AS " + EmployeeCourseDto.EMPLOYEE_ID + ", "
				+ "e.full_name AS " + EmployeeCourseDto.FULL_NAME + ", "
				+ "e.email AS " + EmployeeCourseDto.EMAIL + ","
				+ "ce.status AS " + EmployeeCourseDto.STATUS + ", "
				+ "c.course_id AS " + EmployeeCourseDto.COURSE_ID + ", "
				+ "c.course_tittle AS " + EmployeeCourseDto.COURSE + ", "
				+ "c.platform AS " + EmployeeCourseDto.PLATFORM + " FROM course_employee AS ce\r\n"
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

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<EmployeeCourseDto> getEmployees() {
		Session openSession = entityManager.unwrap(Session.class).getSessionFactory().openSession();
		NativeQuery<?> query = openSession.createNativeQuery(GET_EMPLOYEE_DTOS);
		query.addScalar(EmployeeCourseDto.EMPLOYEE_ID, StandardBasicTypes.INTEGER)
			.addScalar(EmployeeCourseDto.FULL_NAME, StandardBasicTypes.STRING)
			.addScalar(EmployeeCourseDto.STATUS, StandardBasicTypes.INTEGER)
			.addScalar(EmployeeCourseDto.START_DATE, StandardBasicTypes.DATE)
			.addScalar(EmployeeCourseDto.END_DATE, StandardBasicTypes.DATE)
			.addScalar(EmployeeCourseDto.COURSE_ID, StandardBasicTypes.INTEGER)
			.addScalar(EmployeeCourseDto.CERT_LINK, StandardBasicTypes.STRING)
			.addScalar(EmployeeCourseDto.COURSE, StandardBasicTypes.STRING)
			.addScalar(EmployeeCourseDto.COURSE_LENGTH, StandardBasicTypes.INTEGER)
			.addScalar(EmployeeCourseDto.PLATFORM, StandardBasicTypes.STRING)
			.addScalar(EmployeeCourseDto.CATEGORY, StandardBasicTypes.STRING)
			.setResultTransformer(Transformers.aliasToBean(EmployeeCourseDto.class));	
		return (List<EmployeeCourseDto>)query.getResultList();
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


	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<EmployeeCourseDto> findSubscribedEmployeesInLast7Days() {
		Session openSession = entityManager.unwrap(Session.class).getSessionFactory().openSession();
		NativeQuery<?> query = openSession.createNativeQuery(GET_SUBSCRIBED_EMPLOYESS_IN_LAST_7_DAYS_DTOS);
		query.addScalar(EmployeeCourseDto.EMPLOYEE_ID, StandardBasicTypes.INTEGER)
		.addScalar(EmployeeCourseDto.FULL_NAME, StandardBasicTypes.STRING)
		.addScalar(EmployeeCourseDto.EMAIL, StandardBasicTypes.STRING)
		.addScalar(EmployeeCourseDto.STATUS, StandardBasicTypes.INTEGER)
		.addScalar(EmployeeCourseDto.COURSE_ID, StandardBasicTypes.INTEGER)
		.addScalar(EmployeeCourseDto.COURSE, StandardBasicTypes.STRING)
		.addScalar(EmployeeCourseDto.PLATFORM, StandardBasicTypes.STRING)
		.setResultTransformer(Transformers.aliasToBean(EmployeeCourseDto.class));	
	return (List<EmployeeCourseDto>)query.getResultList();
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<EmployeeDto> getEmployeesInfo() {
		Session openSession = entityManager.unwrap(Session.class).getSessionFactory().openSession();
		NativeQuery<?> query = openSession.createNativeQuery(GET_EMPLOYEE_INFO_DTOS);
		query.addScalar(EmployeeDto.FULL_NAME, StandardBasicTypes.STRING)
			.addScalar(EmployeeDto.EMAIL, StandardBasicTypes.STRING)
			.addScalar(EmployeeDto.PROFILE_IMAGE, StandardBasicTypes.STRING)
			.addScalar(EmployeeDto.SKILLS, StandardBasicTypes.STRING)
			.setResultTransformer(Transformers.aliasToBean(EmployeeDto.class));
		return (List<EmployeeDto>) query.getResultList();
	}

}
