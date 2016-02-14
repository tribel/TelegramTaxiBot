package bot.webordersapi.models;

/**
 * Created by andreyprvt on 10.02.16.
 */
public class AuthorisationRequest {

    private String login;
    private String password;
    private String SHA512password;


    public AuthorisationRequest(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String ComputingSHA512password(){

        return "aaa";
    }

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSHA512password() {
		return SHA512password;
	}

	public void setSHA512password(String sHA512password) {
		SHA512password = sHA512password;
	}
    
    

}


