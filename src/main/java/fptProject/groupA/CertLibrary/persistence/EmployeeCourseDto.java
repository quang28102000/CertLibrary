package fptProject.groupA.CertLibrary.persistence;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

public class EmployeeCourseDto {

	public static final String EMPLOYEE_ID = "employeeId";
	public static final String FULL_NAME = "fullName";
	public static final String EMAIL = "email";
	public static final String STATUS = "status";
	public static final String START_DATE = "startDate";
	public static final String END_DATE = "endDate";
	public static final String COURSE_ID = "courseId";
	public static final String COURSE = "course";
	public static final String PLATFORM = "platform";
	public static final String CERT_LINK = "certLink";
	public static final String COURSE_LENGTH = "courseLength";
	public static final String CATEGORY = "category";
	public static final String IS_DELETED = "isDeleted";

	private Integer employeeId;
	private String fullName;
	private String email;
	private String status;
	private Date startDate;
	private Date endDate;
	private Integer courseId;
	private String course;
	private String platform;
	private String certLink;
	private Integer courseLength;
	private String category;
	private Integer isDeleted;

	public EmployeeCourseDto() {

	}

	public EmployeeCourseDto(Integer employeeId, String fullName, String email, String status, Date startDate,
			Date endDate, Integer courseId, String course, String platform, String certLink, Integer courseLength,
			String category, Integer isDeleted) {
		this.employeeId = employeeId;
		this.fullName = fullName;
		this.email = email;
		this.status = status;
		this.startDate = startDate;
		this.endDate = endDate;
		this.courseId = courseId;
		this.course = course;
		this.platform = platform;
		this.certLink = certLink;
		this.courseLength = courseLength;
		this.category = category;
		this.isDeleted = isDeleted;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getCertLink() {
		return certLink;
	}

	public void setCertLink(String certLink) {
		this.certLink = certLink;
	}

	public Integer getCourseLength() {
		return courseLength;
	}

	public void setCourseLength(Integer courseLength) {
		this.courseLength = courseLength;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email.trim();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status.toString();
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return "EmployeeCourseDto [employeeId=" + employeeId + ", fullName=" + fullName + ", email=" + email
				+ ", status=" + status + ", startDate=" + startDate + ", endDate=" + endDate + ", courseId=" + courseId
				+ ", course=" + course + ", platform=" + platform + ", certLink=" + certLink + ", courseLength="
				+ courseLength + ", category=" + category + ", isDeleted=" + isDeleted + "]";
	}

}
