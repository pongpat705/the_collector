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

	public Long getBalanceId() {
		return balanceId;
	}

	public String getBalanceType() {
		return balanceType;
	}

	public String getBalanceNumber() {
		return balanceNumber;
	}

	public String getBalanceSuffix() {
		return balanceSuffix;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public String getRemark() {
		return remark;
	}

	public void setBalanceId(Long balanceId) {
		this.balanceId = balanceId;
	}

	public void setBalanceType(String balanceType) {
		this.balanceType = balanceType;
	}

	public void setBalanceNumber(String balanceNumber) {
		this.balanceNumber = balanceNumber;
	}

	public void setBalanceSuffix(String balanceSuffix) {
		this.balanceSuffix = balanceSuffix;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
