package th.co.collector.entities.chest;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "CHEST")
@TableGenerator(name="GEN_CHEST", initialValue=0, allocationSize=1)
public class Chest{

	@Id
    @GeneratedValue(strategy=GenerationType.TABLE, generator="GEN_CHEST")
    private Long chestId;
	
	private Long accountCode;
	
	private String accountName;
	
	private BigDecimal balance;
	
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
	

	public Long getChestId() {
		return chestId;
	}

	public Long getAccountCode() {
		return accountCode;
	}

	public String getAccountName() {
		return accountName;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setChestId(Long chestId) {
		this.chestId = chestId;
	}

	public void setAccountCode(Long accountCode) {
		this.accountCode = accountCode;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	
}
