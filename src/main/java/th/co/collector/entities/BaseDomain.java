package th.co.collector.entities;

public class BaseDomain {

	private String createBy;
	
	private String reviewBy;
	
	private String approveBy;

	protected String getCreateBy() {
		return createBy;
	}

	protected String getReviewBy() {
		return reviewBy;
	}

	protected String getApproveBy() {
		return approveBy;
	}

	protected void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	protected void setReviewBy(String reviewBy) {
		this.reviewBy = reviewBy;
	}

	protected void setApproveBy(String approveBy) {
		this.approveBy = approveBy;
	}
	
	
}
