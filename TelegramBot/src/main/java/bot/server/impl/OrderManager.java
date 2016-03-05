package bot.server.impl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.inject.Inject;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import bot.jpa.entity.Language;
import bot.jpa.service.LanguageService;
import bot.server.StepEnum;
import bot.webordersapi.TaxiOrders;
import bot.webordersapi.models.Address;
import bot.webordersapi.models.AuthorisationRequest;
import bot.webordersapi.models.CreateOrder;
import bot.webordersapi.models.Order;
import bot.webordersapi.models.PhoneNumber;
import bot.webordersapi.models.Registration;
import bot.webordersapi.models.response.AuthorizationResponse;
import bot.webordersapi.models.response.CostResponse;
import bot.webordersapi.models.response.CreateOrderResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;


public class OrderManager implements Runnable{

	private LanguageService languageService = new ClassPathXmlApplicationContext("spring.xml").getBean(LanguageService.class);
	
	@Inject
	private TaxiOrders taxiOrders = new ClassPathXmlApplicationContext("spring.xml").getBean(TaxiOrders.class);
	
	private long chatId;
	private Update update;
	private TelegramBot telegramBot;
	private List<Language> responseListBySelectLang;
	private String currentStep;
	private int choice;
	
	public OrderManager() {
	}
	
	public OrderManager(Update update, TelegramBot bot) {
		this.update = update;
		this.telegramBot = bot;
		this.chatId = update.message().chat().id();
	}
	
