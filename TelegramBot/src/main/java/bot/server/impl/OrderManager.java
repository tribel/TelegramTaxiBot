package bot.server.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import bot.jpa.entity.Language;
import bot.jpa.service.LanguageService;

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
		update = waitForResponse();
		if (update.message().text().equals("")) {
			
		}
		
		responseListBySelectLang = languageService.getLanguageListByStepLable(update.message().text());
		responseKeyboardMessage(null, update.message(), "login");	
		update = waitForResponse();
		if (update.message().text().equals("")) {
			
		}
		
		
		sendSimpleMessage("address");
		update = waitForResponse();
		if (true) {
			
		}
		
		sendSimpleMessage("destAddress");
		update = waitForResponse();
		if (true) {
			
		}
		
		responseKeyboardMessage(null, update.message(), "specify");
		update = waitForResponse();
		if (update.message().text().equals("")) {
			
		}
		
		responseKeyboardMessage(null, update.message(), "confirm");
		update = waitForResponse();
		if (update.message().text().equals("")) {
			
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
	
	public void responseKeyoardMessage(String firstMsg, List<String> keyboardList, Message msg) {
		if(firstMsg != null) {
			telegramBot.sendMessage(chatId, firstMsg);
		}
		
		String[][] strings = new String[keyboardList.size()][1];
		for(int i = 0; i < keyboardList.size(); i++) {
			for(int j = 0; j < strings[i].length; j++) {
				strings[i][j] = keyboardList.get(i);
			}
		}
		telegramBot.sendMessage(chatId, msg.text(), ParseMode.Markdown, false, msg.messageId(),
								 new ReplyKeyboardMarkup(strings)
								.oneTimeKeyboard(true).resizeKeyboard(true));
		
	}
	
	public void responseKeyboardMessage(String firstMsg, Message msg, String step) {
		if(firstMsg != null) {
			telegramBot.sendMessage(chatId, firstMsg);
		}
		
		List<String> tmpList = new ArrayList<>();
		for(Language l: responseListBySelectLang) {
			if(l.getStepLable().equals(step) || l.getStepLable().equals("cancel")) 
				tmpList.add(l.getText());
		}
		

		String[][] strings = new String[tmpList.size()][1];
		for(int i = 0; i < tmpList.size(); i++) {
			for(int j = 0; j < strings[i].length; j++) {
				strings[i][j] = tmpList.get(i);
			}
		}
		
		telegramBot.sendMessage(chatId, msg.text(), ParseMode.Markdown, false, msg.messageId(),
				 new ReplyKeyboardMarkup(strings)
				.oneTimeKeyboard(true).resizeKeyboard(true));
	}
	
	public void sendSimpleMessage(String step) {
		String tmpVal = null;
		for(Language l: responseListBySelectLang) {
			if(l.getStepLable().equals(step)) tmpVal = l.getText();
		}
		
		telegramBot.sendMessage(chatId, tmpVal);
	}
	
}
