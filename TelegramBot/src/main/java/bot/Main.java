package bot;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import bot.jpa.entity.Language;
import bot.jpa.service.LanguageService;
import bot.server.impl.AbstractCommandWatcher;
import bot.server.impl.OrderValidator;

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