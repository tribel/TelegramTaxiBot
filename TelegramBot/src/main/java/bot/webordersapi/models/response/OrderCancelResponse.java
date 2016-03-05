package bot.webordersapi.models.response;

public class OrderCancelResponse {
	
	private String dispatching_order_uid;
	private int order_client_cancel_result;
	
	public OrderCancelResponse() {
	}

	public OrderCancelResponse(String dispatching_order_uid,
			int order_client_cancel_result) {
		this.dispatching_order_uid = dispatching_order_uid;
		this.order_client_cancel_result = order_client_cancel_result;
	}

	public String getDispatching_order_uid() {
		return dispatching_order_uid;
	}

	public void setDispatching_order_uid(String dispatching_order_uid) {
		this.dispatching_order_uid = dispatching_order_uid;
	}

	public int getOrder_client_cancel_result() {
		return order_client_cancel_result;
	}

	public void setOrder_client_cancel_result(int order_client_cancel_result) {
		this.order_client_cancel_result = order_client_cancel_result;
	}

	@Override
	public String toString() {
		return "OrderCancelResponse [dispatching_order_uid="
				+ dispatching_order_uid + ", order_client_cancel_result="
				+ order_client_cancel_result + "]";
	}
	
	
}
