package org.o7planning.sbhibernate.presistence;

import java.util.Arrays;

public class UserProfileDto {

	public static final String ID = "id";
	public static final String FULL_NAME = "fullName";
	public static final String EMAIL = "email";
	public static final String SKILLS = "skills";
	public static final String COURSES = "courses";

	private Integer id;
	private String fullName;
	private String email;
	private String[] skills;
	private String[] courses;

	public UserProfileDto() {
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
		this.email = email;
	}

	public String[] getSkills() {
		return skills;
	}

	public void setSkills(String[] skills) {
		this.skills = skills;
	}

	public String[] getCourses() {
		return courses;
	}

	public void setCourses(String[] courses) {
		this.courses = courses;
	}

	@Override
	public String toString() {
		return "EmployeeDto [id=" + id + ", fullName=" + fullName + ", email=" + email + ", skills="
				+ Arrays.toString(skills) + ", courses=" + Arrays.toString(courses) + "]";
	}
}
