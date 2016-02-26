package bot.webordersapi.models.response;

/**
 * Created by andreyprvt on 10.02.16.
 */
public class Discount {
    double value;
    String unit;

    public Discount() {
	}
    
    public Discount(double value, String unit) {
        this.value = value;
        this.unit = unit;
    }

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Override
	public String toString() {
		return "Discount [value=" + value + ", unit=" + unit + "]";
	}
    
}
