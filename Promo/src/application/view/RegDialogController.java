package application.view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import org.controlsfx.dialog.Dialogs;

public class RegDialogController {
	@FXML
    private TextField name;
	@FXML
    private TextField userName;
    @FXML
    private TextField password;
    @FXML
    private RadioButton Company;
    @FXML
    private RadioButton TV_channel;
    @FXML
    private Label message;
    @FXML
    private ToggleGroup group; 
    private Stage dialogStage;
    
   
    @FXML
    private void initialize() {    	
    }
    
    private boolean isInputValid() {
    	String errorMessage = "";
    	
    	if (name.getText() == null || name.getText().length() == 0) {
    		errorMessage += "No valid name!\n";
    	}
    	
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
    
    private String readFile(String name){
    	StringBuilder sb = new StringBuilder();
    	File file = new File(name);
    	
        try {
        	if(!file.exists()){
				file.createNewFile();
				return "";
			}
        	
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
            } finally {
                in.close();
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }

        return sb.toString();
    }
    
    private void writeFile(String name, String text){
    	File file = new File(name);
		try{
			PrintWriter out = new PrintWriter(file);
			
			try {
				//text = readFile(name) + text;
				out.append(text);
			} finally {
	            out.close();
	        }
			
		} catch(IOException e) {
	        throw new RuntimeException(e);
	    }
    }
    
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }
    
    @FXML
    private void handleSignUp() {
    	if (isInputValid()) {
    		if (group.getSelectedToggle().equals(Company)) {
        		writeFile("Company", readFile("Company") +  userName.getText() + ":" + password.getText() + ":" + name.getText() + " ");
        	} else {
        		writeFile("TV channel", readFile("TV channel") +  userName.getText() + ":" + password.getText() + ":" + name.getText() + " "); 
        	}
    		dialogStage.close();
    	}
    }
}
