package bot.webordersapi.models.response;

/**
 * Created by andreyprvt on 11.02.16.
 */
public class ModelState {

    String changePasswordOldPassword;
    String changePasswordNewPassword;
    String changePasswordRepeatNewPassword;

    public String getChangePasswordOldPassword() {
        return changePasswordOldPassword;
    }

    public void setChangePasswordOldPassword(String changePasswordOldPassword) {
        this.changePasswordOldPassword = changePasswordOldPassword;
    }

    public String getChangePasswordNewPassword() {
        return changePasswordNewPassword;
    }

    public void setChangePasswordNewPassword(String changePasswordNewPassword) {
        this.changePasswordNewPassword = changePasswordNewPassword;
    }

    public String getChangePasswordRepeatNewPassword() {
        return changePasswordRepeatNewPassword;
    }

    public void setChangePasswordRepeatNewPassword(String changePasswordRepeatNewPassword) {
        this.changePasswordRepeatNewPassword = changePasswordRepeatNewPassword;
    }
}