	@Override
	public void run() {

		responseKeyoardMessage(languageService.getLanguageList(),"Choose your language");	
		responseListBySelectLang = languageService.getLanguageListByStepLable(update.message().text());
		
		choice = getStepId(
				responseKeyboardMessage(null,StepEnum.LOGIN.getStep()).message().text());	
		Order order = new Order();
		
		if (choice == 1) {	
			order.setUser_full_name(update.message().chat().firstName() + " "+ update.message().chat().lastName());
			// telephone load
			OrderValidator validator = new OrderValidator();
			do {
				order.setUser_phone_number(sendSimpleMessage("enterTelephone").message().text());
			} while (validator.telephoneValid(order.getUser_phone_number()) == false);
			
			if(orderImplement(order) == 0) return;
			if(orderAddressImplement(order) == 0) return;
			if(orderAddCostImplement(order) == 0) return;
			order.setTaxiColumnId(0);
			
			Gson gson = new GsonBuilder().serializeNulls().create();
			System.out.println(gson.toJson(order));
			CostResponse response = taxiOrders.calculateCost(order,getBase64Url(
												new AuthorisationRequest("0972525116", "1234qwe")));
			
			if(response != null) {
				sendSimpleMessageWithoutUpdate(StepEnum.ORDER_COST_VIEW.getStep());
			} else {
				sendSimpleMessageWithoutUpdate("orderError");
				exit(); return;
			}
			telegramBot.sendMessage(this.chatId, String.valueOf(response.getOrder_cost() + response.getCurrency()));
			
			choice = getStepId(
					responseKeyboardMessage(null, StepEnum.CONFIRM.getStep()).message().text());
			if(choice == 1) {
				taxiOrders.createOrder(new CreateOrder(0.0, "", order), getBase64Url(
												new AuthorisationRequest("0972525116", "1234qwe")));
				
			} else if(choice == 0) {
				sendSimpleMessageWithoutUpdate(StepEnum.ORDER_CANCEL.getStep());
				exit(); return;
			}
			
			
		} else if(choice == 3) {
			String userTelephone = null , userPassword = null;
			AuthorizationResponse response = null;
			boolean repeatFlag = false;
			
			do {
				if(repeatFlag && 
				  (getStepId(responseKeyboardMessage("authrizeError",StepEnum.REPEAT.getStep()).message().text()) == 0)) {
					sendSimpleMessageWithoutUpdate(StepEnum.ORDER_CANCEL.getStep());
					exit(); return;
				}	
				userTelephone = sendSimpleMessage(StepEnum.ENTER_TELEPHONE.getStep()).message().text();
				userPassword = sendSimpleMessage(StepEnum.ENTER_PASSWORD.getStep()).message().text();
				response = taxiOrders.authorize(new AuthorisationRequest(userTelephone, userPassword));
				repeatFlag = true;
			} while(response.getUser_phone() == null);
		
			choice = getStepId(
					responseKeyboardMessage("showProfile", StepEnum.YES_NO.getStep()).message().text());	
			if(choice == 2) {
				showUserProfile(response);
			} else if(choice == 0) {
				sendSimpleMessageWithoutUpdate(StepEnum.ORDER_CANCEL.getStep());
				exit(); return;
			}
			
			choice = getStepId(
					responseKeyboardMessage("startOrder", StepEnum.YES_NO.getStep()).message().text());	
			if(choice != 2) {
				sendSimpleMessageWithoutUpdate(StepEnum.ORDER_CANCEL.getStep());
				exit(); return;
			}
			
			order.setUser_full_name(response.getUser_full_name());
			order.setUser_phone_number(response.getUser_phone());
			if(orderImplement(order) == 0) return;
			
			if(response.getRoute_address_from() == null) {
				if(orderAddressImplement(order) == 0) return;
			} else {
				ArrayList<Address> tmpRouteList = new ArrayList<>();
				tmpRouteList.add(new Address(response.getRoute_address_from(), response.getRoute_address_number_from()));
				order.setRoute_address_entrance_from(String.valueOf(response.getRoute_address_entrance_from()));
				tmpRouteList.add(new Address(sendSimpleMessage(StepEnum.DESTADDRESS.getStep()).message().text(), 
																50.450814, 30.466327));
				order.setRoute(tmpRouteList);		
			}
			
			if(orderAddCostImplement(order) == 0) return;
			order.setTaxiColumnId(0);
			
			Gson gson = new GsonBuilder().serializeNulls().create();
			System.out.println(gson.toJson(order));
			CostResponse costResponse = taxiOrders.calculateCost(order,getBase64Url(
												new AuthorisationRequest(response.getUser_phone(), userPassword)));
			if(costResponse != null) {
				sendSimpleMessageWithoutUpdate(StepEnum.ORDER_COST_VIEW.getStep());
			} else {
				sendSimpleMessageWithoutUpdate("orderError");
				exit(); return;
			}
			telegramBot.sendMessage(this.chatId, String.valueOf(costResponse.getOrder_cost() +
																costResponse.getCurrency()));
			
			choice = getStepId(
					responseKeyboardMessage(null, StepEnum.CONFIRM.getStep()).message().text());
			if(choice == 1) {
				CreateOrderResponse createOrderResponse = 
						taxiOrders.createOrder(new CreateOrder(0.0, "", order), getBase64Url(
											   new AuthorisationRequest(response.getUser_phone(), userPassword)));
				
			} else if(choice == 0) {
				sendSimpleMessageWithoutUpdate(StepEnum.ORDER_CANCEL.getStep());
				exit(); return;
			}
			
		} else if(choice == 2) {
			OrderValidator validator = new OrderValidator();
			PhoneNumber phoneNumber = null;
			do {
				phoneNumber = new PhoneNumber(sendSimpleMessage("enterTelephone").message().text());
			} while (taxiOrders.registration(phoneNumber) != null
					|| validator.telephoneValid(phoneNumber.getPhone()) == false);
			
			sendSimpleMessageWithoutUpdate("waitSms");
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			Registration registration = new Registration();
			registration.setConfirm_code(sendSimpleMessage("enterConfirmCode").message().text());
			registration.setPhone(phoneNumber.getPhone());
			registration.setUser_first_name(update.message().chat().firstName());
			
			boolean repeatFlag = false;
			
			do {
				if(repeatFlag && 
				  (getStepId(responseKeyboardMessage(
						  "passwordNotEquals",StepEnum.REPEAT.getStep()).message().text()) == 0)) {
					sendSimpleMessageWithoutUpdate(StepEnum.ORDER_CANCEL.getStep());
					exit(); return;
				}	
				registration.setPassword(sendSimpleMessage(StepEnum.ENTER_PASSWORD.getStep()).message().text());
				registration.setConfirm_password(sendSimpleMessage("confirmPassword").message().text());
				repeatFlag = true;
			} while(!registration.isPasswordEquals());
			
			if(taxiOrders.registrationConfirm(registration) != null) {
				sendSimpleMessageWithoutUpdate("registrationError");
			}else {
				sendSimpleMessageWithoutUpdate("registrationSuccess");
			}
		}
		else if (choice == 0) {
			sendSimpleMessageWithoutUpdate(StepEnum.ORDER_CANCEL.getStep());
			exit();
			return;
		}
		
		exit();
	}

