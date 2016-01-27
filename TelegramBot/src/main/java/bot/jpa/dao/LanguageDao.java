package bot.jpa.dao;

import java.util.List;

import bot.jpa.entity.Language;

public interface LanguageDao {
	
	public List<String> getLanguageList();
	
	public void addRecord(Language l);
}
