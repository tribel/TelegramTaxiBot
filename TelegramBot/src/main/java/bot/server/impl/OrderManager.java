package bot.server.impl;

import java.util.ArrayList;
import java.util.List;








import javax.inject.Inject;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import bot.jpa.entity.Language;
import bot.jpa.service.LanguageService;
import bot.server.StepEnum;
import bot.webordersapi.TaxiOrders;
import bot.webordersapi.models.Address;
import bot.webordersapi.models.Order;
import bot.webordersapi.models.Route;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;


public class OrderManager implements Runnable{

	private LanguageService languageService = new ClassPathXmlApplicationContext("spring.xml").getBean(LanguageService.class);
	
	@Inject
	private TaxiOrders taxiOrders;
	
	private long chatId;
	private Update update;
	private TelegramBot telegramBot;
	private List<Language> responseListBySelectLang;
	private String currentStep;
	
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
		
		int choice = getStepId(
				responseKeyboardMessage(null,StepEnum.LOGIN.getStep()).message().text());	
		
		if (choice == 1) {
			Order order = new Order();
			order.setUser_full_name(sendSimpleMessage(StepEnum.ENTER_NAME.getStep()).message().text());
			// telephone load
			choice = getStepId(
						responseKeyboardMessage(null, StepEnum.TIME.getStep()).message().text());
			if(choice == 2) {
				
			} else if(choice == 1) {
				order.setReservation(true);
				order.setRequired_time(sendSimpleMessage(StepEnum.ENTER_TIME.getStep()).message().text());;
			}
			
			choice = getStepId(
					responseKeyboardMessage("commentsYN",StepEnum.YES_NO.getStep()).message().text());
			if(choice == 2) {
				
				order.setComment(sendSimpleMessage(StepEnum.COMMENTS.getStep()).message().text());
			} 
			
			choice = getStepId(
					responseKeyboardMessage("chooseCarYN", StepEnum.YES_NO.getStep()).message().text());
			
			if(choice == 2) {
				choice = getStepId(
						responseKeyboardMessage(null, StepEnum.CHOOSE_CAR.getStep()).message().text());
				if(choice == 2) {
					order.setWagon(true);
				} else if( choice == 1) {
					order.setPremium(true);
				}
			}
			
			choice = getStepId(
					responseKeyboardMessage("conditionsYN", StepEnum.YES_NO.getStep()).message().text());
				
			if(choice == 2) {
				choice = getStepId(
						responseKeyboardMessage(null, StepEnum.CONDITIONS.getStep()).message().text());
			
				if(choice == 2) {
					order.setBaggage(true);
				} else if( choice == 1) {
					order.setTerminal(true);
				}
			}
			ArrayList<Address> tmpRouteList = new ArrayList<>();
			tmpRouteList.add(new Address(sendSimpleMessage(StepEnum.ADDRESS.getStep()).message().text()));
			tmpRouteList.add(new Address(sendSimpleMessage(StepEnum.DESTADDRESS.getStep()).message().text()));
			Route tmpRoute = new Route();
			tmpRoute.setAddresses(tmpRouteList);
			order.setRoute(tmpRoute);
			
			choice = getStepId(
					responseKeyboardMessage(null, StepEnum.SPECIFY.getStep()).message().text());
			
			if(choice == 1) {
				//enter number of enntrance in json
			}
			
			choice = getStepId(
					responseKeyboardMessage("enterCostYN", StepEnum.YES_NO.getStep()).message().text());
			if(choice == 2) {
				order.setAdd_cost(Double.parseDouble(sendSimpleMessage(StepEnum.ENTER_COST.getStep()).message().text()));
			}
			
			choice = getStepId(
					responseKeyboardMessage(null, StepEnum.CONFIRM.getStep()).message().text());
			if(choice == 1) {
				taxiOrders.calculateCost(order);
			} else if(choice == 0) {
				
			}
			
			
		} else if (choice == 0) {
			sendSimpleMessageWithoutUpdate(StepEnum.ORDER_CANCEL.getStep());
			return;
		}
		

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		AbstractCommandWatcher.updateMap.remove(chatId);
		AbstractCommandWatcher.OFFSET = update.updateId() + 1;
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
			}
		}
		return -1;
	}
	
}
