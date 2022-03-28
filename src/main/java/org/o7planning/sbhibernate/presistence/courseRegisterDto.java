package org.o7planning.sbhibernate.presistence;

import java.util.Date;

public class courseRegisterDto {

	public static final String COURSE_ID = "course_ID";
	public static final String EMPLOYEE_ID = "employee_ID";
	public static final String STATUS = "status";
	public static final String START_DATE = "start_Date";
	public static final String END_DATE = "end_Date";
	public static final String CERT_LINK = "cert_Link";
	
	private String employee_ID;
	private String course_ID;
	private Integer status;
	private Date start_Date;
	private Date end_Date;
	private String cert_Link;
	
	public courseRegisterDto() {
		// TODO Auto-generated constructor stub
	}

	public courseRegisterDto(String employee_ID, String course_ID, Integer status, Date start_Date, Date end_Date,
			String cert_Link) {
		super();
		this.employee_ID = employee_ID;
		this.course_ID = course_ID;
		this.status = status;
		this.start_Date = start_Date;
		this.end_Date = end_Date;
		this.cert_Link = cert_Link;
	}

	public String getEmployee_ID() {
		return employee_ID;
	}

	public void setEmployee_ID(String employee_ID) {
		this.employee_ID = employee_ID;
	}

	public String getCourse_ID() {
		return course_ID;
	}

	public void setCourse_ID(String course_ID) {
		this.course_ID = course_ID;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getStart_Date() {
		return start_Date;
	}

	public void setStart_Date(Date start_Date) {
		this.start_Date = start_Date;
	}

	public Date getEnd_Date() {
		return end_Date;
	}

	public void setEnd_Date(Date end_Date) {
		this.end_Date = end_Date;
	}

	public String getCert_Link() {
		return cert_Link;
	}

	public void setCert_Link(String cert_Link) {
		this.cert_Link = cert_Link;
	}

	@Override
	public String toString() {
		return "courseRegisterDto [employee_ID=" + employee_ID + ", course_ID=" + course_ID + ", status=" + status
				+ ", start_Date=" + start_Date + ", end_Date=" + end_Date + ", cert_Link=" + cert_Link + "]";
	}
}
