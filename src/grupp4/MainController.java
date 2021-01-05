package grupp4;


import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;

public class MainController implements Initializable {

	// read and populate list from xml file

	private static ArrayList<Skier> skierList = new ArrayList<Skier>(XML.decode());
	//private static ArrayList<Skier> skierList = new ArrayList<Skier>();

	// populates the observablelist
	ObservableList<Skier> skierObservableList = FXCollections.observableArrayList(skierList);

	// variable to track the selected skier
	private static Skier selectedSkier;

	// for the timedisplay
	int milliseconds = 0;
	int seconds = 0;
	int minutes = 0;
	String timeToDisplay;
	Timeline timeLine;

	@FXML
	private Button mass_StartButton;

	@FXML
	private Button stopSkierButton;

	@FXML
	private Button checkpointButton;

	@FXML
	private Button individualStartButton;
	
	@FXML
    private ChoiceBox<String> delayChoiceBox;
	
	@FXML
	private Button clearHistoryButton;

	@FXML
	private Button pursuitStartButton;

	@FXML
	private Label timerDisplay;

	@FXML
	private TableView<Skier> tableview;

	@FXML
	private TableColumn<Skier, String> skierColumn;

	@FXML
	private TableColumn<Skier, String> timeColumn;

	@FXML
	private TableColumn<Skier, String> checkpointColumn;

	@SuppressWarnings({ "unchecked", "unchecked", "rawtypes" })
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//initialize tableview and columns
		skierObservableList = FXCollections.observableArrayList(skierList);
		skierColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
		checkpointColumn.setCellValueFactory(new PropertyValueFactory<>("checkpoint"));

		tableview.setItems(skierObservableList);
		
		//initialize choicebox
		delayChoiceBox.getItems().add("Choose delay");
		delayChoiceBox.getItems().add("15 seconds");
		delayChoiceBox.getItems().add("30 seconds");
		delayChoiceBox.setValue("Choose delay");


		// listener for changes in observablelist
		skierObservableList.get(0).timeProperty().addListener(new ChangeListener() {

			@Override
			public void changed(ObservableValue observable, Object oldValue, Object newValue) {
				tableview.refresh();
			}

		});

