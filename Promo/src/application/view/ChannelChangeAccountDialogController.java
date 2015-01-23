package application.view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import org.controlsfx.dialog.Dialogs;

import application.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ChannelChangeAccountDialogController {
	
	@FXML
	private TextField dayPrice;
	@FXML
	private TextField nightPrice;
	
	private Stage dialogStage;
	private MainApp mainApp;
	
	@FXML
	private void initialize() {}
	
	@FXML
	private void handleOk() {
		if (isInputValid()) {
			File file = new File("Channels\\"+mainApp.getChannel().getName() + ".txt");
			
			if (!(dayPrice.getText() == null || dayPrice.getText().length() == 0)) {
				mainApp.getChannel().setDayPrice(Integer.parseInt(dayPrice.getText()));
				StringBuilder sb = new StringBuilder();
				try {
					BufferedReader in = new BufferedReader(
		            	new InputStreamReader(
		                    new FileInputStream( file.getAbsoluteFile() ), "UTF-8"
		                )
		            );
		            try {
		                String s = null;
		                s = in.readLine();
		                sb.append(dayPrice.getText() + s.substring(s.indexOf(" ")));
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
			if (!(nightPrice.getText() == null || nightPrice.getText().length() == 0)) {
				mainApp.getChannel().setDayPrice(Integer.parseInt(nightPrice.getText()));
				StringBuilder sb = new StringBuilder();
				try {
					BufferedReader in = new BufferedReader(
		            	new InputStreamReader(
		                    new FileInputStream( file.getAbsoluteFile() ), "UTF-8"
		                )
		            );
		            try {
		                String s = null;
		                s = in.readLine();
		                sb.append(s.substring(0, s.indexOf(" ")+1)+nightPrice.getText());
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
		mainApp.showChannelLayout();
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
    	
    	if ((dayPrice.getText() == null || dayPrice.getText().length() == 0) && (nightPrice.getText() == null || nightPrice.getText().length() == 0)) {
    		errorMessage += "No valid price!\n";
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
	
}
