package bot.server;

/**
 * Interface represents a class that will act as a server.
 * It allows to class to start or shut down the process.
 * 
 * @author Артем
 * 
 */
public interface Service {
	
	/**
	 *  This method starts up the service.
	 */
	public void startUp();
	
	/**
	 *  This method shuts down the service.
	 */
	public void shutDown();
	
	
	/**
	 * 
	 * @return boolean variable , true - if srvece still alive.
	 *							  false - if it off.	 				
	 */
	public boolean isAlive();
	
}
