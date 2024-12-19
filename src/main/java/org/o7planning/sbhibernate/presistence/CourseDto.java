package org.o7planning.sbhibernate.presistence;

import java.util.Arrays;

public class CourseDto {
	public static final String ID = "id";
	public static final String NAME = "name";
	public static final String IMAGE = "image";
	public static final String PLATFORM = "platform";
	public static final String CATEGORY = "category";
	public static final String COURSE_LENGTH = "courseLength";
	public static final String SKILLS = "skills";

	private Integer id;
	private String name;
	private String image;
	private String platform;
	private String category;
	private Double courseLength;
	private String[] skills;

	public CourseDto() {

	}

	public CourseDto(Integer id, String name, String image, String platform, String category, Double courseLength,
			String[] skills) {
		this.id = id;
		this.name = name;
		this.image = image;
		this.platform = platform;
		this.category = category;
		this.courseLength = courseLength;
		this.skills = skills;
	}
	
	public CourseDto(String name,String platform, String category) {
		this.name = name;
		this.platform = platform;
		this.category = category;
	}

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Double getCourseLength() {
		return courseLength;
	}

	public void setCourseLength(Double courseLength) {
		this.courseLength = courseLength;
	}

	public String[] getSkills() {
		return skills;
	}

	public void setSkills(String[] skills) {
		this.skills = skills;
	}

	@Override
	public String toString() {
		return "CourseDto [id=" + id + ", name=" + name + ", image=" + image + ", platform=" + platform + ", category="
				+ category + ", courseLength=" + courseLength + ", skills=" + Arrays.toString(skills) + "]";
	}

}
