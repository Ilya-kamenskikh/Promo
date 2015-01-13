package application.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import application.MainApp;
import application.model.Movie;

public class ChannelLayoutController{
	
	@FXML
	private Label dayPriceLabel;
	@FXML
	private Label nightPriceLabel;
	@FXML
	private Label nameLabel;
	@FXML
	private Label themeLabel;
	@FXML
	private Label audienceLabel;
	@FXML
	private TableView<Movie> content;
	@FXML
	private TableColumn<Movie, String> title;
	@FXML
	private TableColumn<Movie, String> time;
	
	private MainApp mainApp;
	
	public ChannelLayoutController() {}
	
	@FXML
	private void initialize() {		
		title.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		time.setCellValueFactory(cellData -> cellData.getValue().timeProperty());
		
		showMovieDetails(null);
		
		content.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> showMovieDetails(newValue));
	}
	
	@FXML
	private void handleEdit(){
		
	}
	
	@FXML
	private void handleAccept(){
		
	}
	
	@FXML
	private void handleReject(){
		
	}
	
	@FXML
	private void handleLogout(){
		mainApp.getMovieData().removeAll(mainApp.getMovieData());
		mainApp.showEntryLayout();
	}
	private void showMovieDetails(Movie movie) {
		if (movie!= null) {
			themeLabel.setText(movie.getTheme().toString());
			switch(movie.getAudience()) {
				case FOR_ALL:audienceLabel.setText("0+"); break;
				case CHILDREN:audienceLabel.setText("12+"); break;
				case TEENS:audienceLabel.setText("16+"); break;
				case YOUTH:audienceLabel.setText("18+"); break;
			}
		} else {
			themeLabel.setText("none");
			audienceLabel.setText("none");
		}
	}
	
	public void showChannel() {
		nameLabel.setText(mainApp.getChannel().getName());
		dayPriceLabel.setText(Integer.toString(mainApp.getChannel().getDayPrice()));
		nightPriceLabel.setText(Integer.toString(mainApp.getChannel().getNightPrice()));
	}
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		
		content.setItems(mainApp.getMovieData());
	}
}
