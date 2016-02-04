package bot.server;

public enum StepEnum {

	LANGUAGE("language") ,
	LOGIN("login") ,
	ADDRESS("address") ,
	DESTADDRESS("destAddress") ,
	SPECIFY("specify") ,
	CONFIRM("confirm"),
	TIME("time"),
	ORDER_CANCEL("orderCancel"),
	ENTER_NAME("enterName"),
	YES_NO("yesno"),
	ENTER_TIME("enterTime"),
	COMMENTS("comments"),
	CHOOSE_CAR("chooseCar"),
	CONDITIONS("conditions"),
	ENTER_COST("enterCost"),
	CANCEL("cancel");
	
	
	private String step;
	
	private StepEnum(String s) {
		this.step = s;
	}
	
	public String getStep() {
		return step;
	}
}
