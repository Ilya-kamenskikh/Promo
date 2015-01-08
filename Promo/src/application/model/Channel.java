package application.model;

import java.util.List;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Channel {
	private final StringProperty name;
	private final IntegerProperty dayPrice; 
	private final IntegerProperty nightPrice; 
	private ListProperty<Movie> movies;
	
	public Channel() {
		this(null);
	}
	
	public Channel(String name) {
		this.name = new SimpleStringProperty(name);
		this.dayPrice = new SimpleIntegerProperty(1234);
		this.nightPrice = new SimpleIntegerProperty(4321);
		this.movies = new SimpleListProperty<Movie>(FXCollections.observableArrayList());
	}
	
	public String getName() {
		return name.get();
	}
	public void setName(String name) {
		this.name.set(name);
	}
	public StringProperty nameProperty() {
		return name;
	}
	
	public int getDayPrice() {
		return dayPrice.get();
	}
	public void setDayPrice(int price) {
		this.dayPrice.set(price);
	}
	public IntegerProperty dayPriceProperty() {
		return dayPrice;
	}
	
	public int getNightPrice() {
		return nightPrice.get();
	}
	public void setNightPrice(int price) {
		this.nightPrice.set(price);
	}
	public IntegerProperty nightPriceProperty() {
		return nightPrice;
	}
	
	public List<Movie> getMovies() {
		return movies.get();
	}
	public void setMovies(ObservableList<Movie> movies) {
		this.movies.set(movies);
	}
	public ListProperty<Movie> moviesProperty() {
		return movies;
	}
}
