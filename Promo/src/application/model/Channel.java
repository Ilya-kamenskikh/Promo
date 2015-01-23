package application.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Channel {
	private final StringProperty name;
	
	//private final IntegerProperty nightPrice; 
	//private final IntegerProperty morningPrice; 
	//private final IntegerProperty dayPrice;
	//private final IntegerProperty afternoonPrice;
	//private final IntegerProperty eveningPrice;
	
	private final ListProperty<Integer> price;
	
	//private final IntegerProperty nightRatingFactor;
	//private final IntegerProperty morningRatingFactor;
	//private final IntegerProperty dayRatingFactor;
	//private final IntegerProperty afternoonRatingFactor;
	//private final IntegerProperty eveningRatingFactor;
	
	private final ListProperty<Integer> ratingFactor;
	
	private final ListProperty<Integer> timeLeft;
	
	private ListProperty<Movie> movies;
	
	
	public Channel() {
		this(null);
	}
	
	public Channel(String name) {
		this.movies = new SimpleListProperty<Movie>(FXCollections.observableArrayList());
		this.price = new SimpleListProperty<Integer>(FXCollections.observableArrayList());
		this.ratingFactor = new SimpleListProperty<Integer>(FXCollections.observableArrayList());
		this.timeLeft = new SimpleListProperty<Integer>(FXCollections.observableArrayList());
		this.name = new SimpleStringProperty(name);
		getTableFile(name);
		
		getMoviesFile(name);
		
	}
	
	private void getMoviesFile(String name){
		File file = new File("Channels\\"+name+".txt");
		try {
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
	                getMovies().add(new Movie(nameMovie, timeMovie, theme, audience, Integer.parseInt(s.substring(s.indexOf(";")+1))));
	            }
			} finally {
				in.close();
			} 
		} catch(IOException e) {
            throw new RuntimeException(e);
        }
	}
	
	private void getTableFile(String name){
		File file = new File("Channels\\"+name+"_TimeTable.txt");
		try {
			if(!file.exists()){
				file.createNewFile();
				PrintWriter out = new PrintWriter(file);
				try {
					out.print("night:" + 150 + ":" + 5 + ":" + 4300 + "\n" 
							+ "morning:" + 250 + ":" + 15 + ":" + 2100 + "\n" 
							+ "day:" + 200 + ":" + 10 + ":" + 5700 + "\n"
							+ "afternoon:" + 250 + ":" + 15 + ":" + 2100 + "\n"
							+ "evening:" + 300 + ":" + 20 + ":" + 2800 + "\n");
				} finally {
		            out.close();
		        }
				
				getPrice().add(150);
				getPrice().add(250);
				getPrice().add(200);
				getPrice().add(250);
				getPrice().add(300);
				
				getRatingFactor().add(5);
				getRatingFactor().add(15);
				getRatingFactor().add(10);
				getRatingFactor().add(15);
				getRatingFactor().add(20);
				
				getTimeLeft().add(4300);
				getTimeLeft().add(2100);
				getTimeLeft().add(5700);
				getTimeLeft().add(2100);
				getTimeLeft().add(2800);
						
			} else {
					BufferedReader in = new BufferedReader(
					    new InputStreamReader(
					        new FileInputStream( file.getAbsoluteFile() ), "UTF-8"
					    )
					);
					try {
						String s = null;
						while ((s = in.readLine()) != null) {
							getPrice().add(Integer.parseInt(s.substring(s.indexOf(":")+1, s.indexOf(":", s.indexOf(":")+1))));
							getRatingFactor().add( Integer.parseInt( s.substring(s.indexOf(":", s.indexOf(":")+1)+1, s.lastIndexOf(":"))));
							getTimeLeft().add(Integer.parseInt(s.substring(s.lastIndexOf(":"))));
						}
					} finally {
						in.close();
					} 
			}
		} catch(IOException e) {
            throw new RuntimeException(e);
        }
					
					/*try {
						String s = null;
						String nameMovie = null;
						String timeMovie = null;
						Theme theme = null;
						Audience audience = null;
						s = in.readLine();
						this.dayPrice = new SimpleIntegerProperty(Integer.parseInt(s.substring(0, s.indexOf(" "))));
						this.nightPrice = new SimpleIntegerProperty(Integer.parseInt(s.substring(s.indexOf(" ")+1)));
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
		                    if (s.substring(s.indexOf(":")+1).equals("true")) {
		                    	getMovies().add(new Movie(nameMovie, timeMovie, theme, audience, name));
		                    } else {
		                    	getMovies().add(new Movie(nameMovie, timeMovie, theme, audience));
		                    }
		                }
					} finally {
						in.close();
					}
					this.name = new SimpleStringProperty(name);*/
				
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
	
	public List<Integer> getPrice() {
		return price.get();
	}
	public void setPrice(ObservableList<Integer> price) {
		this.price.set(price);
	}
	public ListProperty<Integer> priceProperty() {
		return price;
	}
	
	public List<Integer> getRatingFactor() {
		return ratingFactor.get();
	}
	public void setRatingFactor(ObservableList<Integer> ratingFactor) {
		this.ratingFactor.set(ratingFactor);
	}
	public ListProperty<Integer> ratingFactorProperty() {
		return ratingFactor;
	}
	
	public List<Integer> getTimeLeft() {
		return timeLeft.get();
	}
	public void setTimeLeft(ObservableList<Integer> timeLeft) {
		this.timeLeft.set(timeLeft);
	}
	public ListProperty<Integer> timeLeftProperty() {
		return timeLeft;
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
