package application.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
		this.movies = new SimpleListProperty<Movie>(FXCollections.observableArrayList());
		File file = new File("Channels\\"+name+".txt");
		try {
			if(!file.exists()){
				file.createNewFile();
				PrintWriter out = new PrintWriter(file);
				try {
					out.print(1234+" "+4321+"\n");
				} finally {
		            out.close();
		        }
				this.name = new SimpleStringProperty(name);
				this.dayPrice = new SimpleIntegerProperty(1234);
				this.nightPrice = new SimpleIntegerProperty(4321);
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
				this.name = new SimpleStringProperty(name);
			}
		} catch(IOException e) {
            throw new RuntimeException(e);
        }
		
		
		
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
