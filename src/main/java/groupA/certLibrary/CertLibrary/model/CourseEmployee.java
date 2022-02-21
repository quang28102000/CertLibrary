package groupA.certLibrary.CertLibrary.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
public class CourseEmployee implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private Id id;	

	private Integer status;
	
	@Column(name = "start_date", columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date start_date;
	
	@Column(name = "end_date", columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date end_date;
	
	public CourseEmployee() {}
	
	@Embeddable
	public static class Id implements Serializable {

		private static final long serialVersionUID = 1L;
		
		@Column(name = "course_id")
		private Integer course_id;	
		
		@Column(name = "employee_id")
		private Integer employee_id;
		
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

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	@Override
	public String toString() {
		return "CourseEmployee [id=" + id + ", status=" + status + ", start_date=" + start_date + ", end_date="
				+ end_date + "]";
	}
	
}

