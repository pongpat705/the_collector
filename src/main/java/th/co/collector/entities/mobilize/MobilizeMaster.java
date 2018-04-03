package th.co.collector.entities.mobilize;

import java.math.BigDecimal;
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

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "MOBILIZE_MASTER")
@TableGenerator(name="GEN_MOBILIZE_MASTER", initialValue=0, allocationSize=1)
public class MobilizeMaster {

	@Id
    @GeneratedValue(strategy=GenerationType.TABLE, generator="GEN_MOBILIZE_MASTER")
    private Long mobilizeId;
	
	private String studentName;
	
	private String ref1;
	
	private String ref2;
	
	private BigDecimal totalAmount;
	
	private String totalText;
	
	@OneToMany(cascade=CascadeType.ALL)
	private List<Mobilize> mobilizes;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date createdDate;

	public Long getMobilizeId() {
		return mobilizeId;
	}

	public String getStudentName() {
		return studentName;
	}

	public String getRef1() {
		return ref1;
	}

	public String getRef2() {
		return ref2;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public String getTotalText() {
		return totalText;
	}

	public void setMobilizeId(Long mobilizeId) {
		this.mobilizeId = mobilizeId;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public void setRef1(String ref1) {
		this.ref1 = ref1;
	}

	public void setRef2(String ref2) {
		this.ref2 = ref2;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public void setTotalText(String totalText) {
		this.totalText = totalText;
	}

	public List<Mobilize> getMobilizes() {
		return mobilizes;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setMobilizes(List<Mobilize> mobilizes) {
		this.mobilizes = mobilizes;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
}
