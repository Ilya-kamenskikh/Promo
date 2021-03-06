package application.view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import org.controlsfx.dialog.Dialogs;

import application.MainApp;
import application.model.Channel;
import application.model.Company;

public class CompanyLoginDialogController {

	@FXML
    private TextField userName;
    @FXML
    private PasswordField password;
	
    private Stage dialogStage;
    
    private MainApp mainApp;
    
    private String nameC;

    private static boolean flag;
    
    private boolean isInputExist(String name) {
    	File file = new File(name);
    	String errorMessage = "";
    	try {
    		if(!file.exists()){
				Dialogs.create()
					.title("Login not exists")
					.masthead("Please enter the correct username and password")
					.message("Not username and password")
					.showError();
				return false;
			}
    		
	    	 BufferedReader in = new BufferedReader(
	             new InputStreamReader(
	                 new FileInputStream( file.getAbsoluteFile() ), "UTF-8"
	             )
	         );
	    	 try {
	    		 String s = null;
	    		 
	    		 while ((s = in.readLine())!=null){
	    			 errorMessage = "This username not exist";
	    			 if (s.substring(0, s.indexOf(':')).equals(userName.getText())){
	    				 errorMessage = "";
	    				 
	    				 if (!s.substring(s.indexOf(':')+1, s.lastIndexOf(':')).equals(password.getText())){
	    					 errorMessage += "This password not exist";
	    					 break;
	    				 }
	    				 nameC = s.substring(s.lastIndexOf(':')+1);
	    				 break;
	    			 }
	    		 }
	    	 } finally {
	             in.close();
	         }
    	} catch(IOException e) {
            throw new RuntimeException(e);
        }
    	
    	if (errorMessage.length() == 0) {
    		return true;
    	} else {
    		Dialogs.create()
			.title("Login not exists")
			.masthead("Please enter the correct username and password")
			.message(errorMessage)
			.showError();
    		return false;
    	}	
    }
    
    private boolean isInputValid() {
    	String errorMessage = "";
    	
    	if (userName.getText() == null || userName.getText().length() == 0) {
    		errorMessage += "No valid login!\n";
    	}
    	
    	if (password.getText() == null || password.getText().length() == 0) {
    		errorMessage += "No valid password!\n";
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
    
    
    @FXML
    private void initialize() {
    }
    
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    public void setMainApp(MainApp mainApp){
    	this.mainApp = mainApp;
    }
    
    public static void setFlag(boolean f){
    	flag = f;
    }
    
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }
    
    @FXML
    private void handleLogin() {
    	if (isInputValid()) {
    		
    		if (flag) {
    			if (isInputExist("Company.txt")) {
    				dialogStage.close();
    				mainApp.setCompany(new Company(nameC));
    				//if (!mainApp.getCompany().getMovies().isEmpty())
    					mainApp.getMovieData().setAll(mainApp.getCompany().getMovies());
    				mainApp.setLogin(userName.getText());
    				mainApp.showCompanyLayout();
    			}
    		} else {
    			if (isInputExist("TV channel.txt")) {
    				dialogStage.close();
    				mainApp.setChannel(new Channel(nameC));
    				if (!mainApp.getChannel().getMovies().isEmpty())
    					mainApp.getMovieData().setAll(mainApp.getChannel().getMovies());
    				mainApp.showChannelLayout();
    			}
    		}
    	}
    }
}
