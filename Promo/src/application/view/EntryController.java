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
		CompanyLoginDialogController.setFlag(true);
		mainApp.showCompanyLoginDialog();
	}
	@FXML
	private void handleOpenChannel() {
		CompanyLoginDialogController.setFlag(false);
		mainApp.showCompanyLoginDialog();
	}
	@FXML
	private void handleReg() {
		mainApp.showRegLoginDialog();
	}
	public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}
