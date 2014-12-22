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

public class MainApp extends Application {
	private Stage primaryStage;
	private BorderPane rootLayout;

    public MainApp() {
        // Add some sample data
        
    }
    
    
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
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/CompanyLayout.fxml"));
            AnchorPane companyLayout = (AnchorPane) loader.load();
     

            // Set person overview into the center of root layout.
            rootLayout.setCenter(companyLayout);
            
            // Give the controller access to the main app.
            //PersonOverviewController controller = loader.getController();
            //controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void showChannelLayout() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/ChannelLayout.fxml"));
            AnchorPane channelLayout = (AnchorPane) loader.load();
     
            // Set person overview into the center of root layout.
            rootLayout.setCenter(channelLayout);
            
            // Give the controller access to the main app.
            //PersonOverviewController controller = loader.getController();
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
            dialogStage.setTitle("Login");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            
            CompanyLoginDialogController controller = loader.getController();
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
