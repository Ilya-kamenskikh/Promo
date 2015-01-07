package application.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import application.model.Company;
import application.model.Movie;

public class ChannelLayoutController{
	
	@FXML
	private Label dayPrice;
	@FXML
	private Label nightPrice;
	@FXML
	private Label name;
	@FXML
	private TableView<Movie> content;
	@FXML
	private TableColumn<Movie, String> title;
	@FXML
	private TableColumn<Company, String> company;
	
	public ChannelLayoutController() {}
	
	@FXML
	private void initialize() {		
		title.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		company.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
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
	
}
