package fptProject.groupA.CertLibrary.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "skills")
@NamedQueries({
	@NamedQuery(name = "GET_ALL_SKILLS", query = "FROM Skill")
})
public class Skill {

	public static final String GET_ALL_SKILLS = "GET_ALL_SKILLS";	
	
	@Id
	@Column(name = "skill_id")
	private Integer id;
	
	@Column(name = "skill_name")
	private String name;

	public Skill() {

	}

	public Skill(Integer id, String name) {
		this.id = id;
		this.name = name;
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

}
