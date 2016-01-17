package bot;

import server.impl.CommandWatcher;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.TelegramBotAdapter;


public class Main {

	
	public static void main(String[] args) throws InterruptedException {
		TelegramBot bot = TelegramBotAdapter.build("149115692:AAEG5if5kiK5lMGe9rhueNO90xUm5k2lxwc");
		CommandWatcher watcher = new CommandWatcher(3000, bot);
		watcher.startUp();

	}
}
