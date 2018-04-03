package th.co.collector.entities.mobilize;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "MOBILIZE")
@TableGenerator(name="GEN_MOBILIZE", initialValue=0, allocationSize=1)
public class Mobilize {
	
	@Id
    @GeneratedValue(strategy=GenerationType.TABLE, generator="GEN_MOBILIZE")
    private Long mobilizeId;

	private Integer no;
	
	private String description;
	
	private BigDecimal amount;

	public Long getMobilizeId() {
		return mobilizeId;
	}

	public Integer getNo() {
		return no;
	}

	public String getDescription() {
		return description;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setMobilizeId(Long mobilizeId) {
		this.mobilizeId = mobilizeId;
	}

	public void setNo(Integer no) {
		this.no = no;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	
}
