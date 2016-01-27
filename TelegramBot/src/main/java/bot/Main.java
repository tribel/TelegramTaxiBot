package bot;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import bot.server.impl.AbstractCommandWatcher;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.TelegramBotAdapter;

public class Main {

	
	public static void main(String[] args)  {
		TelegramBot bot = TelegramBotAdapter
				
				.build("149115692:AAEG5if5kiK5lMGe9rhueNO90xUm5k2lxwc");
		AbstractCommandWatcher watcher = new AbstractCommandWatcher(2000, bot);
		watcher.startUp();
		ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

	}

}