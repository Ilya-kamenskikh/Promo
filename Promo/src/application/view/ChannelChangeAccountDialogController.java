package application.view;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import org.controlsfx.dialog.Dialogs;

import application.MainApp;

public class ChannelChangeAccountDialogController {
	
	@FXML
	private TextField nightPrice;
	@FXML
	private TextField morningPrice;
	@FXML
	private TextField dayPrice;
	@FXML
	private TextField afternoonPrice;
	@FXML
	private TextField eveningPrice;
	
	@FXML
	private TextField nightRating;
	@FXML
	private TextField morningRating;
	@FXML
	private TextField dayRating;
	@FXML
	private TextField afternoonRating;
	@FXML
	private TextField eveningRating;
	
	private Stage dialogStage;
	private MainApp mainApp;
	
	@FXML
	private void initialize() {
		
	}
	
	@FXML
	private void handleOk() {
		if (isInputValid()) {
			mainApp.getChannel().getPrice().set(0, Integer.parseInt(nightPrice.getText()));
			mainApp.getChannel().getPrice().set(1, Integer.parseInt(morningPrice.getText()));
			mainApp.getChannel().getPrice().set(2, Integer.parseInt(dayPrice.getText()));
			mainApp.getChannel().getPrice().set(3, Integer.parseInt(afternoonPrice.getText()));
			mainApp.getChannel().getPrice().set(4, Integer.parseInt(eveningPrice.getText()));
			
			mainApp.getChannel().getRatingFactor().set(0, Integer.parseInt(nightRating.getText()));
			mainApp.getChannel().getRatingFactor().set(1, Integer.parseInt(morningRating.getText()));
			mainApp.getChannel().getRatingFactor().set(2, Integer.parseInt(dayRating.getText()));
			mainApp.getChannel().getRatingFactor().set(3, Integer.parseInt(afternoonRating.getText()));
			mainApp.getChannel().getRatingFactor().set(4, Integer.parseInt(eveningRating.getText()));
			
			writeInFile();
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
		
		fillTable();
	}
	
	private boolean isInputValid() {
    	String errorMessage = "";
    	
    	if ((morningPrice.getText() == null || morningPrice.getText().length() == 0) || (nightPrice.getText() == null || nightPrice.getText().length() == 0) || (dayPrice.getText() == null || dayPrice.getText().length() == 0) || (afternoonPrice.getText() == null || afternoonPrice.getText().length() == 0) || (eveningPrice.getText() == null || eveningPrice.getText().length() == 0)) {
    		errorMessage += "No valid price!\n";
    	}
    		
    	if ((morningRating.getText() == null || morningRating.getText().length() == 0) || (nightRating.getText() == null || nightRating.getText().length() == 0) || (dayRating.getText() == null || dayRating.getText().length() == 0) || (afternoonRating.getText() == null || afternoonRating.getText().length() == 0) || (eveningRating.getText() == null || eveningRating.getText().length() == 0)) {
    		errorMessage += "No valid rating!\n";
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
	
	private void writeInFile() {
		File file = new File("Channels\\" + mainApp.getChannel().getName() + "_TimeTable.txt");
		try {
            PrintWriter out = new PrintWriter(file);
			try {
				out.print("night:" + nightPrice.getText() + ":" + nightRating.getText() + ":" + mainApp.getChannel().getTimeLeft().get(0) + "\n" 
						+ "morning:" + morningPrice.getText() + ":" + morningRating.getText() + ":" + mainApp.getChannel().getTimeLeft().get(1) + "\n" 
						+ "day:" + dayPrice.getText() + ":" + dayRating.getText() + ":" + mainApp.getChannel().getTimeLeft().get(2) + "\n"
						+ "afternoon:" + afternoonPrice.getText() + ":" + afternoonRating.getText() + ":" + mainApp.getChannel().getTimeLeft().get(3) + "\n"
						+ "evening:" + eveningPrice.getText() + ":" + eveningRating.getText() + ":" + mainApp.getChannel().getTimeLeft().get(4) + "\n");
			} finally {
	            out.close();
	        }
		} catch(IOException e) {
            throw new RuntimeException(e);
        }
	}
	
	private void fillTable() {
		nightPrice.setText(mainApp.getChannel().getPrice().get(0).toString());
		morningPrice.setText(mainApp.getChannel().getPrice().get(1).toString());
		dayPrice.setText(mainApp.getChannel().getPrice().get(2).toString());
		afternoonPrice.setText(mainApp.getChannel().getPrice().get(3).toString());
		eveningPrice.setText(mainApp.getChannel().getPrice().get(4).toString());
		
		nightRating.setText(mainApp.getChannel().getRatingFactor().get(0).toString());
		morningRating.setText(mainApp.getChannel().getRatingFactor().get(1).toString());
		dayRating.setText(mainApp.getChannel().getRatingFactor().get(2).toString());
		afternoonRating.setText(mainApp.getChannel().getRatingFactor().get(3).toString());
		eveningRating.setText(mainApp.getChannel().getRatingFactor().get(4).toString());
	}
	
}
