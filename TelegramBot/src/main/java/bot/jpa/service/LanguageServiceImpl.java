package bot.jpa.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import bot.jpa.dao.LanguageDao;
import bot.jpa.entity.Language;

@Named
public class LanguageServiceImpl implements LanguageService{
	
	@Inject
	private LanguageDao languageDao;

	@Override
	public List<String> getLanguageList() {
		return languageDao.getLanguageList();
	}

	@Override
	@Transactional
	public void addRecord(Language l) {
		languageDao.addRecord(l);
	}

	@Override
	@Transactional
	public void deleteRecord(Language l) {
		languageDao.deleteRecord(l);
		
	}

	@Override
	public List<String> getTextListByStepLable(String lng, String sl) {
		return languageDao.getTextListByStepLable(lng, sl);
	}

	@Override
	public List<Language> getLanguageListByStepLable(String lng) {
		return languageDao.getLanguageListByStepLable(lng);
	}

	
	
}
