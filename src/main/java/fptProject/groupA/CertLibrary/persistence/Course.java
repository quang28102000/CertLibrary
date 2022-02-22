package fptProject.groupA.CertLibrary.persistence;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "course")
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "course_id", nullable = false, updatable = false)
	private Integer id;

	@Column(name = "course_tittle")
	private String tittle;

	@Column(name = "platform")
	private String platform;

	@Column(name = "category")
	private String category;

	@OneToMany(mappedBy = "course")
	private List<CourseEmployee> courseEmployees;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "Course [id=" + id + ", tittle=" + tittle + ", platform=" + platform + ", category=" + category + "]";
	}

}
