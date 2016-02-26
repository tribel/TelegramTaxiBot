package bot.webordersapi.models.response;

/**
 * Created by andreyprvt on 10.02.16.
 */
public class AuthorizationResponse {
	
	String login;
    String user_full_name;
    String user_first_name;
    String user_last_name;
    String user_middle_name;
    String user_phone;
    double  user_balance;
    String route_address_from;
    String route_address_number_from;
    int route_address_entrance_from;
    int route_address_apartment_from;
    String roles;
    String[] client_sub_cards;
    String version;
    Discount discount;
    int payment_type; // 1 - cash 0 - card
    double client_bonuses;

    
    public AuthorizationResponse() {
	}
    
    public AuthorizationResponse(String user_full_name, String user_phone, 
    		double user_balance, String route_address_from, String route_address_number_from, 
    		int route_address_entrance_from, int route_address_apartment_from, String version, String roles, 
    		String[] client_sum_cards, Discount discount, int payment_type, 
    		int client_bonuses) {
        this.user_full_name = user_full_name;
        this.user_phone = user_phone;
        this.user_balance = user_balance;
        this.route_address_from = route_address_from;
        this.route_address_number_from = route_address_number_from;
        this.route_address_entrance_from = route_address_entrance_from;
        this.route_address_apartment_from = route_address_apartment_from;
        this.version = version;
        this.roles = roles;
        this.client_sub_cards = client_sum_cards;
        this.discount = discount;
        this.payment_type = payment_type;
        this.client_bonuses = client_bonuses;
    }

	public String getUser_full_name() {
		return user_full_name;
	}

	public void setUser_full_name(String user_full_name) {
		this.user_full_name = user_full_name;
	}

	public String getUser_phone() {
		return user_phone;
	}

	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}

	public double getUser_balance() {
		return user_balance;
	}

	public void setUser_balance(double user_balance) {
		this.user_balance = user_balance;
	}

	public String getRoute_address_from() {
		return route_address_from;
	}

	public void setRoute_address_from(String route_address_from) {
		this.route_address_from = route_address_from;
	}

	public String getRoute_address_number_from() {
		return route_address_number_from;
	}

	public void setRoute_address_number_from(String route_address_number_from) {
		this.route_address_number_from = route_address_number_from;
	}

	public int getRoute_address_entrance_from() {
		return route_address_entrance_from;
	}

	public void setRoute_address_entrance_from(int route_address_entrance_from) {
		this.route_address_entrance_from = route_address_entrance_from;
	}

	public int getRoute_address_apartment_from() {
		return route_address_apartment_from;
	}

	public void setRoute_address_apartment_from(int route_address_apartment_from) {
		this.route_address_apartment_from = route_address_apartment_from;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String[] getClient_sum_cards() {
		return client_sub_cards;
	}

	public void setClient_sum_cards(String[] client_sum_cards) {
		this.client_sub_cards = client_sum_cards;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Discount getDiscount() {
		return discount;
	}

	public void setDiscount(Discount discount) {
		this.discount = discount;
	}


	public int getPayment_type() {
		return payment_type;
	}

	public void setPayment_type(int payment_type) {
		this.payment_type = payment_type;
	}

	public double getClient_bonuses() {
		return client_bonuses;
	}

	public void setClient_bonuses(double client_bonuses) {
		this.client_bonuses = client_bonuses;
	}

	@Override
	public String toString() {
		return "AuthorizationResponse [user_full_name=" + user_full_name
				+ ", user_phone=" + user_phone + ", user_balance="
				+ user_balance + ", route_address_from=" + route_address_from
				+ ", route_address_number_from=" + route_address_number_from
				+ ", route_address_entrance_from="
				+ route_address_entrance_from
				+ ", route_address_apartment_from="
				+ route_address_apartment_from + ", roles=" + roles
				+ ", client_sub_cards=" + client_sub_cards + ", version="
				+ version + ", discount=" + discount + ", payment_type="
				+ payment_type + ", client_bonuses=" + client_bonuses + "]";
	}
}

