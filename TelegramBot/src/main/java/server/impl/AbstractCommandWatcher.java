package server.impl;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;

import server.Service;

public abstract class AbstractCommandWatcher implements Service , Runnable{

	protected boolean alive;
	protected long delay;
	protected Thread thread;
	protected TelegramBot telegramBot;
	
	/**
	 * 
	 * @param delay delay in milli sec ,between servers response
	 * @param bot bot from which server wait request.
	 */
	public AbstractCommandWatcher(long delay, TelegramBot bot) {
		this.delay = delay;
		this.telegramBot = bot;
	}
	
	
	@Override
	public void run() {
		
		while(alive) {
			
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			commandExecuting();
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
	
	/**
	 * Method that get bot updates , parse message and do some response  
	 */
	public abstract void commandExecuting();
	
	protected abstract void messageParsing(Message message);
	
}
