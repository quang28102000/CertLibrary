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
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "employee")
public class Employee {

	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "employee_id", nullable = false, updatable = false)
	private Integer id;

	@Column(name = "full_name")
	@JsonProperty("full_name")
	private String fullName;

	@Column(name = "user_name")
	@JsonProperty("user_name")
	private String userName;

	@Column(name = "password")
	private String password;

	@Column(name = "email")
	private String email;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinTable(
			name = "course_employee",
			joinColumns = @JoinColumn(name="employee_id"),
			inverseJoinColumns = @JoinColumn(name="course_id")
			)
	@JsonIgnore
	private List<Course> courses;
	
	public Employee() {
		
	}
	
	public Employee(Integer id, String fullName, String userName, String password, String email) {
		this.id = id;
		this.fullName = fullName;
		this.userName = userName;
		this.password = password;
		this.email = email;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", fullName=" + fullName + ", userName=" + userName + ", password=" + password
				+ ", email=" + email + "]";
	}
	
}
