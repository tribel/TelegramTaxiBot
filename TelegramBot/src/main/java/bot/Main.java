package bot;

import bot.server.impl.AbstractCommandWatcher;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.TelegramBotAdapter;

public class Main {

	
	public static void main(String[] args)  {
		TelegramBot bot = TelegramBotAdapter
				
				.build("");
		AbstractCommandWatcher watcher = new AbstractCommandWatcher(2000, bot);
		watcher.startUp();	
				
	} 
}