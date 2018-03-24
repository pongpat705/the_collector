package th.co.collector.entities.moneycontrol;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "SCHOOL_BUDGET")
@TableGenerator(name="GEN_SCHOOL_BUDGET", initialValue=0, allocationSize=1)
public class SchoolBudget{

	@Id
    @GeneratedValue(strategy=GenerationType.TABLE, generator="GEN_SCHOOL_BUDGET")
    private Long budgetId;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date entryDate;
	
	private String docNo;
	
	private String recordNo;
	
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

	public Long getBudgetId() {
		return budgetId;
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

	public BigDecimal getIncome() {
		return income;
	}

	public BigDecimal getExtra() {
		return extra;
	}

	public BigDecimal getInterest() {
		return interest;
	}

	public BigDecimal getOther() {
		return other;
	}

	public BigDecimal getSum() {
		return sum;
	}

	public String getRemark() {
		return remark;
	}

	public void setBudgetId(Long budgetId) {
		this.budgetId = budgetId;
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

	public void setIncome(BigDecimal income) {
		this.income = income;
	}

	public void setExtra(BigDecimal extra) {
		this.extra = extra;
	}

	public void setInterest(BigDecimal interest) {
		this.interest = interest;
	}

	public void setOther(BigDecimal other) {
		this.other = other;
	}

	public void setSum(BigDecimal sum) {
		this.sum = sum;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getRecordNo() {
		return recordNo;
	}
	public void setRecordNo(String recordNo) {
		this.recordNo = recordNo;
	}
}
