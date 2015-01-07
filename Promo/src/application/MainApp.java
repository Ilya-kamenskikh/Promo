package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import application.view.CompanyLoginDialogController;
import application.view.EntryController;
import application.view.RegDialogController;

public class MainApp extends Application {
	private Stage primaryStage;
	private BorderPane rootLayout;

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Promo");
		
		initRootLayout();
		showEntryLayout();
	}

	
	
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showEntryLayout() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/EntryLayout.fxml"));
            AnchorPane entryLayout = (AnchorPane) loader.load();
            
            

            // Set person overview into the center of root layout.
            rootLayout.setCenter(entryLayout);
            
            // Give the controller access to the main app.
            EntryController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void showCompanyLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/CompanyLayout.fxml"));
            AnchorPane companyLayout = (AnchorPane) loader.load();
     

            // Set person overview into the center of root layout.
            rootLayout.setCenter(companyLayout);
            
            // Give the controller access to the main app.
            //CompanyLayoutController controller = loader.getController();
            //controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void showChannelLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/ChannelLayout.fxml"));
            AnchorPane channelLayout = (AnchorPane) loader.load();
            
            rootLayout.setCenter(channelLayout);
            
            // Give the controller access to the main app.
            //ChannelLayoutController controller = loader.getController();
            //controller.setMainApp(this);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void showCompanyLoginDialog() {
    	try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/CompanyLoginDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
     
            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Login Company");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            
            CompanyLoginDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setMainApp(this);
            
            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();
            
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        
    public void showRegLoginDialog() {
    	try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RegDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
     
            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Registration");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            
            RegDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            
            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();
            
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public Stage getPrimaryStage() {
        return primaryStage;
    }

	public static void main(String[] args) {
		launch(args);
	}
}
