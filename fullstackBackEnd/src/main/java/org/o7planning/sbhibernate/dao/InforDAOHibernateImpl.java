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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class InforDAOHibernateImpl implements InforDAO {

	private final String GET_INFOR_DTO = "SELECT c.full_name, a.course_id, a.employee_id, a.status, a.start_date, a.end_date, a.cert_link,\r\n"
			+ " b.course_tittle, b.category, b.platform, d.course_length FROM certlibrary.course_employee a inner join certlibrary.course b \r\n"
			+ " inner join certlibrary.employee c inner join certlibrary.course_detail d where a.isDeleted = 0";

	private final String ADD_NEW_COURSE = "insert into course(course_tittle, platform, category) "
			+ "values (':course_tittle', ':platform', ':category')";

	private EntityManager entityManager;

	@Autowired
	public InforDAOHibernateImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<InforPageDto> getInforDto() {
		Session openSession = entityManager.unwrap(Session.class).getSessionFactory().openSession();
		NativeQuery<?> query = openSession.createNativeQuery(GET_INFOR_DTO);
		query.addScalar(InforPageDto.FULL_NAME, StandardBasicTypes.STRING)
				.addScalar(InforPageDto.COURSE_ID, StandardBasicTypes.INTEGER)
				.addScalar(InforPageDto.EMPLOYEE_ID, StandardBasicTypes.INTEGER)
				.addScalar(InforPageDto.STATUS, StandardBasicTypes.INTEGER)
				.addScalar(InforPageDto.START_DATE, StandardBasicTypes.STRING)
				.addScalar(InforPageDto.END_DATE, StandardBasicTypes.STRING)
				.addScalar(InforPageDto.CERT_LINK, StandardBasicTypes.STRING)
				.addScalar(InforPageDto.COURSE_TITLE, StandardBasicTypes.STRING)
				.addScalar(InforPageDto.CATEGORY, StandardBasicTypes.STRING)
				.addScalar(InforPageDto.PLATFORM, StandardBasicTypes.STRING)
				.addScalar(InforPageDto.COURSE_LENGTH, StandardBasicTypes.INTEGER);
		return (List<InforPageDto>) query.getResultList();
	}

//	public static void main() {
//		InforDAOHibernateImpl daoHibernateImpl = null;
//		List<InforPageDto> l = new ArrayList<>();
//		l = daoHibernateImpl.getInforDto();
//		System.out.println(l);
//
//	}

	@Override
	public void addNewCourse(String tittle, String platform, String category) {
		Session openSession = entityManager.unwrap(Session.class).getSessionFactory().openSession();

		NativeQuery<?> query = openSession.createNativeQuery(ADD_NEW_COURSE);

		query.setParameter("course_tittle", tittle).setParameter("platform", platform).setParameter("category", category)
				.setResultTransformer(Transformers.aliasToBean(UserProfileDto.class));

	}

//	public Integer getLastestCourse(String title) {
//		Session openSession = entityManager.unwrap(Session.class).getSessionFactory().openSession();
//		int id;
//
//		NativeQuery<?> query = openSession.createNativeQuery(ADD_NEW_COURSE);
//
//		query.addScalar(CourseDto.NAME, StandardBasicTypes.STRING)
//				.addScalar(CourseDto.PLATFORM, StandardBasicTypes.STRING)
//				.addScalar(CourseDto.CATEGORY, StandardBasicTypes.STRING).setParameter("course_tittle", tittle)
//				.setResultTransformer(Transformers.aliasToBean(UserProfileDto.class));
//		
//		return CourseDto.ID;
//	}
	
	public Integer getLastestCourse() {
		Session currentSession = entityManager.unwrap(Session.class);
		String sql = "select course_id from certlibrary.course_detail order by course_id desc limit 1";
		return currentSession.createQuery(sql, Integer.class).getFirstResult();
	}
	
	public void addCourseLength(String id, String time) {
		Session openSession = entityManager.unwrap(Session.class).getSessionFactory().openSession();

		NativeQuery<?> query = openSession.createNativeQuery
				("UPDATE course_detail SET course_detail.course_length = :length WHERE course_detail.course_id = :id;");

		query.setParameter("id", id).setParameter("length", time)
				.setResultTransformer(Transformers.aliasToBean(UserProfileDto.class));
	}
}
