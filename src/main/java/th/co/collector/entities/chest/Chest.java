package th.co.collector.entities.chest;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import th.co.collector.entities.BaseDomain;

@Entity
@Table(name = "CHEST")
@TableGenerator(name="GEN_CHEST", initialValue=0, allocationSize=1)
public class Chest extends BaseDomain{

	@Id
    @GeneratedValue(strategy=GenerationType.TABLE, generator="GEN_CHEST")
    private Long chestId;
	
	private Long accountCode;
	
	private String accountName;
	
	private BigDecimal balance;

	protected Long getChestId() {
		return chestId;
	}

	protected Long getAccountCode() {
		return accountCode;
	}

	protected String getAccountName() {
		return accountName;
	}

	protected BigDecimal getBalance() {
		return balance;
	}

	protected void setChestId(Long chestId) {
		this.chestId = chestId;
	}

	protected void setAccountCode(Long accountCode) {
		this.accountCode = accountCode;
	}

	protected void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	protected void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	
}
