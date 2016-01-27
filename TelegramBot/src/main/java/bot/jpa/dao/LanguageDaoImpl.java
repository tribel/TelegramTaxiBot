package bot.jpa.dao;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import bot.jpa.entity.Language;

@Repository
public class LanguageDaoImpl implements LanguageDao{

	@Override
	public List<String> getLanguageList() {
		
		return null;
	}

	@Override
	public void addRecord(Language l) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(l);
		session.getTransaction().commit();
		session.close();
	}
	
	
}
