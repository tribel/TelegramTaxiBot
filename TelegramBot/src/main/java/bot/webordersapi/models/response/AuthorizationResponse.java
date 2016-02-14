package bot.webordersapi.models.response;

/**
 * Created by andreyprvt on 10.02.16.
 */
public class AuthorizationResponse {

    String user_full_name;
    String user_phone;
    int user_balance;
    String route_address_from;
    String route_address_number_from;
    int route_address_entrance_from;
    int route_address_apartment_from;
    String roles;
    int client_sum_cards;
    int version;
    Discount discount;
    int discount_value;
    String discount_unit;
    int payment_type; // 1 - cash 0 - card
    int client_bonuses;

    public AuthorizationResponse(String user_full_name, String user_phone, int user_balance, String route_address_from, String route_address_number_from, int route_address_entrance_from, int route_address_apartment_from, int version, String roles, int client_sum_cards, Discount discount, int discount_value, String discount_unit, int payment_type, int client_bonuses) {
        this.user_full_name = user_full_name;
        this.user_phone = user_phone;
        this.user_balance = user_balance;
        this.route_address_from = route_address_from;
        this.route_address_number_from = route_address_number_from;
        this.route_address_entrance_from = route_address_entrance_from;
        this.route_address_apartment_from = route_address_apartment_from;
        this.version = version;
        this.roles = roles;
        this.client_sum_cards = client_sum_cards;
        this.discount = discount;
        this.discount_value = discount_value;
        this.discount_unit = discount_unit;
        this.payment_type = payment_type;
        this.client_bonuses = client_bonuses;
    }




}

