package com.promo.clients;

import java.util.ArrayList;
import java.util.List;

import com.promo.type.Movie;

public class Company {
	private String  name;
	private Integer budget;
	private List<Movie> Movies = new ArrayList<Movie>();
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getBudget() {
		return budget;
	}
	public void setBudget(Integer budget) {
		this.budget = budget;
	}
	public void addMovie(Movie m){
		Movies.add(m);
	}
	public List<Movie> getMovies() {
		return Movies;
	}
}
