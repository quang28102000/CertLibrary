package fptProject.groupA.CertLibrary.persistence;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
public class CourseEmployee {

	@EmbeddedId
	private Id id;

	@Column(name = "status")
	private Integer status;

	@Column(name = "start_date", columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;

	@Column(name = "end_date", columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "course_id", referencedColumnName = "course_id", insertable = false, updatable = false)
	private Course course;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employee_id", referencedColumnName = "employee_id", insertable = false, updatable = false)
	private Employee employee;

	public CourseEmployee() {

	}

	public CourseEmployee(Id id, Integer status, Date startDate, Date endDate) {
		this.id = id;
		this.status = status;
		this.startDate = startDate;
		this.endDate = endDate;
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

	public Date getstartDate() {
		return startDate;
	}

	public void setstartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getendDate() {
		return endDate;
	}

	public void setendDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return "CourseEmployee [id=" + id + ", status=" + status + ", startDate=" + startDate + ", endDate=" + endDate
				+ "]";
	}

	@NoArgsConstructor
	@AllArgsConstructor
	@Embeddable
	public static class Id implements Serializable {

		private static final long serialVersionUID = 1L;

		@Column(name = "course_id")
		private Integer courseId;

		@Column(name = "employee_id")
		private Integer employeeId;

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

		@Override
		public String toString() {
			return "Id [courseId=" + courseId + ", employeeId=" + employeeId + "]";
		}
	}
}