	public Update waitForResponse() {
		// add exact time for response waiting
		while (true) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			List<Update> tmpList = telegramBot.getUpdates(
					AbstractCommandWatcher.OFFSET,
					AbstractCommandWatcher.UPDATE_LIMIT, 0).updates();

			for (Update u : tmpList) {
				if (u.message().chat().id() == chatId && (u.updateId() > update.updateId())) 
					return u;		
			}
		}
	}
	
	public Update responseKeyoardMessage(List<String> keyboardList, String msg) {
		if(msg == null)
			msg = "hello";
		
		String[][] strings = convertListToArray(keyboardList);

		telegramBot.sendMessage(chatId, msg, ParseMode.Markdown, false, null,
								 new ReplyKeyboardMarkup(strings)
								.oneTimeKeyboard(true).resizeKeyboard(true));
		
		return update = waitForResponse();
	}
	
	public Update responseKeyboardMessage(String msg, String step) {
		this.currentStep = step;
		if(msg == null)
			msg = "hello";
		
		List<String> tmpList = new ArrayList<>();
		for(Language l: responseListBySelectLang) {
			if(l.getStepLable().equals(step) || l.getStepLable().equals(StepEnum.CANCEL.getStep())) 
				tmpList.add(l.getText());
			if(l.getStepLable().equals(step + "Head"))
				msg = l.getText();
			if(l.getStepLable().equals(msg)) {
				msg = l.getText();
			}
		}
		
		String[][] strings = convertListToArray(tmpList);
		
		telegramBot.sendMessage(chatId, msg, ParseMode.Markdown, false, null,
				 new ReplyKeyboardMarkup(strings)
				.oneTimeKeyboard(true).resizeKeyboard(true));
		
		return update = waitForResponse();
	}
	

	public Update sendSimpleMessage(String step) {
		sendSimpleMessageWithoutUpdate(step);
		return update = waitForResponse();
	}
	
	public void sendSimpleMessageWithoutUpdate(String step) {
		String tmpVal = null;
		for(Language l: responseListBySelectLang) {
			if(l.getStepLable().equals(step)) tmpVal = l.getText();
		}
		
		telegramBot.sendMessage(chatId, tmpVal);
	}
	
	public String[][] convertListToArray(List<String> list) {
		
		String[][] strings = new String[list.size()][1];
		for(int i = 0; i < list.size(); i++) {
			for(int j = 0; j < strings[i].length; j++) {
				strings[i][j] = list.get(i);
			}
		}
		return strings;
	}
	
	public int getStepId(String str) {
		
		for(Language l: responseListBySelectLang) {
			if(l.getStepLable().equals(currentStep) && l.getText().equals(str)) {
				return l.getPriority();
			} else if(l.getStepLable().equals("cancel")) {
				return 0;
			}
		}
		return -1;
	}
	
	public void exit() {

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		AbstractCommandWatcher.updateMap.remove(chatId);
		AbstractCommandWatcher.OFFSET = update.updateId() + 1;
	}
	
	public String getBase64Url(AuthorisationRequest request) {
		String tmpString = request.getLogin() + ":" + request.getPassword();
		String as64 = null;
		try {
			as64 = Base64.getUrlEncoder().encodeToString(tmpString.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println(as64);
		return as64;
	}
	
	public int orderImplement(Order order) {
		choice = getStepId(responseKeyboardMessage(null,StepEnum.TIME.getStep()).message().text());
		
		if (choice == 1) {
			order.setReservation(true);
			order.setRequired_time(sendSimpleMessage(StepEnum.ENTER_TIME.getStep()).message().text());
		} else if (choice == 0) {
			sendSimpleMessageWithoutUpdate(StepEnum.ORDER_CANCEL.getStep());
			exit();
			return 0;
		}

		choice = getStepId(responseKeyboardMessage("commentsYN",StepEnum.YES_NO.getStep()).message().text());
		if (choice == 2) {
			order.setComment(sendSimpleMessage(StepEnum.COMMENTS.getStep())
					.message().text());
		} else if (choice == 0) {
			sendSimpleMessageWithoutUpdate(StepEnum.ORDER_CANCEL.getStep());
			exit();
			return 0;
		}

		choice = getStepId(responseKeyboardMessage("chooseCarYN",StepEnum.YES_NO.getStep()).message().text());

		if (choice == 2) {
			choice = getStepId(responseKeyboardMessage(null,StepEnum.CHOOSE_CAR.getStep()).message().text());
			if (choice == 3) {
				order.setPremium(true);
			} else if (choice == 2) {
				order.setWagon(true);
			} else if (choice == 1) {
				order.setMinibus(true);
			}

		} else if (choice == 1) {
			order.setFlexible_tariff_name("гибкий тариф");

		} else if (choice == 0) {
			sendSimpleMessageWithoutUpdate(StepEnum.ORDER_CANCEL.getStep());
			exit();
			return 0;
		}

		choice = getStepId(responseKeyboardMessage("conditionsYN",StepEnum.YES_NO.getStep()).message().text());

		if (choice == 2) {
			choice = getStepId(responseKeyboardMessage(null,StepEnum.CONDITIONS.getStep()).message().text());

			switch (choice) {
			case 5:
				order.setCourier_delivery(true);; break;
			case 4:
				order.setTerminal(true); break;
			case 3: 
				order.setConditioner(true); break;
			case 1:
				choice = getStepId(responseKeyboardMessage(null,StepEnum.CONDITIONS2.getStep()).message().text());
				switch (choice) {
				case 3: 
					order.setAnimal(true); break;
				case 2: 
					order.setBaggage(true); break;
				case 1: break;	
				}
				break;
			default:
				break;
			}
		} else if (choice == 0) {
			sendSimpleMessageWithoutUpdate(StepEnum.ORDER_CANCEL.getStep());
			exit();
			return 0;
		}
		return 1;
	}
	
	public int orderAddressImplement(Order order) {
		ArrayList<Address> tmpRouteList = new ArrayList<>();
		tmpRouteList.add(new Address(sendSimpleMessage(StepEnum.ADDRESS.getStep()).message().text(),50.42782, 30.66384));
		
		choice = getStepId(
				responseKeyboardMessage(StepEnum.ENTER_ENTRANCE.getStep(), StepEnum.SPECIFY.getStep()).message().text());
		
		if(choice == 1) {
			order.setRoute_address_entrance_from(sendSimpleMessage(StepEnum.ENTER_ENTRANCE.getStep()).message().text());
		} else if(choice == 0) {
			sendSimpleMessageWithoutUpdate(StepEnum.ORDER_CANCEL.getStep());
			exit(); return 0;
		}
		
		tmpRouteList.add(new Address(sendSimpleMessage(StepEnum.DESTADDRESS.getStep()).message().text(), 50.450814, 30.466327));
		order.setRoute(tmpRouteList);
		return 1;
	}
	
	public int orderAddCostImplement(Order order) {
		boolean validFlag = true;
		choice = getStepId(
				responseKeyboardMessage("enterCostYN", StepEnum.YES_NO.getStep()).message().text());
		if(choice == 2) {
			do {
				try {
					order.setAdd_cost(Double.parseDouble(sendSimpleMessage(StepEnum.ENTER_COST.getStep()).message().text()));
					validFlag = true;
				} catch (NumberFormatException e) {
					sendSimpleMessageWithoutUpdate("validAddCost");
					validFlag = false;
				}
				
			} while (!validFlag);
			
		} else if(choice == 0) {
			sendSimpleMessageWithoutUpdate(StepEnum.ORDER_CANCEL.getStep());
			exit(); return 0;
		}
		return 1;
	}
	
	public void showUserProfile(AuthorizationResponse response) {
		sendSimpleMessageWithoutUpdate("userBalance");
		telegramBot.sendMessage(this.chatId, String.valueOf(response.getUser_balance()));
		
		sendSimpleMessageWithoutUpdate("userAddress");
		if(response.getRoute_address_from() == null) {
			sendSimpleMessageWithoutUpdate("userAddressIsNull");
		} else {
			telegramBot.sendMessage(this.chatId, response.getRoute_address_from() 
											 + response.getRoute_address_apartment_from());
		}
		
		sendSimpleMessageWithoutUpdate("userDiscount");
		telegramBot.sendMessage(this.chatId, String.valueOf(response.getDiscount().getValue() 
															+ response.getDiscount().getUnit()));
		
		sendSimpleMessageWithoutUpdate("userBonuses");
		telegramBot.sendMessage(this.chatId, String.valueOf(response.getClient_bonuses()));
	}

}
