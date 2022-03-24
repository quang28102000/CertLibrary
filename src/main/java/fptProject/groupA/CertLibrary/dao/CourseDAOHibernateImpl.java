package fptProject.groupA.CertLibrary.dao;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fptProject.groupA.CertLibrary.persistence.Course;
import fptProject.groupA.CertLibrary.persistence.CourseDto;
import fptProject.groupA.CertLibrary.persistence.CourseEmployee;
import fptProject.groupA.CertLibrary.persistence.CourseHomePageDto;
import fptProject.groupA.CertLibrary.persistence.Employee;
import fptProject.groupA.CertLibrary.persistence.Skill;

@Repository
@Transactional
public class CourseDAOHibernateImpl implements CourseDao {

	private static final String GET_COURSE_DTO = "SELECT cd.course_id AS id, c.course_tittle AS name, cd.image AS image, \r\n"
			+ "c.platform AS platform, c.category AS category, cd.course_length AS courseLength, GROUP_CONCAT(s.skill_name SEPARATOR ';') skills\r\n"
			+ "FROM course_detail AS cd\r\n"
			+ "JOIN course AS c\r\n"
			+ "ON c.course_id = cd.course_id\r\n"
			+ "JOIN course_skills AS cs\r\n"
			+ " ON cd.course_id = cs.course_id\r\n"
			+ "JOIN skills AS s\r\n"
			+ "ON cs.skill_id = s.skill_id\r\n"
			+ "GROUP BY c.course_id;";

	private static final String GET_COURSES_HOME_PAGE_DTO = "SELECT e.employee_id AS id, e.full_name AS fullName, c.course_tittle AS tittle, \r\n"
			+ "c.platform AS platform, c.category AS category, cd.course_length AS courseLength \r\n"
			+ "FROM employee AS e\r\n" + "JOIN course_employee AS ce\r\n" + "ON e.employee_id = ce.employee_id\r\n"
			+ "JOIN course AS c\r\n" + "ON ce.course_id = c.course_id\r\n" + "JOIN course_detail AS cd\r\n"
			+ "ON c.course_id = cd.course_id;";

	private EntityManager entityManager;

	// constructor injection
	@Autowired
	public CourseDAOHibernateImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	// Get all the courses
	@Override
	public List<Course> getAll() {
		Session currentSession = entityManager.unwrap(Session.class);
		return currentSession.createNamedQuery(Course.GET_ALL, Course.class).getResultList();
	}
	
