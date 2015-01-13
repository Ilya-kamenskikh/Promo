package application.view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import org.controlsfx.dialog.Dialogs;

import application.MainApp;
import application.model.Audience;
import application.model.Movie;
import application.model.Theme;

public class NewMovieDialogController {
	
	@FXML
	private TextField name;
	@FXML
	private TextField time;
	@FXML
	private ChoiceBox<String> theme;
	@FXML
	private ChoiceBox<String> audience;
	
	private Stage dialogStage;
	private MainApp mainApp;
	private Theme t;
	private Audience a;
	private Movie movie;
	
	@FXML
	private void initialize() {
		this.theme.getItems().addAll("Cars", "Children", "Social", "Sport", "Food", "Movies");
		this.audience.getItems().addAll("0+", "12+", "16+", "18+");
	}
	
	@FXML
	private void handleOk() {
		if (isInputValid()) {
			switch(theme.getSelectionModel().getSelectedItem()){
				case "Cars": t = Theme.CARS; break;
				case "Children": t = Theme.CHILDREN; break;
				case "Social": t = Theme.SOCIAL; break;
				case "Sport": t = Theme.SPORT; break;
				case "Food": t = Theme.FOOD; break;
				case "Movies": t = Theme.MOVIES; break;
			}
			switch(audience.getSelectionModel().getSelectedItem()){
				case "0+": a = Audience.FOR_ALL; break;
				case "12+": a = Audience.CHILDREN; break;
				case "16+": a = Audience.TEENS; break;
				case "18+": a = Audience.YOUTH; break;
			}
			movie = new Movie(name.getText(), time.getText(), t, a);
			mainApp.getCompany().getMovies().add(movie);
			mainApp.getMovieData().setAll(mainApp.getCompany().getMovies());
			dialogStage.close();
			sendChannel();
			writeFile();
			//mainApp.showCompanyLayout();
		}
	}
	
	@FXML
	private void handleCancel() {
		dialogStage.close();
	}
	
	public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
        
    }
	
	public void setMainApp(MainApp mainApp){
    	this.mainApp = mainApp;
    }
	
	private void writeFile() {
		StringBuilder sb = new StringBuilder();
		File file = new File("Companies\\"+mainApp.getCompany().getName() + ".txt");
		try {
			BufferedReader in = new BufferedReader(
            	new InputStreamReader(
                    new FileInputStream( file.getAbsoluteFile() ), "UTF-8"
                )
            );
            try {
                String s = null;
                while ((s = in.readLine()) != null) {
                    sb.append(s);
                    sb.append("\n");
                }
                sb.append(name.getText() + " " + time.getText() + " " + t.toString() + " " + a.toString() + ":" + "notfound");
                sb.append("\n");
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
	
	private boolean isInputValid() {
    	String errorMessage = "";
    	
    	if (name.getText() == null || name.getText().length() == 0) {
    		errorMessage += "No valid name!\n";
    	}
    	
    	if (time.getText() == null || time.getText().length() == 0) {
    		errorMessage += "No valid time!\n";
    	}
    	
    	if (theme.getSelectionModel().isEmpty()) {
    		errorMessage += "No valid theme!\n";
    	}
    	
    	if (audience.getSelectionModel().isEmpty()) {
    		errorMessage += "No valid audience!\n";
    	}
    	
    	if (errorMessage.length() == 0) {
    		return true;
    	} else {
    		Dialogs.create()
    			.title("Invalid Fields")
    			.masthead("Please correct invalid fields")
    			.message(errorMessage)
    			.showError();
    		return false;
    	}
    }
	private void sendChannel() {
		StringBuilder sb = new StringBuilder();
		File file = new File("TV channel.txt");
		File file1;
		try {
			BufferedReader in = new BufferedReader(
		         new InputStreamReader(
		             new FileInputStream( file.getAbsoluteFile() ), "UTF-8"
		         )
		    );
			try {
				String s = null;
				String s1 = null;
				while ((s = in.readLine())!=null) {
					s = s.substring(s.lastIndexOf(":")+1);
					file1 = new File("Channels\\" + s + ".txt");
					if (file1.exists()) {
						BufferedReader in1 = new BufferedReader(
							new InputStreamReader(
							    new FileInputStream( file1.getAbsoluteFile() ), "UTF-8"
							)
						);
						try {
							s1 = in1.readLine();
							sb.append(s1);
							sb.append("\n");
							sb.append(name.getText() + " " + time.getText() + " " + t.toString() + " " + a.toString() + ":" + "notfound");
							sb.append("\n");
							if (movie.getAudience() == Audience.YOUTH) {
								if (Integer.parseInt(s1.substring(s1.indexOf(" ")+1))*Integer.parseInt(movie.getTime()) < mainApp.getCompany().getBudget() ) {
									mainApp.getCompany().setBudget(mainApp.getCompany().getBudget() - Integer.parseInt(s1.substring(s1.indexOf(" ")+1))*Integer.parseInt(movie.getTime()));
								}
							} else {
								if (Integer.parseInt(s1.substring(0,s1.indexOf(" ")))*Integer.parseInt(movie.getTime()) < mainApp.getCompany().getBudget() ) {
									mainApp.getCompany().setBudget(mainApp.getCompany().getBudget() - Integer.parseInt(s1.substring(0, s1.indexOf(" ")))*Integer.parseInt(movie.getTime()));
								}
							}
						} finally {
							in1.close();
				        }
						PrintWriter out = new PrintWriter(file1);
						try {
							out.print(sb);
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
}
