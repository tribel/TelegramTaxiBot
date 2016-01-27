package bot.jpa.service;

import java.util.List;

import bot.jpa.entity.Language;

public interface LanguageService {
	
	public List<String> getLanguageList();
	
	public void addRecord(Language l);
}
