package bot.webordersapi.models;

/**
 * Created by andreyprvt on 11.02.16.
 */
public class Registration {

    String phone;
    String confirm_code;
    String password;
    String confirm_password;
    String user_first_name;

    public Registration(String phone, String confirm_code, String password, String confirm_password, String user_first_name) {
        this.phone = phone;
        this.confirm_code = confirm_code;
        this.password = password;
        this.confirm_password = confirm_password;
        this.user_first_name = user_first_name;
    }


}
