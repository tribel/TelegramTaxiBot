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

    public Registration() {
	}
    
    public Registration(String phone, String confirm_code, String password, String confirm_password, String user_first_name) {
        this.phone = phone;
        this.confirm_code = confirm_code;
        this.password = password;
        this.confirm_password = confirm_password;
        this.user_first_name = user_first_name;
    }

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getConfirm_code() {
		return confirm_code;
	}

	public void setConfirm_code(String confirm_code) {
		this.confirm_code = confirm_code;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirm_password() {
		return confirm_password;
	}

	public void setConfirm_password(String confirm_password) {
		this.confirm_password = confirm_password;
	}

	public String getUser_first_name() {
		return user_first_name;
	}

	public void setUser_first_name(String user_first_name) {
		this.user_first_name = user_first_name;
	}
    
    public boolean isPasswordEquals() {
    	return password.equals(confirm_password);
    }


}
