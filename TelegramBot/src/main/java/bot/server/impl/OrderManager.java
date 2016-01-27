package bot.server.impl;

import java.util.List;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;


public class OrderManager implements Runnable{

	private long chatId;
	private Update update;
	private TelegramBot telegramBot;
	
	public OrderManager(Update update, TelegramBot bot) {
		this.update = update;
		this.telegramBot = bot;
		this.chatId = update.message().chat().id();
	}
	
	@Override
	public void run() {
		
		languageSelect(update.message());
		update = waitForResponse();
		if (update.message().text().equals("")) {
			
		}
		
		autorizeSelect(update.message());
		update = waitForResponse();
		if (update.message().text().equals("")) {
			
		}
		
		enteredClientAddrees(update.message());
		update = waitForResponse();
		if (true) {
			
		}
		
		enteredDestinationAddress(update.message());
		update = waitForResponse();
		if (true) {
			
		}
		
		specifyOrderAddress(update.message());
		update = waitForResponse();
		if (update.message().text().equals("")) {
			
		}
		
		confirmOrder(update.message());
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
	
	public void languageSelect(Message msg) {
		telegramBot.sendMessage(chatId, "Choose language");
		telegramBot.sendMessage(chatId, msg.text(), ParseMode.Markdown, false,
				msg.messageId(), new ReplyKeyboardMarkup(
						new String[] {"English", "Русский" }, 
						new String[] { "Українська","Отмена" }).
						oneTimeKeyboard(true).resizeKeyboard(true));
	}
	
	public void autorizeSelect(Message msg) {
		telegramBot.sendMessage(chatId, msg.text(), ParseMode.Markdown, false,
				msg.messageId(),
				new ReplyKeyboardMarkup(new String[] { "Вход" }, 
						new String[]{"Регистрация"},
						new String[]{"Заказ без аккаунта"}, 
						new String[]{"Отмена"}).
						oneTimeKeyboard(true).resizeKeyboard(true));
	}
	
	public void enteredClientAddrees(Message msg) {
		telegramBot.sendMessage(chatId, "Веедите ваш адрес");
	}
	
	public void enteredDestinationAddress(Message msg) {
		telegramBot.sendMessage(chatId, "Адрес прибытия");
	}
	
	public void specifyOrderAddress(Message msg) {
		telegramBot.sendMessage(chatId, msg.text(), ParseMode.Markdown, false,
				msg.messageId(),
				new ReplyKeyboardMarkup(new String[] { "Подьехать к подъезду"}, 
						new String[]{"хочу игорька"},
						new String[]{"Отмена" }).
						oneTimeKeyboard(true).resizeKeyboard(true));
	}
	
	public void setNumberOfPorch(Message msg) {
		telegramBot.sendMessage(chatId, "");
	}
	
	public void setTime(Message msg) {
		telegramBot.sendMessage(chatId, msg.text(), ParseMode.Markdown, false,
				msg.messageId(),
				new ReplyKeyboardMarkup(new String[] { "На сейчас" }, 
						new String[]{ "Через час"},
						new String[]{"К игорю на хату"},
						new String[]{"Отмена"}).
						oneTimeKeyboard(true).resizeKeyboard(true));
	}
	
	public void confirmOrder(Message msg) {
		telegramBot.sendMessage(chatId, "Едем на хату к Игрьку");
		telegramBot.sendMessage(chatId, msg.text(), ParseMode.Markdown, false,
				msg.messageId(),
				new ReplyKeyboardMarkup(new String[] { "Подтверждаю заказ", "Отмена",}).
																oneTimeKeyboard(true).resizeKeyboard(true));
	}
}
