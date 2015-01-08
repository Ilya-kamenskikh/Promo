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

public class Company {
	private final StringProperty name;
	private final IntegerProperty budget;
	private ListProperty<Movie> movies;
	
	public Company() {
		this(null);
	}
	
	public Company(String name){
		this.name = new SimpleStringProperty(name);
		this.budget = new SimpleIntegerProperty(1000);
		
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

	public int getBudget() {
		return budget.get();
	}
	public void setBudget(int budget) {
		this.budget.set(budget);
	}
	public IntegerProperty budgetProperty() {
		return budget;
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
