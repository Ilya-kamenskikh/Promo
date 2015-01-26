package application.view;

import java.util.ArrayList;
import java.util.List;

import org.controlsfx.dialog.Dialogs;

import javafx.collections.ObservableList;
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
	private List<String> localNightAmounts = new ArrayList<String>();
	private List<String> localMorningAmounts = new ArrayList<String>();
	private List<String> localDayAmounts = new ArrayList<String>();
	private List<String> localAfternoonAmounts = new ArrayList<String>();
	private List<String> localEveningAmounts = new ArrayList<String>();
	
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
		int sum = 0;
		for (int i = 0; i < mainApp.getChannelsData().size(); ++i) {
			if (selects.get(i) == true){
				sum = sum + mainApp.getChannelsData().get(i).getPrice().get(0) * Integer.valueOf(timeMovieLabel.getText()) * 
						Integer.valueOf(nightAmount.getText()) +
					mainApp.getChannelsData().get(i).getPrice().get(1) * Integer.valueOf(timeMovieLabel.getText()) * 
						Integer.valueOf(morningAmount.getText()) +
					mainApp.getChannelsData().get(i).getPrice().get(2) * Integer.valueOf(timeMovieLabel.getText()) * 
						Integer.valueOf(dayAmount.getText()) +
					mainApp.getChannelsData().get(i).getPrice().get(3) * Integer.valueOf(timeMovieLabel.getText()) * 
						Integer.valueOf(afternoonAmount.getText()) +
					mainApp.getChannelsData().get(i).getPrice().get(4) * Integer.valueOf(timeMovieLabel.getText()) * 
						Integer.valueOf(eveningAmount.getText());
				
				int nightTime = Integer.valueOf(nightTimeLeftLabel.getText()) - 
						(Integer.valueOf(nightAmount.getText()) * Integer.valueOf(timeMovieLabel.getText()));
				mainApp.getChannelsData().get(i).getTimeLeft().set(0, nightTime);
				
				int morningTime = Integer.valueOf(morningTimeLeftLabel.getText()) - 
						(Integer.valueOf(morningAmount.getText()) * Integer.valueOf(timeMovieLabel.getText()));			
				mainApp.getChannelsData().get(i).getTimeLeft().set(1, morningTime);
				
				int dayTime = Integer.valueOf(dayTimeLeftLabel.getText()) - 
						(Integer.valueOf(dayAmount.getText()) * Integer.valueOf(timeMovieLabel.getText()));	
				mainApp.getChannelsData().get(i).getTimeLeft().set(2, dayTime);
				
				int afternoonTime = Integer.valueOf(afternoonTimeLeftLabel.getText()) - 
						(Integer.valueOf(afternoonAmount.getText()) * Integer.valueOf(timeMovieLabel.getText()));		
				mainApp.getChannelsData().get(i).getTimeLeft().set(3, afternoonTime);
				
				int eveningTime = Integer.valueOf(eveningTimeLeftLabel.getText()) - 
						(Integer.valueOf(eveningAmount.getText()) * Integer.valueOf(timeMovieLabel.getText()));
				mainApp.getChannelsData().get(i).getTimeLeft().set(4, eveningTime);
			}
		}
		
		dialogStage.close();
	}
	
	@FXML
	private void handleCalculate() {
		String errorMessage = "";
		String goodMessage = "";
		int sum=0;
		for (int i = 0; i < mainApp.getChannelsData().size(); ++i) {
			if (selects.get(i) == true){
				sum = sum + mainApp.getChannelsData().get(i).getPrice().get(0) * Integer.valueOf(timeMovieLabel.getText()) * 
							Integer.valueOf(nightAmount.getText()) +
						mainApp.getChannelsData().get(i).getPrice().get(1) * Integer.valueOf(timeMovieLabel.getText()) * 
							Integer.valueOf(morningAmount.getText()) +
						mainApp.getChannelsData().get(i).getPrice().get(2) * Integer.valueOf(timeMovieLabel.getText()) * 
							Integer.valueOf(dayAmount.getText()) +
						mainApp.getChannelsData().get(i).getPrice().get(3) * Integer.valueOf(timeMovieLabel.getText()) * 
							Integer.valueOf(afternoonAmount.getText()) +
						mainApp.getChannelsData().get(i).getPrice().get(4) * Integer.valueOf(timeMovieLabel.getText()) * 
							Integer.valueOf(eveningAmount.getText());
				
				int nightTime = Integer.valueOf(nightAmount.getText()) * Integer.valueOf(timeMovieLabel.getText());
				if (nightTime >Integer.valueOf(nightTimeLeftLabel.getText())) {
		    		errorMessage += "This amount of movies is too much in the night\n";
		    	}
				
				int morningTime = Integer.valueOf(morningAmount.getText()) * Integer.valueOf(timeMovieLabel.getText());
				if (morningTime >Integer.valueOf(morningTimeLeftLabel.getText())) {
		    		errorMessage += "This amount of movies is too much in the morning\n";
		    	}
				
				int dayTime = Integer.valueOf(dayAmount.getText()) * Integer.valueOf(timeMovieLabel.getText());
				if (dayTime >Integer.valueOf(dayTimeLeftLabel.getText())) {
		    		errorMessage += "This amount of movies is too much in the day\n";
		    	}
				
				int afternoonTime = Integer.valueOf(afternoonAmount.getText()) * Integer.valueOf(timeMovieLabel.getText());
				if (afternoonTime >Integer.valueOf(afternoonTimeLeftLabel.getText())) {
		    		errorMessage += "This amount of movies is too much in the afernoon\n";
		    	}
				
				int eveningTime = Integer.valueOf(eveningAmount.getText()) * Integer.valueOf(timeMovieLabel.getText());
				if (eveningTime >Integer.valueOf(eveningTimeLeftLabel.getText())) {
		    		errorMessage += "This amount of movies is too much in the evening\n";
		    	}
			}
		}
		
		if (sum > Integer.valueOf(availableBudgetLabel.getText())) {
    		errorMessage += "This amount of movies is too expensive\n";
    	}else{
    		String varStr = "";
    		varStr = Integer.toString(sum);
    		goodMessage += "Cost of the contract: " + varStr + "\n";
    	}
		
		if (errorMessage.length() == 0) {
			ok.setOpacity(1);
			Dialogs.create()
				.title("Conditions of the contract")
				.message(goodMessage);		
		}else{
			Dialogs.create()
				.title("Impracticable contract")
				.masthead("Please change conditions of the contact")
				.message(errorMessage)
				.showError();
		}	
		
	}
	
	
	@FXML
	private void handleCancel() {
		dialogStage.close();
	}
	
	
	
	private void showChannelsDetails(Channel channel) {
			ok.setOpacity(0);
			if (channel != null){
				
				if (selectIndex >= 0){
					selects.set(selectIndex, select.isSelected());
					localNightAmounts.set(selectIndex, nightAmount.getText());
					localMorningAmounts.set(selectIndex, morningAmount.getText());
					localDayAmounts.set(selectIndex, dayAmount.getText());
					localAfternoonAmounts.set(selectIndex, afternoonAmount.getText());
					localEveningAmounts.set(selectIndex, eveningAmount.getText());
				}
				
				selectIndex = channelsTable.getSelectionModel().getSelectedIndex();
				
				nightPriceLabel.setText(Integer.toString(channel.getPrice().get(0)*Integer.valueOf(timeMovieLabel.getText())));
				morningPriceLabel.setText(Integer.toString(channel.getPrice().get(1)*Integer.valueOf(timeMovieLabel.getText())));
				dayPriceLabel.setText(Integer.toString(channel.getPrice().get(2)*Integer.valueOf(timeMovieLabel.getText())));
				afternoonPriceLabel.setText(Integer.toString(channel.getPrice().get(3)*Integer.valueOf(timeMovieLabel.getText())));
				eveningPriceLabel.setText(Integer.toString(channel.getPrice().get(4)*Integer.valueOf(timeMovieLabel.getText())));
				
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
				
				
				
				if (selectIndex>=0){
					select.setSelected(selects.get(selectIndex));
					nightAmount.setText(localNightAmounts.get(selectIndex));
					morningAmount.setText(localMorningAmounts.get(selectIndex));
					dayAmount.setText(localDayAmounts.get(selectIndex));
					afternoonAmount.setText(localAfternoonAmounts.get(selectIndex));
					eveningAmount.setText(localEveningAmounts.get(selectIndex));
					
				}
					
				
				
			} else {
				
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
			localNightAmounts.add("0");
			localMorningAmounts.add("0");
			localDayAmounts.add("0");
			localAfternoonAmounts.add("0");
			localEveningAmounts.add("0");
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
