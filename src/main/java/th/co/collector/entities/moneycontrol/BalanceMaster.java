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

import th.co.collector.entities.BaseDomain;

@Entity
@Table(name = "BALANCE_MASTER")
@TableGenerator(name="GEN_BALANCE_MASTER", initialValue=0, allocationSize=1)
public class BalanceMaster extends BaseDomain{

	@Id
    @GeneratedValue(strategy=GenerationType.TABLE, generator="GEN_BALANCE_MASTER")
	private Long masterId;
	
	private String department;
	
	private String amphur;
	
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

	protected Long getMasterId() {
		return masterId;
	}

	protected String getDepartment() {
		return department;
	}

	protected String getAmphur() {
		return amphur;
	}

	protected Date getCreateDate() {
		return createDate;
	}

	protected List<Balance> getBalances() {
		return balances;
	}

	protected String getTotalText() {
		return totalText;
	}

	protected String getCreateBy() {
		return createBy;
	}

	protected String getCommittee1() {
		return committee1;
	}

	protected String getCommittee2() {
		return committee2;
	}

	protected String getCommittee3() {
		return committee3;
	}

	protected Date getReceiveDate() {
		return receiveDate;
	}

	protected String getReceiveBy() {
		return receiveBy;
	}

	protected String getSupervisor() {
		return supervisor;
	}

	protected void setMasterId(Long masterId) {
		this.masterId = masterId;
	}

	protected void setDepartment(String department) {
		this.department = department;
	}

	protected void setAmphur(String amphur) {
		this.amphur = amphur;
	}

	protected void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	protected void setBalances(List<Balance> balances) {
		this.balances = balances;
	}

	protected void setTotalText(String totalText) {
		this.totalText = totalText;
	}

	protected void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	protected void setCommittee1(String committee1) {
		this.committee1 = committee1;
	}

	protected void setCommittee2(String committee2) {
		this.committee2 = committee2;
	}

	protected void setCommittee3(String committee3) {
		this.committee3 = committee3;
	}

	protected void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}

	protected void setReceiveBy(String receiveBy) {
		this.receiveBy = receiveBy;
	}

	protected void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}
	
	
	
}
