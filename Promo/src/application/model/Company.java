package application.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

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
		this.movies = new SimpleListProperty<Movie>(FXCollections.observableArrayList());
		File file = new File("Companies\\"+name+".txt");
		try {
    		if(!file.exists()){
				file.createNewFile();
				PrintWriter out = new PrintWriter(file);
				try {
					out.print(10000 + "\n");
				} finally {
		            out.close();
		        }
				this.name = new SimpleStringProperty(name);
				this.budget = new SimpleIntegerProperty(10000);
			} else {
				BufferedReader in = new BufferedReader(
			        new InputStreamReader(
			            new FileInputStream( file.getAbsoluteFile() ), "UTF-8"
			        )
			    );
				try {
					String s = null;
					String nameMovie = null;
					String timeMovie = null;
					Theme theme = null;
					Audience audience = null;
					//int rating = 0;
					//οττ
					
					this.budget = new SimpleIntegerProperty(Integer.parseInt(in.readLine()));
	                while ((s = in.readLine()) != null) {
	                    nameMovie = s.substring(0, s.indexOf(" "));
	                    timeMovie = s.substring(s.indexOf(" ")+1, s.indexOf(" ", s.indexOf(" ")+1));
	                    //theme = s.substring(s.indexOf(" ", s.indexOf(" ")+1)+1, s.lastIndexOf(" "));
	                    //audience = s.substring(s.lastIndexOf(" "));
	                    switch(s.substring(s.indexOf(" ", s.indexOf(" ")+1)+1, s.lastIndexOf(" "))){
		    				case "CARS": theme = Theme.CARS; break;
		    				case "CHILDREN": theme = Theme.CHILDREN; break;
		    				case "SOCIAL": theme = Theme.SOCIAL; break;
		    				case "SPORT": theme = Theme.SPORT; break;
		    				case "FOOD": theme = Theme.FOOD; break;
		    				case "MOVIES": theme = Theme.MOVIES; break;
	                    }
	                    switch(s.substring(s.lastIndexOf(" ")+1, s.indexOf(":"))){
		    				case "FOR_ALL": audience = Audience.FOR_ALL; break;
		    				case "CHILDREN": audience = Audience.CHILDREN; break;
		    				case "TEENS": audience = Audience.TEENS; break;
		    				case "YOUTH": audience = Audience.YOUTH; break;
	                    }
	                    //rating = Integer.parseInt(s.substring(s.lastIndexOf(" ")+1, s.indexOf(":")));
	                    getMovies().add(new Movie(nameMovie, timeMovie, theme, audience, Integer.parseInt(s.substring(s.indexOf(":")+1))));
	                }
				} finally {
					in.close();
				}
				this.name = new SimpleStringProperty(name);
			}
		} catch(IOException e) {
            throw new RuntimeException(e);
        }
	}
	
//οττ
	private void getMoviesInformationFile(String name){
		File file = new File("Companies\\Movies\\"+name+".txt");
		try {
			BufferedReader in = new BufferedReader(
				new InputStreamReader(
				    new FileInputStream( file.getAbsoluteFile() ), "UTF-8"
				)
			);
			try {

			} finally {
				in.close();
			} 
		} catch(IOException e) {
            throw new RuntimeException(e);
        }
	}
//	

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

	public ObservableList<Movie> getMovies() {
		return movies.get();
	}
	public void setMovies(ObservableList<Movie> movies) {
		this.movies.set(movies);
	}
	public ListProperty<Movie> moviesProperty() {
		return movies;
	}
	
	
}
