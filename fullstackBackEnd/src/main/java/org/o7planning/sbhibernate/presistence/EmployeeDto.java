package org.o7planning.sbhibernate.presistence;

public class EmployeeDto {

	public static final String ID = "id";
	public static final String FULL_NAME = "fullName";
	public static final String EMAIL = "email";
	public static final String STATUS = "status";
	public static final String COURSE = "course";
	public static final String PLATFORM = "platform";

	private Integer id;
	private String fullName;
	private String email;
	private String status;
	private String course;
	private String platform;

	public EmployeeDto() {

	}

	public EmployeeDto(Integer id, String fullName, String email, String status, String platform) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.email = email;
		this.status = status;
		this.platform = platform;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "EmployeeDto [id=" + id + ", fullName=" + fullName + ", email=" + email + ", status=" + status
				+ ", course=" + course + ", platform=" + platform + "]";
	}

}
