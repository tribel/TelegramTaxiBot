package bot.server.impl;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class OrderValidator {
	
	private static final  int MAX_HOUR = 23;
	private static final int MAX_MINUTE = 59;
	
	private String finalAddress = "";
	private String finalAddNumber ;
	
	public OrderValidator() {
	}
	
	public boolean isAddressValid(String addr) {
		return false;
	}
	
	public boolean timeValidator(String time) {
		if(time.length() != 5) return false;
		if(time.toCharArray()[2] != ':') return false;
	
		try {
			if(Integer.parseInt(time.split(":")[0]) > MAX_HOUR) return false;
			if(Integer.parseInt(time.split(":")[1]) > MAX_MINUTE) return false;
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
	
	//"2015-08-24T19:15:00"
	public String timeConverter(String time) {
		LocalDateTime dateTime = LocalDateTime.now();
		LocalTime userTime = LocalTime.of(Integer.parseInt(time.split(":")[0]), 
										  Integer.parseInt(time.split(":")[1]));
		
		if(dateTime.getHour() > userTime.getHour()) {
		 	dateTime = dateTime.plusDays(1);
		} else if(dateTime.getHour() == userTime.getHour() && dateTime.getMinute() > userTime.getMinute()) {
			dateTime = dateTime.plusDays(1);
		}
		
		
		LocalDateTime orderDateTime = LocalDateTime.of(dateTime.toLocalDate(), userTime);
		return orderDateTime.toString();
	}
	
	public boolean telephoneValid(String tel) {
		if(tel.length() != 10) return false;
		if(tel.charAt(0) != '0') return false;
		return true;
	}
	
	public boolean seperateAddressString(String str) {
		this.finalAddNumber = new String();
		this.finalAddress = new String();
		
		String [] addrArr = str.split(" ");
		for(String s: addrArr) {
			try {
				Integer.valueOf(s);
				this.finalAddNumber = s;
			} catch (NumberFormatException e) {
				this.finalAddress = finalAddress + " " + s;
			}
		}
		return true;
	}

	public String getFinalAddress() {
		return finalAddress.trim();
	}

	public String getFinalAddNumber() {
		return finalAddNumber;
	}
	
	
 }
