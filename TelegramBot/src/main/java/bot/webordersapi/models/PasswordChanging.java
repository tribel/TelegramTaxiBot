package bot.webordersapi.models;

/**
 * Created by andreyprvt on 11.02.16.
 */
public class PasswordChanging {
    String oldPassword;
    String newPassword;
    String confirmNewPassword;

    public PasswordChanging(String oldPassword, String newPassword, String confirmNewPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
        this.confirmNewPassword = confirmNewPassword;
    }
}
