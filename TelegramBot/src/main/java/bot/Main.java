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
				.build("149115692:AAEG5if5kiK5lMGe9rhueNO90xUm5k2lxwc");
		AbstractCommandWatcher watcher = new AbstractCommandWatcher(2000, bot);
		watcher.startUp();	
		//LanguageService languageService = new ClassPathXmlApplicationContext("spring.xml").getBean(LanguageService.class);
		//languageService.addRecord(new Language("Русский", 1, "orderResponseYN", "Неверно введены адреса. Повторить ?"));
		//languageService.addRecord(new Language("Русский", 1, "successfulUpdate", "Профиль обновлен успешно"));
		//languageService.addRecord(new Language("English", 1, "orderResponseYN", "Invalid address format. Repeat ?"));
		//languageService.addRecord(new Language("English", 1, "successfulUpdate", "Successful frifile update"));

		
		
	} 
}