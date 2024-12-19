package org.o7planning.sbhibernate.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.o7planning.sbhibernate.presistence.InforPageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class InforDAOHibernateImpl implements InforDAO {

	private final String GET_INFOR_DTO = "SELECT c.full_name, a.course_id, a.employee_id, a.status, a.start_date, a.end_date, a.cert_link,\r\n"
			+ " b.course_tittle, b.category, b.platform, d.course_length FROM certlibrary.course_employee a "
			+ "inner join certlibrary.course b on a.course_id = b.course_id\r\n"
			+ " inner join certlibrary.employee c on c.employee_id = a.employee_id "
			+ "inner join certlibrary.course_detail d on d.course_id = b.course_id "
			+ "where a.isDeleted = 0  order by a.employee_id asc;";

//	private final String ADD_NEW_COURSE = "insert into course(course_tittle, platform, category) "
//			+ "values (':course_tittle', ':platform', ':category')";

	private EntityManager entityManager;

	@Autowired
	public InforDAOHibernateImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<InforPageDto> getInforDto() {
		Session openSession = entityManager.unwrap(Session.class).getSessionFactory().openSession();
		NativeQuery<InforPageDto> query = openSession.createNativeQuery(GET_INFOR_DTO);
		query.addScalar(InforPageDto.FULL_NAME, StandardBasicTypes.STRING)
				.addScalar(InforPageDto.COURSE_ID, StandardBasicTypes.INTEGER)
				.addScalar(InforPageDto.EMPLOYEE_ID, StandardBasicTypes.INTEGER)
				.addScalar(InforPageDto.STATUS, StandardBasicTypes.INTEGER)
				.addScalar(InforPageDto.START_DATE, StandardBasicTypes.DATE)
				.addScalar(InforPageDto.END_DATE, StandardBasicTypes.DATE)
				.addScalar(InforPageDto.CERT_LINK, StandardBasicTypes.STRING)
				.addScalar(InforPageDto.COURSE_TITTLE, StandardBasicTypes.STRING)
				.addScalar(InforPageDto.CATEGORY, StandardBasicTypes.STRING)
				.addScalar(InforPageDto.PLATFORM, StandardBasicTypes.STRING)
				.addScalar(InforPageDto.COURSE_LENGTH, StandardBasicTypes.INTEGER)
				.setResultTransformer(Transformers.aliasToBean(InforPageDto.class));

		List<InforPageDto> list = query.list();
		return list;
//		return (List<InforPageDto>) query.getResultList();
	}

	@Override
	public List<InforPageDto> getInforName() {
		Session openSession = entityManager.unwrap(Session.class).getSessionFactory().openSession();
		NativeQuery<InforPageDto> query = openSession.createNativeQuery(GET_INFOR_DTO);
		query.addScalar(InforPageDto.FULL_NAME, StandardBasicTypes.STRING)
				.addScalar(InforPageDto.COURSE_ID, StandardBasicTypes.INTEGER)
				.addScalar(InforPageDto.EMPLOYEE_ID, StandardBasicTypes.INTEGER)
				.addScalar(InforPageDto.STATUS, StandardBasicTypes.INTEGER)
				.addScalar(InforPageDto.START_DATE, StandardBasicTypes.DATE)
				.addScalar(InforPageDto.END_DATE, StandardBasicTypes.DATE)
				.addScalar(InforPageDto.CERT_LINK, StandardBasicTypes.STRING)
				.addScalar(InforPageDto.COURSE_TITTLE, StandardBasicTypes.STRING)
				.addScalar(InforPageDto.CATEGORY, StandardBasicTypes.STRING)
				.addScalar(InforPageDto.PLATFORM, StandardBasicTypes.STRING)
				.addScalar(InforPageDto.COURSE_LENGTH, StandardBasicTypes.INTEGER);
		List<InforPageDto> list = query.list();
		return list;
	}

	@Override
	public InforPageDto editInfor(InforPageDto inforPageDto) {
		System.err.println(inforPageDto);
		Session openSession = entityManager.unwrap(Session.class).getSessionFactory().openSession();
		org.hibernate.Transaction txn = openSession.beginTransaction();
		NativeQuery<?> query = openSession.createNativeQuery("Update course_employee\r\n"
				+ "set course_employee.cert_link = :cert_link, course_employee.status = :status,\r\n"
				+ "course_employee.start_date = :start_date, course_employee.end_date = :end_date\r\n"
				+ "where course_employee.employee_id = :employee_id and course_employee.course_id = :course_id");
		query.setParameter("course_id", inforPageDto.getCourse_ID())
				.setParameter("employee_id", inforPageDto.getEmployee_ID())
				.setParameter("status", inforPageDto.getStatus())
				.setParameter("start_date", inforPageDto.getStart_Date())
				.setParameter("end_date", inforPageDto.getEnd_Date())
				.setParameter("cert_link", inforPageDto.getCert_Link()).executeUpdate();
		txn.commit();
//		updateCourseLength(inforPageDto.getCourse_ID(), inforPageDto.getCourse_Length());
		System.err.println(inforPageDto.getEmployee_ID());
		return inforPageDto;
	}

	public void updateCourseLength(int id, float time) {
		Session openSession = entityManager.unwrap(Session.class).getSessionFactory().openSession();
		org.hibernate.Transaction txn = openSession.beginTransaction();
		System.err.println(id);
		System.err.println(time);
		NativeQuery<?> query = openSession.createNativeQuery(
				"UPDATE course_detail SET course_detail.course_length = :course_length WHERE course_detail.course_id = :course_id");

		query.setParameter("course_length", time).setParameter("course_id", id).executeUpdate();
		txn.commit();
	}

	@Override
	public void deleteInfor(Integer courseID, Integer employeeID) {
		Session openSession = entityManager.unwrap(Session.class).getSessionFactory().openSession();
		System.err.println(courseID + "_" + employeeID);
		org.hibernate.Transaction txn = openSession.beginTransaction();

		NativeQuery<?> query = openSession
				.createNativeQuery("UPDATE certlibrary.course_employee SET course_employee.isDeleted = 1\r\n"
						+ "WHERE course_employee.employee_id = :employee_id and course_employee.course_id = :course_id");

		query.setParameter("employee_id", employeeID).setParameter("course_id", courseID).executeUpdate();
		txn.commit();
	}

	@Override
	public void deleteInforobj(Integer courseID, Integer employeeID) {
		Session openSession = entityManager.unwrap(Session.class).getSessionFactory().openSession();
		System.err.println(courseID + "_" + employeeID);
		org.hibernate.Transaction txn = openSession.beginTransaction();

		NativeQuery<?> query = openSession
				.createNativeQuery("UPDATE certlibrary.course_employee SET course_employee.isDeleted = 1\r\n"
						+ "WHERE course_employee.employee_id = :employee_id and course_employee.course_id = :course_id");

		query.setParameter("employee_id", employeeID).setParameter("course_id", courseID).executeUpdate();
		txn.commit();

	}

//	@Override
//	public void addNewCourse(String tittle, String platform, String category) {
//		Session openSession = entityManager.unwrap(Session.class).getSessionFactory().openSession();
//
//		NativeQuery<?> query = openSession.createNativeQuery("insert into course(course_tittle, platform, category) "
//				+ "values (':course_tittle', ':platform', ':category')");
//
//		query.setParameter("course_tittle", tittle).setParameter("platform", platform)
//				.setParameter("category", category)
//				.setResultTransformer(Transformers.aliasToBean(UserProfileDto.class));
//
//	}

//	public Integer getLastestCourse() {
//		Session currentSession = entityManager.unwrap(Session.class);
//		String sql = "select course_id from certlibrary.course_detail order by course_id desc limit 1";
//		return currentSession.createQuery(sql, Integer.class).getFirstResult();
//	}
}
