package th.co.collector.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "TRANS_CODE")
@TableGenerator(name="GEN_TRANS_CODE", initialValue=0, allocationSize=1)
public class TransCode {
	
	@Id
    @GeneratedValue(strategy=GenerationType.TABLE, generator="GEN_TRANS_CODE")
    private Long codeId;
	
	private String code;
	
	private Date createDate;

	public Long getCodeId() {
		return codeId;
	}

	public String getCode() {
		return code;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCodeId(Long codeId) {
		this.codeId = codeId;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
