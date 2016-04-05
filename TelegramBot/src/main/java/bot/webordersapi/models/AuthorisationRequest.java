package bot.webordersapi.models;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by andreyprvt on 10.02.16.
 */
public class AuthorisationRequest {

    private String login;
    private String password;


    public AuthorisationRequest(String login, String password) {
        this.login = login;
        this.password = computingSHA512password(password);

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
		this.password = computingSHA512password(password);
	}


	protected String computingSHA512password(String pswd){
		byte[] secretBytes = new String(pswd).getBytes();
		MessageDigest digest = null;
		
		try {
			digest = MessageDigest.getInstance("SHA-512");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		byte[] computeHash = digest.digest(secretBytes);
		String hash = new BigInteger(1, computeHash).toString(16);
        return hash;
    }

	@Override
	public String toString() {
		return "AuthorisationRequest [login=" + login + ", password="
				+ password + "]";
	}


}


