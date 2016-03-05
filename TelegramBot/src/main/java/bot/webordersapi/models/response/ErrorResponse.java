package bot.webordersapi.models.response;

/**
 * Created by andreyprvt on 11.02.16.
 */
public class ErrorResponse {
    
	int id;
    String message;
    
    public ErrorResponse() {
	}
    

	public ErrorResponse(String message, int id) {
        this.message = message;
        this.id = id;
    }
    
    public void setId(int id) {
		this.id = id;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	public String getMessage() {
		return message;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "ErrorResponse [message=" + message + ", id=" + id + "]";
	}
    
}
