package fptProject.groupA.CertLibrary.persistence;

public class CourseRegisterDto {

	public static final String EMPLOYEE_ID = "employeeID";
	public static final String EMPLOYEE_NAME = "employeeName";
	public static final String COURSE_ID = "courseID";
	public static final String COURSE_NAME = "courseName";
	public static final String PLATFORM = "platform";
	public static final String STATUS = "status";
	public static final String START_DATE = "startDate";
	public static final String END_DATE = "endDate";
	public static final String COURSE_LENGTH = "courseLength";
																																																																					public static final String COURSE_LINK = "certLink";

	private Integer employeeId;
	private String employeeName;
	private Integer courseId;
	private String courseName;
	private String platform;
	private String status;
	private String startDate;
	private String endDate;
	private Integer courseLength;
	private String certLink;

	public CourseRegisterDto() {

	}
	
	public CourseRegisterDto(Integer employeeId, String employeeName, Integer courseId, String courseName,
			String platform, String status, String startDate, String endDate, Integer courseLength, String certLink) {
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.courseId = courseId;
		this.courseName = courseName;
		this.platform = platform;
		this.status = status;
		this.startDate = startDate;
		this.endDate = endDate;
		this.courseLength = courseLength;
		this.certLink = certLink;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Integer getCourseLength() {
		return courseLength;
	}

	public void setCourseLength(Integer courseLength) {
		this.courseLength = courseLength;
	}

	public String getCertLink() {
		return certLink;
	}

	public void setCertLink(String certLink) {
		this.certLink = certLink;
	}

	@Override
	public String toString() {
		return "CourseRegisterDTO [employeeId=" + employeeId + ", employeeName=" + employeeName + ", courseId="
				+ courseId + ", courseName=" + courseName + ", platform=" + platform + ", status=" + status
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", courseLength=" + courseLength + ", certLink="
				+ certLink + "]";
	}

}
