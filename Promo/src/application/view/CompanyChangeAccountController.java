package application.view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import org.controlsfx.dialog.Dialogs;

import application.MainApp;

public class CompanyChangeAccountController {
	
	@FXML
	private TextField budget;
	
	private Stage dialogStage;
	
	private MainApp mainApp;
	
	@FXML
	private void initialize() {}
	
	@FXML
	private void handleOk(){
		if (isInputValid()) {
			File file = new File("Companies\\"+mainApp.getCompany().getName() + ".txt");
			
			if (!(budget.getText() == null || budget.getText().length() == 0)) {
				mainApp.getCompany().setBudget(Integer.parseInt(budget.getText()));
				StringBuilder sb = new StringBuilder();
				try {
					BufferedReader in = new BufferedReader(
		            	new InputStreamReader(
		                    new FileInputStream( file.getAbsoluteFile() ), "UTF-8"
		                )
		            );
		            try {
		                String s = null;
		                in.readLine();
		                sb.append(budget.getText());
		                sb.append("\n");
		                while ((s = in.readLine()) != null) {
		                    sb.append(s);
		                    sb.append("\n");
		                }
		            } finally {
		                in.close();
		            }
		            PrintWriter out = new PrintWriter(file);
					
					try {
						out.append(sb);
					} finally {
			            out.close();
			        }
				} catch(IOException e) {
		            throw new RuntimeException(e);
		        }
			}
			
		}
		dialogStage.close();
		mainApp.showCompanyLayout();
	}
	
	@FXML 
	private void handleCancel() {
		dialogStage.close();
	}
	
	public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	private boolean isInputValid() {
    	String errorMessage = "";
    	
    	if ((budget.getText() == null || budget.getText().length() == 0)) {
    		errorMessage += "No valid budget!\n";
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
	
	/*private void changeCompanyName(String name) {
		StringBuilder sb = new StringBuilder();
		File file = new File("Company.txt");
		try {
			BufferedReader in = new BufferedReader(
	            new InputStreamReader(
	                new FileInputStream( file.getAbsoluteFile() ), "UTF-8"
	            )
	        );
	        try {
	            String s = null;
	            while ((s = in.readLine()) != null) {
	            	if (s.substring(0, s.indexOf(':')).equals(mainApp.getLogin())){
	            		s = s.substring(0, s.lastIndexOf(':')+1);
	            		s+=name;
	            	}
	                sb.append(s);
	                sb.append("\n");
	            }
	            
	        } finally {
	            in.close();
	        }
	        PrintWriter out = new PrintWriter(file);
			
			try {
				out.append(sb);
			} finally {
	            out.close();
	        }
		} catch(IOException e) {
            throw new RuntimeException(e);
        }
	}*/
}
