package th.co.collector.entities.moneycontrol;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "BALANCE_MASTER")
@TableGenerator(name="GEN_BALANCE_MASTER", initialValue=0, allocationSize=1)
public class BalanceMaster{

	@Id
    @GeneratedValue(strategy=GenerationType.TABLE, generator="GEN_BALANCE_MASTER")
	private Long masterId;
	
	private String department;
	
	private String amphur;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date createDate;
	
	@OneToMany(cascade=CascadeType.ALL)
	private List<Balance> balances;
	
	private String totalText;
	
	private String createBy;
	
	private String committee1;
	
	private String committee2;
	
	private String committee3;
	
	private Date receiveDate;
	
	private String receiveBy;
	
	private String supervisor;
	
	private String recordBy;
	private String reviewBy;
	private String approveBy;
	
	
	public String getReviewBy() {
		return reviewBy;
	}
	public String getApproveBy() {
		return approveBy;
	}
	public void setReviewBy(String reviewBy) {
		this.reviewBy = reviewBy;
	}
	public void setApproveBy(String approveBy) {
		this.approveBy = approveBy;
	}
	public String getRecordBy() {
		return recordBy;
	}
	public void setRecordBy(String recordBy) {
		this.recordBy = recordBy;
	}

	public Long getMasterId() {
		return masterId;
	}

	public String getDepartment() {
		return department;
	}

	public String getAmphur() {
		return amphur;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public List<Balance> getBalances() {
		return balances;
	}

	public String getTotalText() {
		return totalText;
	}

	public String getCreateBy() {
		return createBy;
	}

	public String getCommittee1() {
		return committee1;
	}

	public String getCommittee2() {
		return committee2;
	}

	public String getCommittee3() {
		return committee3;
	}

	public Date getReceiveDate() {
		return receiveDate;
	}

	public String getReceiveBy() {
		return receiveBy;
	}

	public String getSupervisor() {
		return supervisor;
	}

	public void setMasterId(Long masterId) {
		this.masterId = masterId;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public void setAmphur(String amphur) {
		this.amphur = amphur;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public void setBalances(List<Balance> balances) {
		this.balances = balances;
	}

	public void setTotalText(String totalText) {
		this.totalText = totalText;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public void setCommittee1(String committee1) {
		this.committee1 = committee1;
	}

	public void setCommittee2(String committee2) {
		this.committee2 = committee2;
	}

	public void setCommittee3(String committee3) {
		this.committee3 = committee3;
	}

	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}

	public void setReceiveBy(String receiveBy) {
		this.receiveBy = receiveBy;
	}

	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}
	
	
	
}
