package fptProject.groupA.CertLibrary.persistence;

public class CourseHomePageDto {
	public static final String ID = "id";
	public static final String FULL_NAME = "fullName";
	public static final String TITTLE = "tittle";
	public static final String PLATFORM = "platform";
	public static final String CATEGORY = "category";
	public static final String COURSE_LENGTH = "courseLength";

	private Integer id;
	private String fullName;
	private String tittle;
	private String platform;
	private String category;
	private Integer courseLength;

	public CourseHomePageDto() {

	}

	public CourseHomePageDto(Integer id, String fullName, String tittle, String platform, String category,
			Integer courseLength) {
		this.id = id;
		this.fullName = fullName;
		this.tittle = tittle;
		this.platform = platform;
		this.category = category;
		this.courseLength = courseLength;
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

	public String getTittle() {
		return tittle;
	}

	public void setTittle(String tittle) {
		this.tittle = tittle;
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

	public Integer getCourseLength() {
		return courseLength;
	}

	public void setCourseLength(Integer courseLength) {
		this.courseLength = courseLength;
	}

	@Override
	public String toString() {
		return "CourseHomePageDto [id=" + id + ", fullName=" + fullName + ", tittle=" + tittle + ", platform="
				+ platform + ", category=" + category + ", courseLength=" + courseLength + "]";
	}

}
