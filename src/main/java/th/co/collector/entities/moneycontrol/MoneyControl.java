package th.co.collector.entities.moneycontrol;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import th.co.collector.entities.BaseDomain;

@Entity
@Table(name = "MONEY_CONTROL")
@TableGenerator(name="GEN_MONEY_CONTROL", initialValue=0, allocationSize=1)
public class MoneyControl extends BaseDomain{

	@Id
    @GeneratedValue(strategy=GenerationType.TABLE, generator="GEN_MONEY_CONTROL")
    private Long controlId;
	
	private String controlType;
	
	private Date entryDate;
	
	private String docNo;
	
	private String description;
	
	private BigDecimal cashIn;
	
	private BigDecimal cashOut;
	
	private BigDecimal balance;
	
	private BigDecimal temporary;
	
	private BigDecimal compensation;
	
	private BigDecimal usability;
	
	private BigDecimal utility;
	
	private BigDecimal material;
	
	private BigDecimal durable;
	
	private BigDecimal landBuild;
	
	private BigDecimal subsidy;
	
	private String remark;

	public Long getControlId() {
		return controlId;
	}

	public String getControlType() {
		return controlType;
	}

	public Date getEntryDate() {
		return entryDate;
	}

	public String getDocNo() {
		return docNo;
	}

	public String getDescription() {
		return description;
	}

	protected BigDecimal getCashIn() {
		return cashIn;
	}

	protected BigDecimal getCashOut() {
		return cashOut;
	}

	protected void setCashIn(BigDecimal cashIn) {
		this.cashIn = cashIn;
	}

	protected void setCashOut(BigDecimal cashOut) {
		this.cashOut = cashOut;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public BigDecimal getTemporary() {
		return temporary;
	}

	public BigDecimal getCompensation() {
		return compensation;
	}

	public BigDecimal getUsability() {
		return usability;
	}

	public BigDecimal getUtility() {
		return utility;
	}

	public BigDecimal getMaterial() {
		return material;
	}

	public BigDecimal getDurable() {
		return durable;
	}

	public BigDecimal getLandBuild() {
		return landBuild;
	}

	public BigDecimal getSubsidy() {
		return subsidy;
	}

	public void setControlId(Long controlId) {
		this.controlId = controlId;
	}

	public void setControlType(String controlType) {
		this.controlType = controlType;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	public void setDocNo(String docNo) {
		this.docNo = docNo;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public void setTemporary(BigDecimal temporary) {
		this.temporary = temporary;
	}

	public void setCompensation(BigDecimal compensation) {
		this.compensation = compensation;
	}

	public void setUsability(BigDecimal usability) {
		this.usability = usability;
	}

	public void setUtility(BigDecimal utility) {
		this.utility = utility;
	}

	public void setMaterial(BigDecimal material) {
		this.material = material;
	}

	public void setDurable(BigDecimal durable) {
		this.durable = durable;
	}

	public void setLandBuild(BigDecimal landBuild) {
		this.landBuild = landBuild;
	}

	public void setSubsidy(BigDecimal subsidy) {
		this.subsidy = subsidy;
	}

	protected String getRemark() {
		return remark;
	}

	protected void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
