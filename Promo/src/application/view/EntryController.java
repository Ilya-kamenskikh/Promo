package application.view;

import javafx.fxml.FXML;
import application.MainApp;

public class EntryController {
	private MainApp mainApp;
	
	public EntryController() {	
	}
	
	@FXML
	private void initialize() {		
	}
	
	@FXML
	private void handleOpenCompany() {
		mainApp.showCompanyLoginDialog();
	}
	@FXML
	private void handleOpenChannel() {
		mainApp.showCompanyLoginDialog();
	}
	@FXML
	private void handleReg() {
		
	}
	public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}
