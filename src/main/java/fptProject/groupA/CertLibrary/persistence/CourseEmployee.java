package fptProject.groupA.CertLibrary.persistence;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CourseEmployee {

	@JsonProperty("courseId")
	private Integer courseId;

	@JsonProperty("employeeId")
	private Integer employeeId;

	private Integer status;

	@JsonProperty("startDate")
//	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "YYYY-MM-DD hh:mm:ss")
	private Date startDate;

	@JsonIgnoreProperties(ignoreUnknown = true)
	private Date endDate;

	private String certLink;

	private Integer isDeleted;

	public CourseEmployee() {

	}

	public CourseEmployee(Integer courseId, Integer employeeId, Integer status, Date startDate, Date endDate,
			String certLink, Integer isDeleted) {
		this.courseId = courseId;
		this.employeeId = employeeId;
		this.status = status;
		this.startDate = startDate;
		this.endDate = endDate;
		this.certLink = certLink;
		this.isDeleted = isDeleted;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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
		return "CourseEmployee [courseId=" + courseId + ", employeeId=" + employeeId + ", status=" + status
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", certLink=" + certLink + ", isDeleted="
				+ isDeleted + "]";
	}

}
