package org.o7planning.sbhibernate.presistence;

import java.util.Date;

public class InforPageDto {

	public static final String FULL_NAME = "full_Name";
	public static final String COURSE_ID = "course_ID";
	public static final String EMPLOYEE_ID = "employee_ID";
	public static final String STATUS = "status";
	public static final String START_DATE = "start_Date";
	public static final String END_DATE = "end_Date";
	public static final String CERT_LINK = "cert_Link";
	public static final String COURSE_TITLE = "course_Title";
	public static final String CATEGORY = "category";
	public static final String PLATFORM = "platform";
	public static final String COURSE_LENGTH = "course_Length";

	private String full_Name;
	private Integer course_ID;
	private Integer employee_ID;
	private Integer status;
	private Date start_Date;
	private Date end_Date;
	private String cert_Link;
	private String course_Title;
	private String category;
	private String platform;
	private Integer course_Length;

	public InforPageDto() {
		// TODO Auto-generated constructor stub
	}

	public InforPageDto(String full_Name, Integer course_ID, Integer employee_ID, Integer status, Date start_Date,
			Date end_Date, String cert_Link, String course_Title, String category, String platform,
			Integer course_Length) {
		super();
		this.full_Name = full_Name;
		this.course_ID = course_ID;
		this.employee_ID = employee_ID;
		this.status = status;
		this.start_Date = start_Date;
		this.end_Date = end_Date;
		this.cert_Link = cert_Link;
		this.course_Title = course_Title;
		this.category = category;
		this.platform = platform;
		this.course_Length = course_Length;
	}

	public String getFull_Name() {
		return full_Name;
	}

	public void setFull_Name(String full_Name) {
		this.full_Name = full_Name;
	}

	public Integer getCourse_ID() {
		return course_ID;
	}

	public void setCourse_ID(Integer course_ID) {
		this.course_ID = course_ID;
	}

	public Integer getEmployee_ID() {
		return employee_ID;
	}

	public void setEmployee_ID(Integer employee_ID) {
		this.employee_ID = employee_ID;
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

	public String getCourse_Title() {
		return course_Title;
	}

	public void setCourse_Title(String course_Title) {
		this.course_Title = course_Title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public Integer getCourse_Length() {
		return course_Length;
	}

	public void setCourse_Length(Integer course_Length) {
		this.course_Length = course_Length;
	}

	@Override
	public String toString() {
		return "InforPageDto [full_Name=" + full_Name + ", course_ID=" + course_ID + ", employee_ID=" + employee_ID
				+ ", status=" + status + ", start_Date=" + start_Date + ", end_Date=" + end_Date + ", cert_Link="
				+ cert_Link + ", course_Title=" + course_Title + ", category=" + category + ", platform=" + platform
				+ ", course_Length=" + course_Length + "]";
	}
	
	

}
