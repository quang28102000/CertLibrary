package fptProject.groupA.CertLibrary.persistence;

import java.util.Arrays;
import java.util.regex.Pattern;

public class EmployeeDto {
	
	public static final String FULL_NAME = "fullName";
	public static final String EMAIL = "email";
	public static final String PROFILE_IMAGE = "profileImage";
	public static final String SKILLS = "skills";
	
	private String fullName;
	private String email;
	private String profileImage;
	private String[] skills;
	
	public EmployeeDto() {
		
	}

	public EmployeeDto(String fullName, String email, String profileImage, String[] skills) {
		this.fullName = fullName;
		this.email = email;
		this.profileImage = profileImage;
		this.skills = skills;
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

	public String getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	public String[] getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = Pattern.compile(";").split(skills);
	}

	@Override
	public String toString() {
		return "EmployeeDto [fullName=" + fullName + ", email=" + email + ", profileImage=" + profileImage + ", skills="
				+ Arrays.toString(skills) + "]";
	}
	
}
