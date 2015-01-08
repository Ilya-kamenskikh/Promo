package application.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Movie {
	private final StringProperty name;
	private final StringProperty nameChannel;
	private final StringProperty time;
	private final StringProperty theme;
	private final IntegerProperty audience;
	
	public Movie() {
		this(null, null, null, null);
	}
	
	public Movie(String name, String time, String theme, Integer audience) {
		this.name = new SimpleStringProperty(name);
		this.time = new SimpleStringProperty(time);
		this.theme = new SimpleStringProperty(theme);
		this.audience = new SimpleIntegerProperty(audience);
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
	
	public String getTheme() {
		return theme.get();
	}
	public void setTheme(String theme) {
		this.theme.set(theme);
	}
	public StringProperty themeProperty(){
		return theme;
	}
	
	public int getAudience() {
		return audience.get();
	}
	public void setAudience(int audience) {
		this.audience.set(audience);
	}
	public IntegerProperty audienceProperty(){
		return audience;
	}
	
}
