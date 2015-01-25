package application.view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import application.MainApp;
import application.model.Movie;
import application.model.Channel;

public class CompanyLayoutController {
	@FXML
	private TableView<Movie> table;
	@FXML
	private TableColumn<Movie, String> nameMovie;
	@FXML
	private Label nameMovieLabel;
	@FXML
	private Label timeMovieLabel;
	@FXML
	private Label themeMovieLabel;
	@FXML
	private Label ratingMovieLabel;
	@FXML
	private Label audienceMovieLabel;
	@FXML
	private Label nameCompanyLabel;
	@FXML
	private Label budgetCompanyLabel;
	
	@FXML
	private Button contracts;
	@FXML
	private Button newContracts;
	
	
	private MainApp mainApp;
	
	@FXML
    private void initialize() {
		nameMovie.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		
		showMovieDetails(null);
		
		table.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> showMovieDetails(newValue));
	}
	
	@FXML
	private void handleNewMovie(){	
		mainApp.showNewMovieDialog();
	}
	
	@FXML
	private void handleChangeAccount() {
		mainApp.showCompanyChangeAccount();
	}
	
	@FXML
	private void handleLogout(){
		mainApp.getMovieData().removeAll(mainApp.getMovieData());
		mainApp.showEntryLayout();
	}
	
	@FXML
	private void handleContracts() {
		
	}
	
	@FXML
	private void handleNewContracts() {
		getChannelsTable();
		int selectIndex = table.getSelectionModel().getSelectedIndex();
		mainApp.setIndex(selectIndex);
		mainApp.showCompanyConclusionContracts();
	}
	
	private void showMovieDetails(Movie movie) {
		if (movie!= null) {
			contracts.setOpacity(1);
			newContracts.setOpacity(1);
			nameMovieLabel.setText(movie.getName());
			timeMovieLabel.setText(movie.getTime());
			themeMovieLabel.setText(movie.getTheme().toString());
			switch(movie.getAudience()) {
				case FOR_ALL:audienceMovieLabel.setText("0+"); break;
				case CHILDREN:audienceMovieLabel.setText("12+"); break;
				case TEENS:audienceMovieLabel.setText("16+"); break;
				case YOUTH:audienceMovieLabel.setText("18+"); break;
			}
			ratingMovieLabel.setText(movie.getRatingChannel().toString());
			//audienceMovieLabel.setText(movie.getAudience().toString());
		} else {
			contracts.setOpacity(0);
			newContracts.setOpacity(0);
			nameMovieLabel.setText("none");
			timeMovieLabel.setText("none");
			themeMovieLabel.setText("none");
			audienceMovieLabel.setText("none");
			ratingMovieLabel.setText("none");
		}
	}
	
	public void showCompany() {
		nameCompanyLabel.setText(mainApp.getCompany().getName());
		budgetCompanyLabel.setText(Integer.toString( mainApp.getCompany().getBudget()));
	}
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		
		table.setItems(mainApp.getMovieData());
	}
	
	
	private void getChannelsTable() {
		File file = new File("TV channel.txt");
		try {
			if (!file.exists()) {
				file.createNewFile();
				return;
			}
			BufferedReader in = new BufferedReader(
				new InputStreamReader(
				    new FileInputStream( file.getAbsoluteFile() ), "UTF-8"
				)
			);
			try {
				String s = null;
				String nameChannel = null;
				mainApp.getChannelsData().clear();
				while ((s = in.readLine()) != null) {
					nameChannel = s.substring(s.lastIndexOf(":")+1);
					mainApp.getChannelsData().add(new Channel(nameChannel));
				}
			} finally {
				in.close();
			} 
		} catch(IOException e) {
			throw new RuntimeException(e);
		}		
	}

}
		
	

