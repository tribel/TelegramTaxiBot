package bot.jpa.service;

import java.util.List;

import bot.jpa.entity.Language;

public interface LanguageService {
	
	public List<String> getLanguageList();
	
	public void addRecord(Language l);
	
	public void deleteRecord(Language l);
	
	public List<String> getTextListByStepLable(String lng, String sl);
	
	public List<Language> getLanguageListByStepLable(String lng);

}