		// listener for tableview selection
		tableview.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			if (newSelection != null) {
				stopSkierButton.setDisable(false);
				checkpointButton.setDisable(false);
			}
		});
		
		//listener for choicebox selection
		delayChoiceBox.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) ->{
			if(newSelection != "Choose delay") {
				individualStartButton.setDisable(false);
			}else {
				individualStartButton.setDisable(true);
			}
		});

	}

	@FXML
	void mass_StartTimer(ActionEvent event) {
		if (mass_StartButton.getText().equals("Mass start")) {
			mass_StartButton.setText("STOP RACE");
			
			
			minutes = 0;
			seconds = 0;
			milliseconds = 0;
			clearHistory(event);
			mass_Start();
			newTimeline();

		} else {
			mass_StartButton.setText("Mass start");
			this.timeLine.stop();
			stopAllSkiers();
			pursuitStartButton.setDisable(false);
			delayChoiceBox.setDisable(false);

		}
	}

	void newTimeline() {
		timeLine = new Timeline(new KeyFrame(Duration.millis(1), e -> {
			milliseconds++;

			if (milliseconds == 1000) {
				milliseconds = 0;
				seconds++;
			}

			if (seconds == 60) {
				seconds = 0;
				minutes++;
			}
			timeToDisplay = String.format("%02d : %02d : %03d", minutes, seconds, milliseconds);
			timerDisplay.setText(timeToDisplay);
		}));
		timeLine.setCycleCount(Animation.INDEFINITE);
		timeLine.play();
	}

	
	void stopAllSkiers() {

		for (int i = 0; i < skierList.size(); i++) {
			skierList.get(i).stopTime();
		}

	}

	@FXML
	void clearHistory(ActionEvent event) {
		for (int i = 0; i < skierList.size(); i++) {
			skierList.get(i).setTimeProperty("");
			skierList.get(i).setCheckpoint("");
		}
	}

	public void createSkiers() {
		Skier skier1 = new Skier("Anders", 1, "00:00:00:00", "0");
		Skier skier2 = new Skier("Erik", 2, "00:00:00:00", "0");
		Skier skier3 = new Skier("Frida", 3, "00:00:00:00", "0");
		Skier skier4 = new Skier("Mia", 4, "00:00:00:00", "0");
		Skier skier5 = new Skier("Alex", 5, "00:00:00:00", "0");
		Skier skier6 = new Skier("Anna", 5, "00:00:00:00", "0");
		Skier skier7 = new Skier("Lollo", 5, "00:00:00:00", "0");
		Skier skier8 = new Skier("Fredrik", 5, "00:00:00:00", "0");
		Skier skier9 = new Skier("Abebe", 5, "00:00:00:00", "0");
		Skier skier10 = new Skier("Marcus", 5, "00:00:00:00", "0");
		skierList.add(skier1);
		skierList.add(skier2);
		skierList.add(skier3);
		skierList.add(skier4);
		skierList.add(skier5);
		skierList.add(skier6);
		skierList.add(skier7);
		skierList.add(skier8);
		skierList.add(skier9);
		skierList.add(skier10);
	}

	public static ArrayList<Skier> getSkierList() {
		return skierList;
	}

	@FXML
	void stopButton(ActionEvent event) {
		selectedSkier = tableview.getSelectionModel().getSelectedItem();
		selectedSkier.stopTime();
	}

	public void mass_Start() {
		Mass_start mass_start = new Mass_start();
		mass_start.run();
		individualStartButton.setDisable(true);
		pursuitStartButton.setDisable(true);
		delayChoiceBox.setDisable(true);
	
	}

	@FXML
	void individualStartButtonPressed(ActionEvent event) {

		if (individualStartButton.getText().equals("Individual Start")) {
			individualStartButton.setText("STOP RACE");
			
			IndividualStart individualstart = new IndividualStart();
			int selectedindex = delayChoiceBox.getSelectionModel().getSelectedIndex();
			clearHistory(event);
			if(selectedindex == 1) {
				individualstart.setDelay(15000);
				individualstart.start();
			}else {
				individualstart.setDelay(30000);
				individualstart.start();
			}
			
			minutes = 0;
			seconds = 0;
			milliseconds = 0;

			newTimeline();

			mass_StartButton.setDisable(true);
			pursuitStartButton.setDisable(true);

		} else {
			individualStartButton.setText("Individual Start");
			this.timeLine.stop();
			stopAllSkiers();
			mass_StartButton.setDisable(false);
			pursuitStartButton.setDisable(false);

		}

	}
	

	@FXML
	void pursuitStartButtonPressed(ActionEvent event) {

		if (pursuitStartButton.getText().equals("Pursuit Start")) {
			pursuitStartButton.setText("STOP RACE");
			SkierSorter skiersorter = new SkierSorter(skierList);
			skierList = skiersorter.getSortedSkierListByTime();
			
			clearHistory(event);

			minutes = 0;
			seconds = 0;
			milliseconds = 0;

			newTimeline();

			PursuitStart jaktstart = new PursuitStart();
			jaktstart.start();

			mass_StartButton.setDisable(true);
			individualStartButton.setDisable(true);

		} else {
			pursuitStartButton.setText("Pursuit Start");
			this.timeLine.stop();
			stopAllSkiers();
			mass_StartButton.setDisable(false);

		}

	}

	@FXML
	void checkpointButtonPressed(ActionEvent event) {
		selectedSkier = tableview.getSelectionModel().getSelectedItem();
		selectedSkier.setCheckpoint(selectedSkier.getTimeProperty());
	}

}
