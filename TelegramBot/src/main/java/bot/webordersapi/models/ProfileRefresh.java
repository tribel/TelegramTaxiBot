package bot.webordersapi.models;

public class ProfileRefresh {
	
	private String user_first_name;
	private String user_middle_name;
	private String user_last_name;
	private String route_address_from;
	private String route_address_number_from;
	private int route_address_entrance_from;
	private int route_address_apartment_from;
	
	public ProfileRefresh() {
	}

	public ProfileRefresh(String user_first_name, String user_middle_name,
			String user_last_name, String route_address_from,
			String route_address_number_from, int route_address_entrance_from,
			int route_address_apartment_from) {
		super();
		this.user_first_name = user_first_name;
		this.user_middle_name = user_middle_name;
		this.user_last_name = user_last_name;
		this.route_address_from = route_address_from;
		this.route_address_number_from = route_address_number_from;
		this.route_address_entrance_from = route_address_entrance_from;
		this.route_address_apartment_from = route_address_apartment_from;
	}

	public String getUser_first_name() {
		return user_first_name;
	}

	public void setUser_first_name(String user_first_name) {
		this.user_first_name = user_first_name;
	}

	public String getUser_middle_name() {
		return user_middle_name;
	}

	public void setUser_middle_name(String user_middle_name) {
		this.user_middle_name = user_middle_name;
	}

	public String getUser_last_name() {
		return user_last_name;
	}

	public void setUser_last_name(String user_last_name) {
		this.user_last_name = user_last_name;
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

	@Override
	public String toString() {
		return "ProfileRefresh [user_first_name=" + user_first_name
				+ ", user_middle_name=" + user_middle_name
				+ ", user_last_name=" + user_last_name
				+ ", route_address_from=" + route_address_from
				+ ", route_address_number_from=" + route_address_number_from
				+ ", route_address_entrance_from="
				+ route_address_entrance_from
				+ ", route_address_apartment_from="
				+ route_address_apartment_from + "]";
	}
	
	
}
