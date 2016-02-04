package bot.jpa.dao;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import bot.jpa.entity.Language;

@Repository
public class LanguageDaoImpl implements LanguageDao{

	private Session session;
	
	private Session openSession() {
		return session = HibernateUtil.getSessionFactory().openSession();
	}
	
	private void closeSession() {
		session.close();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getLanguageList() {
		openSession().beginTransaction();
		List<String> tmpList = session.createQuery("SELECT DISTINCT l.language FROM Language l").list();
		session.getTransaction().commit();
		closeSession();
		return tmpList;
	}

	@Override
	public void addRecord(Language l) {
		openSession().beginTransaction();
		session.save(l);
		session.getTransaction().commit();
		closeSession();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getTextListByStepLable(String lng, String sl) {
		openSession().beginTransaction();
		List<String> tmpList = session.createQuery(
		"SELECT l.text FROM Language l WHERE l.language = :lang AND l.stepLable = :lable ORDER BY l.priority DESC")
				.setParameter("lang", lng).setParameter("lable", sl).list();
		session.getTransaction().commit();
		closeSession();
		return tmpList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Language> getLanguageListByStepLable(String lng) {
		openSession().beginTransaction();
		List<Language> tmpList = session.createQuery(
							"SELECT l FROM Language l WHERE l.language = :lang ORDER BY l.priority DESC")
						.setParameter("lang", lng).list();
		session.getTransaction().commit();
		closeSession();
		return tmpList;
	}

	@Override
	public void deleteRecord(Language l) {
		openSession().beginTransaction();
		session.delete(l);
		session.getTransaction().commit();
		closeSession();
	}
	
	
}
