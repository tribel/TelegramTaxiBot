package bot.webordersapi.models.response;

/**
 * Created by andreyprvt on 10.02.16.
 */
public class Discount {
    String value;
    String unit;

    public Discount(String value, String unit) {
        this.value = value;
        this.unit = unit;
    }
}
