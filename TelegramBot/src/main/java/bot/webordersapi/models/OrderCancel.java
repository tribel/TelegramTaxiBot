package bot.webordersapi.models;

public class OrderCancel {
	
	private String uid;
	
	public OrderCancel() {
	}

	public OrderCancel(String uid) {
		super();
		this.uid = uid;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}
	
	
}
