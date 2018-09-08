package th.co.collector.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "STUDENT")
@TableGenerator(name="GEN_STUDENT", initialValue=0, allocationSize=1)
public class Student {

	
	@Id
    @GeneratedValue(strategy=GenerationType.TABLE, generator="GEN_STUDENT")
    private Long id;
	
	private String stName;
	
	private String stGrade;
	
	private String stNatid;
	
	private char active;
	
	private String createdBy;
	
	private Date createdDate;
	
	private String updatedBy;
	
	private Date updatedDate;

	public Long getId() {
		return id;
	}

	public String getStName() {
		return stName;
	}

	public String getStGrade() {
		return stGrade;
	}

	public String getStNatid() {
		return stNatid;
	}

	public char getActive() {
		return active;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setStName(String stName) {
		this.stName = stName;
	}

	public void setStGrade(String stGrade) {
		this.stGrade = stGrade;
	}

	public void setStNatid(String stNatid) {
		this.stNatid = stNatid;
	}

	public void setActive(char active) {
		this.active = active;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
}
