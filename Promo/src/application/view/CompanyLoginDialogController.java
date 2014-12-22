package application.view;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CompanyLoginDialogController {

	@FXML
    private TextField userName;
    @FXML
    private TextField password;
	
    private Stage dialogStage;
    
    @FXML
    private void initialize() {
    }
    
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }
    
    @FXML
    private void handleLogin() {
    	
    }
}
