package bot.server.impl;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import bot.server.Service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;


public class AbstractCommandWatcher implements Service , Runnable{
	
	protected boolean alive;
	protected long delay;
	protected Thread thread;
	protected TelegramBot telegramBot;
	public static ConcurrentHashMap<Long, Update> updateMap = new ConcurrentHashMap<>();
	public static final Integer UPDATE_LIMIT = 100;
	public static volatile Integer OFFSET;
	
	
	public AbstractCommandWatcher() {
	}
	
	/**
	 * 
	 * @param delay delay in milli sec ,between servers response
	 * @param bot bot from which server wait request.
	 */
	public AbstractCommandWatcher(long delay, TelegramBot bot) {
		this.delay = delay;
		this.telegramBot = bot;
		List<Update> tmpList= bot.getUpdates(OFFSET, UPDATE_LIMIT, 0).updates();
		
		if(tmpList.size() != 0 )
			OFFSET = tmpList.get(tmpList.size() -1).updateId();
	}
	
	
	@Override
	public void run() {
		List<Update> tmpUpdateList = null;
		while(alive) {
			
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			tmpUpdateList = telegramBot.getUpdates(OFFSET, UPDATE_LIMIT, 0).updates();
			for(Update u: tmpUpdateList) {
				long chatId = u.message().chat().id();
				if(!updateMap.containsKey(chatId)) {
					updateMap.put(chatId, u);
					new Thread(new OrderManager(u, telegramBot)).start();
					
				}
			}
			tmpUpdateList.clear();
		}
	}

	@Override
	public void startUp() {
		alive = true;
		
		thread = new Thread(this);
		thread.start();
	}

	@Override
	public void shutDown() {
		alive = false;
	}

	@Override
	public boolean isAlive() {
		return alive;
	}

	public long getDelay() {
		return delay;
	}

	public void setDelay(long delay) {
		this.delay = delay;
	}

	public TelegramBot getTelegramBot() {
		return telegramBot;
	}

	public void setTelegramBot(TelegramBot telegramBot) {
		this.telegramBot = telegramBot;
	}

		
}

