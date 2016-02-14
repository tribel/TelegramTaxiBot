package bot.webordersapi.models.response;

/**
 * Created by andreyprvt on 11.02.16.
 */
public class ErrorResponse {

    String message;
    int id;

    public ErrorResponse(String message, int id) {
        this.message = message;
        this.id = id;
    }


}
