package fptProject.groupA.CertLibrary.persistence;
import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

@Entity
@Table(name = "course_employee")
public class CourseEmployee {

	@EmbeddedId
	private Id id;

	@Column(name = "status")
	private Integer status;

	@Column(name = "start_date")
	private LocalDateTime startDate;

	@Column(name = "end_date")
	private LocalDateTime endDate;

	@Column(name = "cert_link")
	private String certLink;

	@Column(name = "is_deleted")
	private Integer isDeleted;

	public CourseEmployee() {

	}

	public CourseEmployee(Id id, Integer status, LocalDateTime startDate, LocalDateTime endDate, String certLink,
			Integer isDeleted) {
		super();
		this.id = id;
		this.status = status;
		this.startDate = startDate;
		this.endDate = endDate;
		this.certLink = certLink;
		this.isDeleted = isDeleted;
	}

	public Id getId() {
		return id;
	}

	public void setId(Id id) {
		this.id = id;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	public String getCertLink() {
		return certLink;
	}

	public void setCertLink(String certLink) {
		this.certLink = certLink;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Override
	public String toString() {
		return "CourseEmployee [id=" + id + ", status=" + status + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", certLink=" + certLink + ", isDeleted=" + isDeleted + "]";
	}

	public static class Id implements Serializable {

		private static final long serialVersionUID = 1L;

		@Column(name = "course_id")
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Integer courseId;

		@Column(name = "employee_id")
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Integer employeeId;

		public Id() {

		}

		public Id(Integer courseId, Integer employeeId) {
			this.courseId = courseId;
			this.employeeId = employeeId;
		}

		public Integer getCourseId() {
			return courseId;
		}

		public void setCourseId(Integer courseId) {
			this.courseId = courseId;
		}

		public Integer getEmployeeId() {
			return employeeId;
		}

		public void setEmployeeId(Integer employeeId) {
			this.employeeId = employeeId;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}

		@Override
		public String toString() {
			return "Id [courseId=" + courseId + ", employeeId=" + employeeId + "]";
		}

	}

}
