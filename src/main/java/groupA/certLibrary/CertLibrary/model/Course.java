package groupA.certLibrary.CertLibrary.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Course implements Serializable {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private int course_id;
    private String course_tittle;
    private String platform;
    private String category;

    public Course() {}

    public Course(int course_id, String course_tittle, String platform, String category) {
        this.course_id = course_id;
        this.course_tittle = course_tittle;
        this.platform = platform;
        this.category = category;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public String getCourse_tittle() {
        return course_tittle;
    }

    public void setCourse_tittle(String course_title) {
        this.course_tittle = course_title;
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
        return "Course{" +
                "course_id=" + course_id +
                ", course_title='" + course_tittle + '\'' +
                ", platform='" + platform + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
