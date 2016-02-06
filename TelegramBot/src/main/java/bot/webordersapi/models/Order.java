package bot.webordersapi.models;


/**
 * Created by andreyprvt on 29.01.16.
 */
public class Order {

    private long id; // order id
    private String user_full_name; //
    private String user_phone_number;
    private boolean reservation;
    private String required_time;
    private String comment;
    private String flexible_tariff_name;
    private boolean wagon;
    private boolean minibus;
    private boolean premium;
    private boolean baggage;
    private boolean animal;
    private boolean conditioner;
    private boolean courier_delivery;
    private boolean route_undefined;
    private boolean terminal;
    private boolean reciept;
    private Route route;
    private String route_address_entrance_from;
    private String client_sub_card;
    private int add_cost;
    private int taxiColumnId;
    private int paymentType;



    public Order(boolean reservation, Route route, int taxiColumnId){
        this.reservation = reservation;
        this.route = new Route();
        this.route = route;
        this.taxiColumnId = taxiColumnId;
    }



    public String getFlexible_tariff_name() {
        return flexible_tariff_name;
    }

    public void setFlexible_tariff_name(String flexible_tariff_name) {
        this.flexible_tariff_name = flexible_tariff_name;
    }


    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getRoute_address_entrance_from() {
        return route_address_entrance_from;
    }

    public void setRoute_address_entrance_from(String route_address_entrance_from) {
        this.route_address_entrance_from = route_address_entrance_from;
    }

    public String getClient_sub_card() {
        return client_sub_card;
    }

    public void setClient_sub_card(String client_sub_card) {
        this.client_sub_card = client_sub_card;
    }

    public int getAdd_cost() {
        return add_cost;
    }

    public void setAdd_cost(int add_cost) {
        this.add_cost = add_cost;
    }

    public int getTaxiColumnId() {
        return taxiColumnId;
    }

    public void setTaxiColumnId(int taxiColumnId) {
        this.taxiColumnId = taxiColumnId;
    }

    public int getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(int paymentType) {
        this.paymentType = paymentType;
    }

    public boolean isAnimal() {
        return animal;
    }

    public void setAnimal(boolean animal) {
        this.animal = animal;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUser_full_name() {
        return user_full_name;
    }

    public void setUser_full_name(String user_full_name) {
        this.user_full_name = user_full_name;
    }

    public String getUser_phone_number() {
        return user_phone_number;
    }

    public void setUser_phone_number(String user_phone_number) {
        this.user_phone_number = user_phone_number;
    }

    public boolean isReservation() {
        return reservation;
    }

    public void setReservation(boolean reservation) {
        this.reservation = reservation;
    }

    public String getRequired_time() {
        return required_time;
    }

    public void setRequired_time(String required_time) {
        this.required_time = required_time;
    }


    public boolean isMinibus() {
        return minibus;
    }

    public void setMinibus(boolean minibus) {
        this.minibus = minibus;
    }

    public boolean isWagon() {
        return wagon;
    }

    public void setWagon(boolean wagon) {
        this.wagon = wagon;
    }

    public boolean isPremium() {
        return premium;
    }

    public void setPremium(boolean premium) {
        this.premium = premium;
    }

    public boolean isBaggage() {
        return baggage;
    }

    public void setBaggage(boolean baggage) {
        this.baggage = baggage;
    }

    public boolean isConditioner() {
        return conditioner;
    }

    public void setConditioner(boolean conditioner) {
        this.conditioner = conditioner;
    }

    public boolean isCourier_delivery() {
        return courier_delivery;
    }

    public void setCourier_delivery(boolean courier_delivery) {
        this.courier_delivery = courier_delivery;
    }

    public boolean isRoute_undefined() {
        return route_undefined;
    }

    public void setRoute_undefined(boolean route_undefined) {
        this.route_undefined = route_undefined;
    }

    public boolean isReciept() {
        return reciept;
    }

    public void setReciept(boolean reciept) {
        this.reciept = reciept;
    }

    public boolean isTerminal() {
        return terminal;
    }

    public void setTerminal(boolean terminal) {
        this.terminal = terminal;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }



    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("\"user_full_name\":\""+ getUser_full_name()+"\""+"\n");
        sb.append(",\'user_phone\":\""+getUser_phone_number()+"\""+"\n");
        sb.append(",\"client_sub_card\":\""+getClient_sub_card()+"\""+"\n");
        sb.append(",\"required_time\":\""+ getRequired_time()+"\"" +"\n");
        sb.append(",\"reservation\":\""+ isReservation()+"\"" +"\n");
        sb.append(",\"route_address_entrance_from\":\""+ getRoute_address_entrance_from()+"\"" +"\n");
        sb.append(",\"comment\":\""+ getComment()+"\"" +"\n");
        sb.append(",\"add_cost\":\""+ getAdd_cost()+"\"" +"\n");
        sb.append(",\"wagon\":\""+ isWagon()+"\"" +"\n");
        sb.append(",\"minibus\":\""+ isMinibus()+"\"" +"\n");
        sb.append(",\"premium\":\""+ isPremium()+"\"" +"\n");
        sb.append(",\"flexible_tariff_name\":\""+ getFlexible_tariff_name()+"\"" +"\n");
        sb.append(",\"baggage\":\""+ isBaggage()+"\"" +"\n");
        sb.append(",\"animal\":\""+ isAnimal()+"\"" +"\n");
        sb.append(",\"conditioner\":\""+ isConditioner()+"\"" +"\n");
        sb.append(",\"courier_delivery\":\""+ isCourier_delivery()+"\"" +"\n");
        sb.append(",\"route_undefined\":\""+ isRoute_undefined()+"\"" +"\n");
        sb.append(",\"terminal\":\""+ isTerminal()+"\"" +"\n");
        sb.append(",\"receipt\":\""+ isRoute_undefined()+"\"" +"\n");



        return sb.toString();
    }


}

