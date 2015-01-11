package application.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Movie {
	private final StringProperty name;
	private final StringProperty nameChannel;
	private final StringProperty time;
	private final ObjectProperty<Theme> theme;
	private final ObjectProperty<Audience> audience;
	
	public Movie() {
		this(null, null, null, null);
	}
	
	public Movie(String name, String time, Theme theme, Audience audience) {
		this.name = new SimpleStringProperty(name);
		this.time = new SimpleStringProperty(time);
		this.theme = new SimpleObjectProperty<Theme>(theme);
		this.audience = new SimpleObjectProperty<Audience>(audience);
		this.nameChannel = new SimpleStringProperty("not found");
	}
	
	public String getName() {
		return name.get();
	}
	public void setName(String name) {
		this.name.set(name);
	}
	public StringProperty nameProperty(){
		return name;
	}
	
	public String getNameChannel() {
		return nameChannel.get();
	}
	public void setNameChannel(String name) {
		this.nameChannel.set(name);
	}
	public StringProperty nameChannelProperty(){
		return nameChannel;
	}
	
	public String getTime() {
		return time.get();
	}
	public void setTime(String time) {
		this.time.set(time);
	}
	public StringProperty timeProperty(){
		return time;
	}
	
	public Theme getTheme() {
		return theme.get();
	}
	public void setTheme(Theme theme) {
		this.theme.set(theme);
	}
	public ObjectProperty<Theme> themeProperty(){
		return theme;
	}
	
	public Audience getAudience() {
		return audience.get();
	}
	public void setAudience(Audience audience) {
		this.audience.set(audience);
	}
	public ObjectProperty<Audience> audienceProperty(){
		return audience;
	}
	
}
