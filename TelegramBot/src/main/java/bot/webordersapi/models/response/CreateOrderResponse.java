package bot.webordersapi.models.response;

public class CreateOrderResponse {

	private String dispatching_order_uid;
	private int find_car_timeout;
	private int find_car_delay;
	
	public CreateOrderResponse() {
	}

	public CreateOrderResponse(String dispatching_order_uid,
			int find_car_timeout, int find_car_delay) {
	
		this.dispatching_order_uid = dispatching_order_uid;
		this.find_car_timeout = find_car_timeout;
		this.find_car_delay = find_car_delay;
	}

	public String getDispatching_order_uid() {
		return dispatching_order_uid;
	}

	public void setDispatching_order_uid(String dispatching_order_uid) {
		this.dispatching_order_uid = dispatching_order_uid;
	}

	public int getFind_car_timeout() {
		return find_car_timeout;
	}

	public void setFind_car_timeout(int find_car_timeout) {
		this.find_car_timeout = find_car_timeout;
	}

	public int getFind_car_delay() {
		return find_car_delay;
	}

	public void setFind_car_delay(int find_car_delay) {
		this.find_car_delay = find_car_delay;
	}

	@Override
	public String toString() {
		return "CreateOrderResponse [dispatching_order_uid="
				+ dispatching_order_uid + ", find_car_timeout="
				+ find_car_timeout + ", find_car_delay=" + find_car_delay + "]";
	}
	
}
