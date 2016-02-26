package bot.webordersapi.models;

/**
 * Created by andreyprvt on 29.01.16.
 */
public class Address {

    private String name;
    private String number;
    private Double lat;
    private Double lng;

    public Address() {
	}
    
    public Address(String name, String number, double lat, double lng) {
		super();
		this.name = name;
		this.number = number;
		this.lat = lat;
		this.lng = lng;
	}

    public Address(String name, String number) {
    	this.name = name;
    	this.number = number;
    }
    
    public Address(String name, double lat, double lng) {
    	this.name = name;
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

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

	@Override
	public String toString() {
		return "Address [name=" + name + ", number=" + number + ", lat=" + lat
				+ ", lng=" + lng + "]";
	}


}
