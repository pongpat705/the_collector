package th.co.collector.entities.moneycontrol;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "SCHOOL_BUDGET")
@TableGenerator(name="GEN_SCHOOL_BUDGET", initialValue=0, allocationSize=1)
public class SchoolBudget{

	@Id
    @GeneratedValue(strategy=GenerationType.TABLE, generator="GEN_SCHOOL_BUDGET")
    private Long budgetId;
	
	private Date entryDate;
	
	private String docNo;
	
	private String description;
	
	private BigDecimal income;
	
	private BigDecimal extra;
	
	private BigDecimal interest;
	
	private BigDecimal other;
	
	private BigDecimal sum;
	
	private String remark;
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

	protected Long getBudgetId() {
		return budgetId;
	}

	protected Date getEntryDate() {
		return entryDate;
	}

	protected String getDocNo() {
		return docNo;
	}

	protected String getDescription() {
		return description;
	}

	protected BigDecimal getIncome() {
		return income;
	}

	protected BigDecimal getExtra() {
		return extra;
	}

	protected BigDecimal getInterest() {
		return interest;
	}

	protected BigDecimal getOther() {
		return other;
	}

	protected BigDecimal getSum() {
		return sum;
	}

	protected String getRemark() {
		return remark;
	}

	protected void setBudgetId(Long budgetId) {
		this.budgetId = budgetId;
	}

	protected void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	protected void setDocNo(String docNo) {
		this.docNo = docNo;
	}

	protected void setDescription(String description) {
		this.description = description;
	}

	protected void setIncome(BigDecimal income) {
		this.income = income;
	}

	protected void setExtra(BigDecimal extra) {
		this.extra = extra;
	}

	protected void setInterest(BigDecimal interest) {
		this.interest = interest;
	}

	protected void setOther(BigDecimal other) {
		this.other = other;
	}

	protected void setSum(BigDecimal sum) {
		this.sum = sum;
	}

	protected void setRemark(String remark) {
		this.remark = remark;
	}
}
