package fptProject.groupA.CertLibrary.persistence;

import java.util.Arrays;
import java.util.regex.Pattern;

public class UserProfileDto {

	public static final String ID = "id";
	public static final String FULL_NAME = "fullName";
	public static final String EMAIL = "email";
	public static final String SKILLS = "skills";
	public static final String COURSES = "courses";
	public static final String PROFILE_IMAGE = "profile_image";

	private Integer id;
	private String fullName;
	private String email;
	private String[] skills;
	private String[] courses;
	private String profile_image;
	
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

	public void setSkills(String skills) {
		this.skills = Pattern.compile(";").split(skills);
	}

	public String[] getCourses() {
		return courses;
	}

	public void setCourses(String courses) {
		this.courses = Pattern.compile(";").split(courses);
	}

	public String getProfile_image() {
		return profile_image;
	}

	public void setProfile_image(String profile_image) {
		this.profile_image = profile_image;
	}

	@Override
	public String toString() {
		return "UserProfileDto [id=" + id + ", fullName=" + fullName + ", email=" + email + ", skills="
				+ Arrays.toString(skills) + ", courses=" + Arrays.toString(courses) + ", profile_image=" + profile_image
				+ "]";
	}
	
}
