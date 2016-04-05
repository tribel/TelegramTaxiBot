package bot.webordersapi.models;


public class CreateOrder extends Order{

	private double pay_bonuses;
	private String app_registration_token;
	
	
	public CreateOrder() {
		super();
	}
	
	public CreateOrder(double pay_bonuses, String app_registration_token, Order order) {
		super();
		this.user_full_name = order.user_full_name;
		this.user_phone = order.user_phone;
		this.reservation = order.reservation;
		this.required_time = order.required_time;
		this.comment = order.comment;
		this.flexible_tariff_name = order.flexible_tariff_name;
		this.wagon = order.wagon;
		this.minibus = order.minibus;
		this.premium = order.premium;
		this.baggage = order.baggage;
		this.animal = order.animal;
		this.conditioner = order.conditioner;
		this.courier_delivery = order.courier_delivery;
		this.route_undefined = order.route_undefined;
		this.terminal = order.terminal;
		this.reciept = order.reciept;
		this.route = order.route;
		this.route_address_entrance_from = order.route_address_entrance_from;
		this.client_sub_card = order.client_sub_card;
		this.add_cost = order.add_cost;
		this.taxiColumnId = order.taxiColumnId;
		this.paymentType = order.paymentType;
		this.pay_bonuses = pay_bonuses;
		this.app_registration_token = app_registration_token;
	}

	public double getPay_bonuses() {
		return pay_bonuses;
	}

	public void setPay_bonuses(double pay_bonuses) {
		this.pay_bonuses = pay_bonuses;
	}

	public String getApp_registration_token() {
		return app_registration_token;
	}

	public void setApp_registration_token(String app_registration_token) {
		this.app_registration_token = app_registration_token;
	}

	@Override
	public String toString() {
		return "CreateOrder [pay_bonuses=" + pay_bonuses
				+ ", app_registration_token=" + app_registration_token + "]";
	}

	
}