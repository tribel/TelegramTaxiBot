package bot.jpa.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import bot.jpa.dao.LanguageDao;
import bot.jpa.entity.Language;

@Named
public class LanguageServiceImpl implements LanguageService{
	
	@Inject
	private LanguageDao languageDao;

	@Override
	public List<String> getLanguageList() {
		return null;
	}

	@Override
	public void addRecord(Language l) {
		languageDao.addRecord(l);
	}

	
}
