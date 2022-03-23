package fptProject.groupA.CertLibrary.persistence;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "course")
@NamedQueries({
	@NamedQuery(name = "GET_ALL", query = "FROM Course")
})
public class Course {

	public static final String GET_ALL = "GET_ALL";
	
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "course_id", nullable = false, updatable = false)
	private Integer id;

	@Column(name = "course_tittle")
	@JsonProperty("course_tittle")
	private String tittle;

	@Column(name = "platform")
	@JsonProperty("platform")
	private String platform;

	@Column(name = "category")
	@JsonProperty("category")
	private String category;

	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinTable(
			name = "course_employee",
			joinColumns = @JoinColumn(name="course_id"),
			inverseJoinColumns = @JoinColumn(name="employee_id")
			)
	@JsonIgnore
	private List<Employee> employees;
	

	public Course(Integer id, String tittle, String platform, String category) {
		this.id = id;
		this.tittle = tittle;
		this.platform = platform;
		this.category = category;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

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
