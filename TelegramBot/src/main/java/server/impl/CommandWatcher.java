package server.impl;

import java.util.List;

import retrofit.RetrofitError;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;

public class CommandWatcher extends AbstractCommandWatcher{

	public static final Integer UPDATE_LIMIT = 100;
	public static Integer OFFSET;
	
	public CommandWatcher(long delay, TelegramBot bot) {
		super(delay, bot);
		List<Update> tmpUpdates = telegramBot.getUpdates(OFFSET, UPDATE_LIMIT, 0).updates();
		for(Update update: tmpUpdates) {
			System.out.println(update);
			
		}

		OFFSET = tmpUpdates.get(tmpUpdates.size() -1).updateId();
	}

	
	public void commandExecuting() {
		List<Update> tmpUpdates = null;
		
		try {
			tmpUpdates = telegramBot.getUpdates(OFFSET + 1, UPDATE_LIMIT, 0).updates();
			
		} catch (RetrofitError e) {
			e.printStackTrace();
		}
		
		if(tmpUpdates.size() != 0) {
			for(Update u: tmpUpdates) {
				messageParsing(u.message()); // or in multiple threads 
				OFFSET++;
			}
		}
		
	}


	protected void messageParsing(Message message) {
		if(message.text().equals("hello")) {
			telegramBot.sendMessage(message.chat().id(), "Hello my name is igor");
		}
		
		if(message.text().equals("bye")) {
			telegramBot.sendMessage(message.chat().id(), "Bye nice to meet you");
		}
		
		if(message.text().equals("who am i")) {
			telegramBot.sendMessage(
					message.chat().id(),                   
				    "sdf", 
				    ParseMode.Markdown,        
				    false,                     
				    message.messageId(),           
				    new ReplyKeyboardMarkup(new String[]{"mafia", "sherif"}).oneTimeKeyboard(true));
			
			
		}
		
	}
	
}
