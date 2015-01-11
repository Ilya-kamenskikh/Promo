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
	
	private void showMovieDetails(Movie movie) {
		if (movie!= null) {
			nameMovieLabel.setText(movie.getName());
			timeMovieLabel.setText(movie.getTime());
			themeMovieLabel.setText(movie.getTheme().toString());
			switch(movie.getAudience()) {
				case FOR_ALL:audienceMovieLabel.setText("0+"); break;
				case CHILDREN:audienceMovieLabel.setText("12+"); break;
				case TEENS:audienceMovieLabel.setText("16+"); break;
				case YOUTH:audienceMovieLabel.setText("18+"); break;
			}
			//audienceMovieLabel.setText(movie.getAudience().toString());
		} else {
			nameMovieLabel.setText("none");
			timeMovieLabel.setText("none");
			themeMovieLabel.setText("none");
			audienceMovieLabel.setText("none");
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
