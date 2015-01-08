package application.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import application.MainApp;
import application.model.Movie;

public class CompanyLayoutController {
	@FXML
	private TableView<Movie> table;
	@FXML
	private TableColumn<Movie, String> nameMovie;
	@FXML
	private TableColumn<Movie, String> nameChannel;
	@FXML
	private Label nameMovieLabel;
	@FXML
	private Label timeMovieLabel;
	@FXML
	private Label themeMovieLabel;
	@FXML
	private Label audienceMovieLabel;
	@FXML
	private Label nameCompanyLabel;
	@FXML
	private Label budgetCompanyLabel;
	
	private MainApp mainApp;
	
	@FXML
    private void initialize() {
		nameMovie.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		nameChannel.setCellValueFactory(cellData -> cellData.getValue().nameChannelProperty());
		
		showMovieDetails(null);
		
		table.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> showMovieDetails(newValue));
	}
	
	@FXML
	private void handleNewList(){}
	
	@FXML
	private void handleLogout(){
		mainApp.getMovieData().removeAll(mainApp.getMovieData());
		mainApp.showEntryLayout();
	}
	
	private void showMovieDetails(Movie movie) {
		if (movie!= null) {
			nameMovieLabel.setText(movie.getName());
			timeMovieLabel.setText(movie.getTime());
			themeMovieLabel.setText(movie.getTheme());
			audienceMovieLabel.setText(Integer.toString(movie.getAudience()));
		} else {
			nameMovieLabel.setText("");
			timeMovieLabel.setText("");
			themeMovieLabel.setText("");
			audienceMovieLabel.setText("");
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
}
