package org.o7planning.sbhibernate.presistence;

import javax.persistence.Entity;

import java.util.List;

import javax.persistence.*;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "course")
@NamedQueries({
	@NamedQuery(name = "GET_ALL", query = "FROM Course")
})
public class Course {

	public static final String GET_ALL = "GET_ALL";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "course_id", nullable = false, updatable = false)
	private Integer id;

	@Column(name = "course_tittle")
	private String tittle;

	@Column(name = "platform")
	private String platform;

	@Column(name = "category")
	private String category;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "course_employee",
			joinColumns = @JoinColumn(name="course_id"),
			inverseJoinColumns = @JoinColumn(name="employee_id")
			)
	@JsonIgnore
	private List<Employee> employees;
	

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

}
