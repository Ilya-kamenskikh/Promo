package application.view;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import application.MainApp;
import application.model.Channel;

public class CompanyConclusionContractsController {
	@FXML
	private Label nameMovieLabel;
	
	@FXML
	private Label timeMovieLabel;
	
	@FXML
	private Label availableBudgetLabel;
	
	@FXML
	private TableView<Channel> channelsTable;
	@FXML
	private TableColumn<Channel, String> name;
	
	@FXML
	private Label nightPriceLabel;
	@FXML
	private Label morningPriceLabel;
	@FXML
	private Label dayPriceLabel;
	@FXML
	private Label afternoonPriceLabel;
	@FXML
	private Label eveningPriceLabel;
	
	@FXML
	private Label nightRatingFactorLabel;
	@FXML
	private Label morningRatingFactorLabel;
	@FXML
	private Label dayRatingFactorLabel;
	@FXML
	private Label afternoonRatingFactorLabel;
	@FXML
	private Label eveningRatingFactorLabel;
	
	@FXML
	private Label nightTimeLeftLabel;
	@FXML
	private Label morningTimeLeftLabel;
	@FXML
	private Label dayTimeLeftLabel;
	@FXML
	private Label afternoonTimeLeftLabel;
	@FXML
	private Label eveningTimeLeftLabel;
	
	@FXML
	private TextField nightAmount;
	@FXML
	private TextField morningAmount;
	@FXML
	private TextField dayAmount;
	@FXML
	private TextField afternoonAmount;
	@FXML
	private TextField eveningAmount;
	
	@FXML
	private CheckBox select;
	@FXML
	private Button ok;

	private MainApp mainApp;
	
	private Stage dialogStage;
	
	private List<Boolean> selects = new ArrayList<Boolean>();
	
	private int selectIndex = - 1;
	
	@FXML
	private void initialize() {
		name.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		
		showChannelsDetails(null);
		
		channelsTable.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> showChannelsDetails(newValue));
	}
	
	@FXML
	private void handleOk() {
		
		
	}
	
	@FXML
	private void handleCalculate() {
		
	}
	
	
	@FXML
	private void handleCancel() {
		dialogStage.close();
	}
	
	private void showChannelsDetails(Channel channel) {

			if (channel != null){
				//ok.setOpacity(0);
				
				if (selectIndex >= 0){
					selects.set(selectIndex, select.isSelected());
				}
				
				selectIndex = channelsTable.getSelectionModel().getSelectedIndex();
				
				nightPriceLabel.setText(Integer.toString(channel.getPrice().get(0)));
				morningPriceLabel.setText(Integer.toString(channel.getPrice().get(1)));
				dayPriceLabel.setText(Integer.toString(channel.getPrice().get(2)));
				afternoonPriceLabel.setText(Integer.toString(channel.getPrice().get(3)));
				eveningPriceLabel.setText(Integer.toString(channel.getPrice().get(4)));
				
				nightRatingFactorLabel.setText(Integer.toString(channel.getRatingFactor().get(0)));
				morningRatingFactorLabel.setText(Integer.toString(channel.getRatingFactor().get(1)));
				dayRatingFactorLabel.setText(Integer.toString(channel.getRatingFactor().get(2)));
				afternoonRatingFactorLabel.setText(Integer.toString(channel.getRatingFactor().get(3)));
				eveningRatingFactorLabel.setText(Integer.toString(channel.getRatingFactor().get(4)));
				
				nightTimeLeftLabel.setText(Integer.toString(channel.getTimeLeft().get(0)));
				morningTimeLeftLabel.setText(Integer.toString(channel.getTimeLeft().get(1)));
				dayTimeLeftLabel.setText(Integer.toString(channel.getTimeLeft().get(2)));
				afternoonTimeLeftLabel.setText(Integer.toString(channel.getTimeLeft().get(3)));
				eveningTimeLeftLabel.setText(Integer.toString(channel.getTimeLeft().get(4)));
				
				if (selectIndex>=0)
					select.setSelected(selects.get(selectIndex));
				
				/*if(select.isSelected()){
					ok.setOpacity(1);
				}else{
					ok.setOpacity(0);
				}*/
				
			} else {
				ok.setOpacity(0);
				
				nightPriceLabel.setText(null);
				morningPriceLabel.setText(null);
				dayPriceLabel.setText(null);
				afternoonPriceLabel.setText(null);
				eveningPriceLabel.setText(null);
				
				nightRatingFactorLabel.setText(null);
				morningRatingFactorLabel.setText(null);
				dayRatingFactorLabel.setText(null);
				afternoonRatingFactorLabel.setText(null);
				eveningRatingFactorLabel.setText(null);
				
				nightTimeLeftLabel.setText(null);
				morningTimeLeftLabel.setText(null);
				dayTimeLeftLabel.setText(null);
				afternoonTimeLeftLabel.setText(null);
				eveningTimeLeftLabel.setText(null);
			}

	}
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		
		channelsTable.setItems(mainApp.getChannelsData());
		for (int i = 0; i < mainApp.getChannelsData().size(); ++i) {
			selects.add(Boolean.FALSE);
		}
	}
	
	public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
	
	 public void showMovieInformation(int index) {
		nameMovieLabel.setText(mainApp.getCompany().getMovies().get(index).getName());
		timeMovieLabel.setText(mainApp.getCompany().getMovies().get(index).getTime());
		availableBudgetLabel.setText(Integer.toString(mainApp.getCompany().getBudget()));
	}
}
