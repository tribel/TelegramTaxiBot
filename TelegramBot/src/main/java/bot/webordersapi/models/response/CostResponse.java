package bot.webordersapi.models.response;

public class CostResponse {	
		
	private String dispatching_order_uid;
	private String currency;
	private double order_cost;
	private boolean discount_trip;
	private boolean can_pay_bonuses;
	
	public CostResponse() {
	}
	
	public CostResponse(String dispatching_order_uid, String currency,
			double order_cost) {

		this.dispatching_order_uid = dispatching_order_uid;
		this.currency = currency;
		this.order_cost = order_cost;
	}

	public String getDispatching_order_uid() {
		return dispatching_order_uid;
	}

	public void setDispatching_order_uid(String dispatching_order_uid) {
		this.dispatching_order_uid = dispatching_order_uid;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public double getOrder_cost() {
		return order_cost;
	}

	public void setOrder_cost(double order_cost) {
		this.order_cost = order_cost;
	}

	
	public boolean isDiscount_trip() {
		return discount_trip;
	}

	public void setDiscount_trip(boolean discount_trip) {
		this.discount_trip = discount_trip;
	}

	public boolean isCan_pay_bonuses() {
		return can_pay_bonuses;
	}

	public void setCan_pay_bonuses(boolean can_pay_bonuses) {
		this.can_pay_bonuses = can_pay_bonuses;
	}

	@Override
	public String toString() {
		return "CostResponse [dispatching_order_uid=" + dispatching_order_uid
				+ ", currency=" + currency + ", order_cost=" + order_cost
				+ ", discount_trip=" + discount_trip + ", can_pay_bonuses="
				+ can_pay_bonuses + "]";
	}

}
