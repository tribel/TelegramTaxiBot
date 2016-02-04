package bot.server.impl;

import java.util.ArrayList;
import java.util.List;



import org.springframework.context.support.ClassPathXmlApplicationContext;

import bot.jpa.entity.Language;
import bot.jpa.service.LanguageService;
import bot.server.StepEnum;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;


public class OrderManager implements Runnable{

	private LanguageService languageService = new ClassPathXmlApplicationContext("spring.xml").getBean(LanguageService.class);
	
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

		responseKeyoardMessage("Choose your language", languageService.getLanguageList(), update.message());	
		responseListBySelectLang = languageService.getLanguageListByStepLable(update.message().text());
		
		int choice = getStepId(
				responseKeyboardMessage(null, update.message(), StepEnum.LOGIN.getStep()).message().text());	
	
		if (choice == 1) {
			sendSimpleMessage(StepEnum.ENTER_NAME.getStep());
			// telephone load
			choice = getStepId(
						responseKeyboardMessage(null, update.message(), StepEnum.TIME.getStep()).message().text());
			if(choice == 2) {
				
			} else if(choice == 1) {
				sendSimpleMessage(StepEnum.ENTER_TIME.getStep());
			}
			
			choice = getStepId(
					responseKeyboardMessage("Comments ?", update.message(), StepEnum.YES_NO.getStep()).message().text());
			if(choice == 2) {
				sendSimpleMessage(StepEnum.COMMENTS.getStep());
			} 
			
			choice = getStepId(
					responseKeyboardMessage("What car", update.message(), StepEnum.YES_NO.getStep()).message().text());
			
			if(choice == 2) {
				choice = getStepId(
						responseKeyboardMessage(null, update.message(), StepEnum.CHOOSE_CAR.getStep()).message().text());
				if(choice == 2) {
					
				} else if( choice == 1) {
					
				}
			}
			
			choice = getStepId(
					responseKeyboardMessage("Additional conditions", update.message(), StepEnum.YES_NO.getStep()).message().text());
				
			if(choice == 2) {
				choice = getStepId(
						responseKeyboardMessage(null, update.message(), StepEnum.CONDITIONS.getStep()).message().text());
			
				if(choice == 2) {
					
				} else if( choice == 1) {
					
				}
			}
			
			sendSimpleMessage(StepEnum.ADDRESS.getStep());
			sendSimpleMessage(StepEnum.DESTADDRESS.getStep());
			
			choice = getStepId(
					responseKeyboardMessage("Enterence", update.message(), StepEnum.SPECIFY.getStep()).message().text());
			
			if(choice == 1) {
				//enter number of enntrance in json
			}
			
			choice = getStepId(
					responseKeyboardMessage("Additional cost", update.message(), StepEnum.YES_NO.getStep()).message().text());
			if(choice == 2) {
				sendSimpleMessage(StepEnum.ENTER_COST.getStep());
			}
			
			responseKeyboardMessage(null, update.message(), StepEnum.CONFIRM.getStep());
			
			
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
	
	public Update responseKeyoardMessage(String firstMsg, List<String> keyboardList, Message msg) {
		if(firstMsg != null) {
			telegramBot.sendMessage(chatId, firstMsg);
		}		
		String[][] strings = convertListToArray(keyboardList);

		telegramBot.sendMessage(chatId, msg.text(), ParseMode.Markdown, false, null,
								 new ReplyKeyboardMarkup(strings)
								.oneTimeKeyboard(true).resizeKeyboard(true));
		
		return update = waitForResponse();
	}
	
	public Update responseKeyboardMessage(String firstMsg, Message msg, String step) {
		this.currentStep = step;
		
		if(firstMsg != null) {
			telegramBot.sendMessage(chatId, firstMsg);
		}
		
		List<String> tmpList = new ArrayList<>();
		for(Language l: responseListBySelectLang) {
			if(l.getStepLable().equals(step) || l.getStepLable().equals(StepEnum.CANCEL.getStep())) 
				tmpList.add(l.getText());
		}
		
		String[][] strings = convertListToArray(tmpList);
		
		telegramBot.sendMessage(chatId, msg.text(), ParseMode.Markdown, false, null,
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
