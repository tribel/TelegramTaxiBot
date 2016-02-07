package bot.webordersapi.models;

/**
 * Created by andreyprvt on 29.01.16.
 */
public class Address {

    private String name;
    private String number;
    private String lat;
    private String lng;

    public Address() {
	}
    
    public Address(String name, String number, String lat, String lng) {
		super();
		this.name = name;
		this.number = number;
		this.lat = lat;
		this.lng = lng;
	}

	public Address(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

	@Override
	public String toString() {
		return "Address [name=" + name + ", number=" + number + ", lat=" + lat
				+ ", lng=" + lng + "]";
	}


}
