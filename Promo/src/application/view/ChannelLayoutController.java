package application.view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import org.controlsfx.dialog.Dialogs;

import application.MainApp;
import application.model.Audience;
import application.model.Movie;
import application.model.Theme;

public class ChannelLayoutController{
	
	@FXML
	private Label dayPriceLabel;
	@FXML
	private Label nightPriceLabel;
	@FXML
	private Label nameLabel;
	@FXML
	private Label themeLabel;
	@FXML
	private Label audienceLabel;
	@FXML
	private TableView<Movie> content;
	@FXML
	private TableColumn<Movie, String> title;
	@FXML
	private TableColumn<Movie, String> time;
	
	@FXML
	private Button accept;
	
	@FXML
	private Button reject;
	
	private MainApp mainApp;
	
	private boolean flag;
	
	private String nameCompany;
	
	public ChannelLayoutController() {}
	
	@FXML
	private void initialize() {		
		title.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		time.setCellValueFactory(cellData -> cellData.getValue().timeProperty());
		
		showMovieDetails(null);
		
		content.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> showMovieDetails(newValue));
	}
	
	@FXML
	private void handleEdit(){
		
	}
	
	@FXML
	private void handleAccept(){
		StringBuilder sb = new StringBuilder();
		int selectIndex = content.getSelectionModel().getSelectedIndex();
		if (selectIndex >= 0) {
			File file = new File("Channels\\" + mainApp.getChannel().getName() + ".txt");
			try {
				BufferedReader in = new BufferedReader(
					new InputStreamReader(
					    new FileInputStream( file.getAbsoluteFile() ), "UTF-8"
					)
				);
				try {
					String s = null;
					sb.append(in.readLine());
					sb.append("\n");
					while ((s = in.readLine()) != null) {
	                	if (s.substring(0, s.indexOf(" ")).equals(content.getItems().get(selectIndex).getName()) )
	                		if (s.substring(s.indexOf(" ")+1, s.indexOf(" ", s.indexOf(" ")+1)).equals(content.getItems().get(selectIndex).getTime()))
	                			if (s.substring(s.indexOf(" ", s.indexOf(" ")+1)+1, s.lastIndexOf(" ")).equals(content.getItems().get(selectIndex).getTheme().toString()))
	                				if (s.substring(s.lastIndexOf(" ")+1, s.indexOf(":")).equals(content.getItems().get(selectIndex).getAudience().toString())) {
	                					nameCompany = s.substring(s.indexOf(":")+1);
	                					//movie = s;
	                					changeNameChannelinCompany(content.getItems().get(selectIndex));
	                					s = s.substring(0, s.indexOf(":")) + ":true";
	                				}
	                    sb.append(s);
	                    sb.append("\n");
	                }
					PrintWriter out = new PrintWriter(file);
	    			
	    			try {
	    				out.append(sb);
	    			} finally {
	    	            out.close();
	    	        }
				} finally {
					in.close();
				}
			} catch(IOException e) {
	            throw new RuntimeException(e);
	        }
		}
	}
	
	@FXML
	private void handleReject(){
		StringBuilder sb = new StringBuilder();
		int selectIndex = content.getSelectionModel().getSelectedIndex();
		if (selectIndex >= 0) {
			
			File file = new File("Channels\\" + mainApp.getChannel().getName() + ".txt");
			try {
				BufferedReader in = new BufferedReader(
				    new InputStreamReader(
				        new FileInputStream( file.getAbsoluteFile() ), "UTF-8"
				    )
				);
				try {
					String s = null;
					sb.append(in.readLine());
					sb.append("\n");
	                while ((s = in.readLine()) != null) {
	                	if (s.substring(0, s.indexOf(" ")).equals(content.getItems().get(selectIndex).getName()) )
	                		if (s.substring(s.indexOf(" ")+1, s.indexOf(" ", s.indexOf(" ")+1)).equals(content.getItems().get(selectIndex).getTime()))
	                			if (s.substring(s.indexOf(" ", s.indexOf(" ")+1)+1, s.lastIndexOf(" ")).equals(content.getItems().get(selectIndex).getTheme().toString()))
	                				if (s.substring(s.lastIndexOf(" ")+1, s.indexOf(":")).equals(content.getItems().get(selectIndex).getAudience().toString())) {
	                					nameCompany = s.substring(s.indexOf(":")+1);
	                					continue;
	                				}
	                    sb.append(s);
	                    sb.append("\n");
	                }
	                PrintWriter out = new PrintWriter(file);
	    			
	    			try {
	    				out.append(sb);
	    			} finally {
	    	            out.close();
	    	        }
				} finally {
					in.close();
				}
			} catch(IOException e) {
	            throw new RuntimeException(e);
	        }
			changeChannel(content.getItems().get(selectIndex), nameCompany);
			content.getItems().remove(selectIndex);
		} else {
			Dialogs.create()
            	.title("No Selection")
            	.masthead("No Person Selected")
            	.message("Please select a person in the table.")
            	.showWarning();
		}
	}
	
	@FXML
	private void handleLogout(){
		mainApp.getMovieData().removeAll(mainApp.getMovieData());
		mainApp.showEntryLayout();
	}
	private void showMovieDetails(Movie movie) {
		if (movie!= null) {
			themeLabel.setText(movie.getTheme().toString());
			switch(movie.getAudience()) {
				case FOR_ALL:audienceLabel.setText("0+"); break;
				case CHILDREN:audienceLabel.setText("12+"); break;
				case TEENS:audienceLabel.setText("16+"); break;
				case YOUTH:audienceLabel.setText("18+"); break;
			}
			if (!movie.getNameChannel().equals("not found")) {
				accept.setOpacity(0);
				reject.setOpacity(0);
			} else {
				accept.setOpacity(1);
				reject.setOpacity(1);
			}
		} else {
			themeLabel.setText("none");
			audienceLabel.setText("none");
		}
	}
	
	private void changeChannel(Movie movie, String nameCompany) {
		StringBuilder sb1 = new StringBuilder();
		File file2 = new File("TV channel.txt");
		File file1;
		try {
			BufferedReader in = new BufferedReader(
		         new InputStreamReader(
		             new FileInputStream( file2.getAbsoluteFile() ), "UTF-8"
		         )
		    );
			try {
				String s = null;
				String s1 = null;
				String s2 = null;
				flag = true;
				while ((s = in.readLine())!=null && flag) {
					s = s.substring(s.lastIndexOf(":")+1);
					if (s.equals(mainApp.getChannel().getName()))
						continue;
					file1 = new File("Channels\\" + s + ".txt");
					if (file1.exists()) {
						BufferedReader in1 = new BufferedReader(
							new InputStreamReader(
							    new FileInputStream( file1.getAbsoluteFile() ), "UTF-8"
							)
						);
						try {
							s1 = in1.readLine();
							sb1.append(s1);
							sb1.append("\n");
							s2 = in1.readLine();
							if (s2 == null || ((movie.getTheme() == Theme.MOVIES || movie.getTheme() == Theme.FOOD) && (s2.substring(s2.indexOf(" ", s2.indexOf(" ")+1)+1, s2.lastIndexOf(" ")).equals("MOVIES") || s2.substring(s2.indexOf(" ", s2.indexOf(" ")+1)+1, s2.lastIndexOf(" ")).equals("FOOD"))) || ((movie.getTheme() == Theme.SPORT || movie.getTheme() == Theme.CARS) && (s2.substring(s2.indexOf(" ", s2.indexOf(" ")+1)+1, s2.lastIndexOf(" ")).equals("SPORT") || s2.substring(s2.indexOf(" ", s2.indexOf(" ")+1)+1, s2.lastIndexOf(" ")).equals("CARS"))) || ((movie.getTheme() == Theme.SOCIAL) && (s2.substring(s2.indexOf(" ", s2.indexOf(" ")+1)+1, s2.lastIndexOf(" ")).equals("SOCIAL"))) || ((movie.getTheme() == Theme.CHILDREN) && (s2.substring(s2.indexOf(" ", s2.indexOf(" ")+1)+1, s2.lastIndexOf(" ")).equals("CHILDREN"))))
								if (movie.getAudience() == Audience.YOUTH) {
										sb1.append(movie.getName() + " " + movie.getTime() + " " + movie.getTheme().toString() + " " + movie.getAudience().toString() + ":" + nameCompany);
										sb1.append("\n");
										flag = false;
								} else {
										sb1.append(movie.getName() + " " + movie.getTime() + " " + movie.getTheme().toString() + " " + movie.getAudience().toString() + ":" + nameCompany);
										sb1.append("\n");
										flag = false;
								}
							if (s2!=null) {
								sb1.append(s2);
								sb1.append("\n");
								while ((s2 = in1.readLine()) != null) {
									sb1.append(s2);
									sb1.append("\n");
								}
							}
						} finally {
							in1.close();
				        }
						PrintWriter out = new PrintWriter(file1);
						try {
							out.print(sb1);
						} finally {
				            out.close();
				        }
					}
				}
			} finally {
				in.close();
			}
		} catch(IOException e) {
            throw new RuntimeException(e);
        }
	}
	
	private void changeNameChannelinCompany(Movie movie) {
		StringBuilder sb = new StringBuilder();
		movie.setNameChannel(mainApp.getChannel().getName());
		File file = new File("Companies\\" + nameCompany + ".txt");
		try {
			BufferedReader in = new BufferedReader(
				new InputStreamReader(
					new FileInputStream( file.getAbsoluteFile() ), "UTF-8"
				)
			);
			try {
				String s = null;
				String s1 = null;
				if (movie.getAudience() == Audience.YOUTH) {
					sb.append(Integer.parseInt(in.readLine()) - mainApp.getChannel().getNightPrice() * Integer.parseInt(movie.getTime()));
				} else {
					sb.append(Integer.parseInt(in.readLine()) - mainApp.getChannel().getDayPrice() * Integer.parseInt(movie.getTime()));
				}
				//int i = Integer.parseInt(in.readLine()) - mainApp.getChannel().getDayPrice()*(Integer.parseInt(movie.substring(movie.indexOf(" ")+1, movie.indexOf(" ", movie.indexOf(" ")+1))));
				sb.append("\n");
				while ((s = in.readLine())!=null){
					if (s.substring(0, s.indexOf(" ")).equals(movie.getName()) )
                		if (s.substring(s.indexOf(" ")+1, s.indexOf(" ", s.indexOf(" ")+1)).equals(movie.getTime()))
                			if (s.substring(s.indexOf(" ", s.indexOf(" ")+1)+1, s.lastIndexOf(" ")).equals(movie.getTheme().toString()))
                				if (s.substring(s.lastIndexOf(" ")+1, s.indexOf(":")).equals(movie.getAudience().toString())) {
									s1 = mainApp.getChannel().getName();
									s1.replace(" ", "_");
									s = s.substring(0, s.indexOf(":")) + ":" + s1;
					}
					sb.append(s);
					sb.append("\n");
				}
				PrintWriter out = new PrintWriter(file);
				try {
					out.print(sb);
				} finally {
		            out.close();
		        }
			} finally {
				in.close();
	        }
		} catch(IOException e) {
            throw new RuntimeException(e);
        }
	}
	
	public void showChannel() {
		nameLabel.setText(mainApp.getChannel().getName());
		dayPriceLabel.setText(Integer.toString(mainApp.getChannel().getDayPrice()));
		nightPriceLabel.setText(Integer.toString(mainApp.getChannel().getNightPrice()));
	}
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		
		content.setItems(mainApp.getMovieData());
	}
}
