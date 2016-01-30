package bot.jpa.dao;

import java.util.List;

import bot.jpa.entity.Language;

public interface LanguageDao {
	
	public List<String> getLanguageList();
	
	public List<String> getTextListByStepLable(String lng, String sl);
	
	public List<Language> getLanguageListByStepLable(String lng);
	
	public void addRecord(Language l);
	
	public void deleteRecord(Language l);
}
