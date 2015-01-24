package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import application.model.Channel;
import application.model.Company;
import application.model.Movie;
import application.view.ChannelChangeAccountDialogController;
import application.view.ChannelLayoutController;
import application.view.CompanyChangeAccountController;
import application.view.CompanyConclusionContractsController;
import application.view.CompanyLayoutController;
import application.view.CompanyLoginDialogController;
import application.view.EntryController;
import application.view.NewMovieDialogController;
import application.view.RegDialogController;

public class MainApp extends Application {
	private Stage primaryStage;
	private BorderPane rootLayout;
	private ObservableList<Movie> movieData = FXCollections.observableArrayList();
	private ObservableList<Channel> channelsData = FXCollections.observableArrayList();
	private Company company;
	private Channel channel;
	private String login;
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getLogin(){
		return login;
	}
	
	public ObservableList<Channel> getChannelsData() {
		return channelsData;
	}
	
	public ObservableList<Movie> getMovieData() {
		return movieData;
	}
	
	public void setChannel(Channel channel) {
		this.channel = channel;
	}
	
	public Channel getChannel() {
		return channel;
	}
	
	public void setCompany(Company company) {
		this.company = company;
	}
	
	public Company getCompany() {
		return company;
	}

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Promo");
		
		initRootLayout();
		showEntryLayout();
	}

	public MainApp(){
		//movieData.add(new Movie());
		//movieData.add(new Movie("Bad", "14", "g", 14));
		//movieData.addAll(company.getMovies());
	}
	
    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showEntryLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/EntryLayout.fxml"));
            AnchorPane entryLayout = (AnchorPane) loader.load();
            
            rootLayout.setCenter(entryLayout);
            
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
     
            rootLayout.setCenter(companyLayout);
            
            CompanyLayoutController controller = loader.getController();
            controller.setMainApp(this);
            controller.showCompany();
            
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
            
            ChannelLayoutController controller = loader.getController();
            controller.setMainApp(this);
            controller.showChannel();
            
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
    
    public void showNewMovieDialog() {
    	try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/NewMovieDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
     
            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Add movie");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            
            NewMovieDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setMainApp(this);
            
            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();
            
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void showCompanyChangeAccount() {
    	try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/CompanyChangeAccountDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
     
            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Change Account");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            
            CompanyChangeAccountController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setMainApp(this);
            
            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();
            
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void showChannelChangeAccount() {
    	try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/ChannelChangeAccountDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
     
            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Change Account");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            
            ChannelChangeAccountDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setMainApp(this);
            
            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();
            
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void showCompanyConclusionContracts() {
    	try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/CompanyConclusionContracts.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
     
            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Contracts");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            
            CompanyConclusionContractsController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setMainApp(this);
            
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
