package th.co.collector.entities.moneycontrol;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "BALANCE")
@TableGenerator(name="GEN_BALANCE", initialValue=0, allocationSize=1)
public class Balance{

	@Id
    @GeneratedValue(strategy=GenerationType.TABLE, generator="GEN_BALANCE")
    private Long balanceId;
	
	private String balanceType;
	
	private String balanceNumber;
	
	private String balanceSuffix;
	
	private BigDecimal amount;
	
	private String remark;

	protected Long getBalanceId() {
		return balanceId;
	}

	protected String getBalanceType() {
		return balanceType;
	}

	protected String getBalanceNumber() {
		return balanceNumber;
	}

	protected String getBalanceSuffix() {
		return balanceSuffix;
	}

	protected BigDecimal getAmount() {
		return amount;
	}

	protected String getRemark() {
		return remark;
	}

	protected void setBalanceId(Long balanceId) {
		this.balanceId = balanceId;
	}

	protected void setBalanceType(String balanceType) {
		this.balanceType = balanceType;
	}

	protected void setBalanceNumber(String balanceNumber) {
		this.balanceNumber = balanceNumber;
	}

	protected void setBalanceSuffix(String balanceSuffix) {
		this.balanceSuffix = balanceSuffix;
	}

	protected void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	protected void setRemark(String remark) {
		this.remark = remark;
	}
}
