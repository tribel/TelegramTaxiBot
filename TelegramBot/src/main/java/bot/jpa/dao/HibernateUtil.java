package bot.jpa.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	
	
	private static SessionFactory sessionFactory;
	
	static {
		try {
			sessionFactory = new Configuration().configure().buildSessionFactory();
		
		} catch (Throwable e) {
			e.printStackTrace();
		}	
	}
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
