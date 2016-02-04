package bot.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Language implements Comparable<Language>{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column
	private String language;
	
	@Column
	private int priority;
	
	@Column
	private String stepLable;
	
	@Column
	private String text;
	
	@Column
	private int choiceId;

	public Language() {
	}

	public Language(String language, int priority, String stepLable,
			String text , int choice) {
		super();
		this.language = language;
		this.priority = priority;
		this.stepLable = stepLable;
		this.text = text;
		this.choiceId = choice;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getStepLable() {
		return stepLable;
	}

	public void setStepLable(String stepLable) {
		this.stepLable = stepLable;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public int getChoiceId() {
		return choiceId;
	}

	public void setChoiceId(int choiceId) {
		this.choiceId = choiceId;
	}

	@Override
	public String toString() {
		return "Language [id=" + id + ", language=" + language + ", priority="
				+ priority + ", stepLable=" + stepLable + ", text=" + text
				+ ", choiceId=" + choiceId + "]";
	}

	@Override
	public int compareTo(Language o) {
		return -Integer.compare(this.getPriority(), o.getPriority());
	}


	
}