	@Override
	public List<Skill> getCourseSkills() {
		Session currentSession = entityManager.unwrap(Session.class);

		return currentSession.createNamedQuery(Skill.GET_ALL_SKILLS, Skill.class).getResultList();
	}
	
	
//	Tổng số khóa học
	@Override
	public Integer numberOfCourses() {
		Session currentSession = entityManager.unwrap(Session.class);
		String sql = "SELECT count(c.course_id) FROM course AS c JOIN course_employee";
		return currentSession.createQuery(sql, Integer.class).getFirstResult();
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<CourseDto> getCoursesDto() {
		Session openSession = entityManager.unwrap(Session.class).getSessionFactory().openSession();

		NativeQuery<?> query = openSession.createNativeQuery(GET_COURSE_DTO);
		query.addScalar(CourseDto.ID, StandardBasicTypes.INTEGER).addScalar(CourseDto.NAME, StandardBasicTypes.STRING)
				.addScalar(CourseDto.IMAGE, StandardBasicTypes.STRING)
				.addScalar(CourseDto.PLATFORM, StandardBasicTypes.STRING)
				.addScalar(CourseDto.CATEGORY, StandardBasicTypes.STRING)
				.addScalar(CourseDto.COURSE_LENGTH, StandardBasicTypes.DOUBLE)
				.addScalar(CourseDto.SKILLS, StandardBasicTypes.STRING)
				.setResultTransformer(Transformers.aliasToBean(CourseDto.class));
		return (List<CourseDto>) query.getResultList();
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<CourseHomePageDto> getCoursesHomePageDto() {
		Session openSession = entityManager.unwrap(Session.class).getSessionFactory().openSession();
		NativeQuery<?> query = openSession.createNativeQuery(GET_COURSES_HOME_PAGE_DTO);
		query.addScalar(CourseHomePageDto.ID, StandardBasicTypes.INTEGER)
				.addScalar(CourseHomePageDto.FULL_NAME, StandardBasicTypes.STRING)
				.addScalar(CourseHomePageDto.TITTLE, StandardBasicTypes.STRING)
				.addScalar(CourseHomePageDto.PLATFORM, StandardBasicTypes.STRING)
				.addScalar(CourseHomePageDto.CATEGORY, StandardBasicTypes.STRING)
				.addScalar(CourseHomePageDto.COURSE_LENGTH, StandardBasicTypes.INTEGER)
				.setResultTransformer(Transformers.aliasToBean(CourseHomePageDto.class));
		return (List<CourseHomePageDto>) query.getResultList();
	}

	@Override
	public Course addCourseForEmployee(Course course, Employee employee) {
		Session openSession = entityManager.unwrap(Session.class).getSessionFactory().openSession();

		org.hibernate.Transaction transaction = openSession.beginTransaction();

		try {
			openSession.saveOrUpdate(course);
			System.out.println("Course updated!");

			openSession.saveOrUpdate(employee);
			System.out.println("Employee updated!");

			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
		}

		System.out.println(course.toString());
		System.out.println(employee.toString());
		return course;
	}

	@Override
	public CourseEmployee addCourseEmployee(CourseEmployee courseEmployee) {
		Session session = entityManager.unwrap(Session.class).getSessionFactory().openSession();
		org.hibernate.Transaction transaction = session.beginTransaction();

		NativeQuery<?> query = session.createNativeQuery("INSERT INTO certlibrary.course_employee "
				+ "(course_id, employee_id, status, start_date, end_date, cert_link, is_deleted) "
				+ "VALUES (:courseId, :employeeId, :status, :startDate, :endDate, :certLink, :isDeleted) ");
		query.setParameter("courseId", courseEmployee.getCourseId())
				.setParameter("employeeId", courseEmployee.getEmployeeId())
				.setParameter("status", courseEmployee.getStatus())
				.setParameter("startDate", courseEmployee.getStartDate())
				.setParameter("endDate", courseEmployee.getEndDate())
				.setParameter("certLink", courseEmployee.getCertLink())
				.setParameter("isDeleted", courseEmployee.getIsDeleted()).executeUpdate();

		System.out.println("CourseEmployee added!");
		System.out.println(courseEmployee.getStartDate() + " - " + courseEmployee.getEndDate());
		transaction.commit();
		return courseEmployee;
	}

	@Override
	public String deleteCourseEmployeeWithFlag(Integer courseId, Integer employeeId) {
		Session session = entityManager.unwrap(Session.class).getSessionFactory().openSession();
		org.hibernate.Transaction transaction = session.beginTransaction();

		NativeQuery<?> query = session.createNativeQuery("UPDATE course_employee AS c\r\n"
				+ "SET c.is_deleted = :isDeleted WHERE c.course_id = :courseId AND c.employee_id = :employeeId");
		query.setParameter("isDeleted", 1).setParameter("courseId", courseId).setParameter("employeeId", employeeId)
				.executeUpdate();

		System.out.println("Deleted CourseEmployee(CourseId: " + courseId + ", EmployeeId: " + employeeId + ")");

		transaction.commit();

		return "CourseId: " + courseId + " and EmployeeId: " + employeeId + " are deleted";
	}

	@Override
	public String updateCourseEmployee(CourseEmployee courseEmployee) {
		Session session = entityManager.unwrap(Session.class).getSessionFactory().openSession();
		org.hibernate.Transaction transaction = session.beginTransaction();

		NativeQuery<?> query = session.createNativeQuery(
				"UPDATE course_employee AS c\r\n" + "SET c.status = :status, c.start_date = :startDate, \r\n"
						+ "c.end_date = :endDate, c.cert_link = :certLink, " + "c.is_deleted = :isDeleted\r\n"
						+ "WHERE c.course_id = :courseId AND c.employee_id = :employeeId");
		query.setParameter("status", courseEmployee.getStatus())
				.setParameter("startDate", courseEmployee.getStartDate())
				.setParameter("endDate", courseEmployee.getEndDate())
				.setParameter("certLink", courseEmployee.getCertLink())
				.setParameter("isDeleted", courseEmployee.getIsDeleted())
				.setParameter("courseId", courseEmployee.getCourseId())
				.setParameter("employeeId", courseEmployee.getEmployeeId()).executeUpdate();

		transaction.commit();

		return "CourseId: " + courseEmployee.getCourseId() + " and EmployeeId: " + courseEmployee.getEmployeeId()
				+ " are updated";
	}

	@Override
	public String deleteCourseEmployee(Integer courseId, Integer employeeId) {
		Session session = entityManager.unwrap(Session.class).getSessionFactory().openSession();
		org.hibernate.Transaction transaction = session.beginTransaction();

		NativeQuery<?> query = session.createNativeQuery("DELETE FROM course_employee AS c\r\n"
				+ "WHERE c.course_id = :courseId AND c.employee_id = :employeeId");
		query.setParameter("courseId", courseId).setParameter("employeeId", employeeId).executeUpdate();

		System.out.println("Deleted CourseEmployee(CourseId: " + courseId + ", EmployeeId: " + employeeId + ")");

		transaction.commit();

		return "CourseId: " + courseId + " and EmployeeId: " + employeeId + " are deleted";
	}

	@Override
	public String addSkillOfACourse(Integer[] skillsId, String[] skillsName, Integer courseFlag) {
		// 3, 4
		Integer[] newSkillsId = Arrays.copyOfRange(skillsId, courseFlag, skillsId.length);
		String[] newSkillsName = Arrays.copyOfRange(skillsName, courseFlag, skillsName.length);
		
		Session session = entityManager.unwrap(Session.class).getSessionFactory().openSession();
		org.hibernate.Transaction transaction = session.beginTransaction();

		for(int i = 0; i < newSkillsId.length; i++) {
			NativeQuery<?> query = session.createNativeQuery("INSERT INTO "
					+ "skills (skill_id, skill_name)\r\n"
					+ "VALUES (:skillId, :skillName)");
			query.setParameter("skillId", newSkillsId[i])
				 .setParameter("skillName", newSkillsName[i])
				 .executeUpdate();
		}

		transaction.commit();
		
		return "New skills: " + skillsId.toString() + " and " + skillsName.toString() + " are updated";
	}

	@Override
	public String addCourse(Course theCourse) {
		Session session = entityManager.unwrap(Session.class).getSessionFactory().openSession();
		org.hibernate.Transaction transaction = session.beginTransaction();

		NativeQuery<?> query = session
				.createNativeQuery("INSERT INTO course " + "(course_id, course_tittle, platform, category)\r\n"
						+ "VALUES (:courseId, :courseTittle, :platform, :category)");

		query.setParameter("courseId", theCourse.getId()).setParameter("courseTittle", theCourse.getTittle())
				.setParameter("platform", theCourse.getPlatform()).setParameter("category", theCourse.getCategory())
				.executeUpdate();
		
		System.out.println(theCourse.getTittle());
		System.out.println(theCourse.getPlatform());
		System.out.println(theCourse.getCategory());

		transaction.commit();
		return "Course: " + theCourse.getId() + " with tittle " + theCourse.getTittle() + " is added";
	}

	@Override
	public String addCourseDetail(Course theCourse, Integer courseLength, String image) {
		Session session = entityManager.unwrap(Session.class).getSessionFactory().openSession();
		org.hibernate.Transaction transaction = session.beginTransaction();

		NativeQuery<?> query = session.createNativeQuery(
				"INSERT INTO course_detail " + "(course_detail_id, image, course_id, course_length)\r\n"
						+ "VALUES (:courseDetailId, :image, :courseId, :courseLength)");

		query.setParameter("courseDetailId", theCourse.getId()).setParameter("image", image)
				.setParameter("courseId", theCourse.getId()).setParameter("courseLength", courseLength).executeUpdate();

		transaction.commit();
		return "CourseDetailId: " + theCourse.getId() + " is added";
	}

	@Override
	public String addCourseSkill(Course theCourse, Integer[] skillsId) {
		Session session = entityManager.unwrap(Session.class).getSessionFactory().openSession();
		org.hibernate.Transaction transaction = session.beginTransaction();

		for(int i = 0; i < skillsId.length; i++) {
			NativeQuery<?> query = session.createNativeQuery(
					"INSERT INTO course_skills (skill_id, course_id)\r\n"
					+ "VALUES (:skillId, :courseId)");
			query.setParameter("skillId", skillsId[i])
					.setParameter("courseId", theCourse.getId())
					.executeUpdate();
		}

		transaction.commit();
		return "CourseSkills[skillId: " + skillsId.toString() + " and CourseId" + theCourse.getId() + " is added";	
		}


}
