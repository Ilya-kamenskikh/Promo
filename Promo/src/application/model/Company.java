package application.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Company {
	private final StringProperty name;
	private final IntegerProperty budget;
	private ObjectProperty<Movie> movies;
	
	public Company() {
		this(null, null);
	}
	
	public Company(String name, Integer budget){
		this.name = new SimpleStringProperty(name);
		this.budget = new SimpleIntegerProperty(budget);
		
		this.movies = new SimpleObjectProperty<Movie>();
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

	public Movie getMovies() {
		return movies.get();
	}
	public void setMovies(Movie movie) {
		this.movies.set(movie);
	}
	public ObjectProperty<Movie> moviesProperty() {
		return movies;
	}
	
	
}
