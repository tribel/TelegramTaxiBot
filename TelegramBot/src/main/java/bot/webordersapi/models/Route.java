package bot.webordersapi.models;

import java.util.ArrayList;

/**
 * Created by andreyprvt on 29.01.16.
 */
public class Route {

	// only two fields without array

    ArrayList<Address> addresses;

    public Route(){
        addresses = new ArrayList<Address>();
    }

    public boolean isValid(){
        if (addresses.size() < 2)
            return false;
        return true;
    }

    public ArrayList<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(ArrayList<Address> addresses) {
        this.addresses = addresses;
    }

    @Override
    public String toString(){
        if (!isValid())
            return "Error";
        StringBuilder sb = new StringBuilder();
        sb.append("["+'\n');
        for (int i = 1; i < addresses.size(); i++) {
            sb.append("," + addresses.get(i).toString());
        }
        sb.append("]"+"\n");
        return sb.toString();
    }


}
