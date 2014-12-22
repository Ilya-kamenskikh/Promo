package application.view;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
    private void handleSignUp() {
    	
    }
}
