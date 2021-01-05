package grupp4;

import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainController implements Initializable {

	// read and populate list from xml file

	private static ArrayList<Skier> skierList = new ArrayList<Skier>(XML.decode());
	// private static ArrayList<Skier> skierList = new ArrayList<Skier>();

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
	private Button stopAllButton;

	@FXML
	private Button individualStartButton;

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
		skierObservableList = FXCollections.observableArrayList(skierList);
		skierColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
		checkpointColumn.setCellValueFactory(new PropertyValueFactory<>("checkpoint"));

		tableview.setItems(skierObservableList);

		// listener for changes in observablelist
		skierObservableList.get(0).timeProperty().addListener(new ChangeListener() {

			@Override
			public void changed(ObservableValue observable, Object oldValue, Object newValue) {
				tableview.refresh();
			}

		});

		// listener for selection
		tableview.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			if (newSelection != null) {
				stopSkierButton.setDisable(false);
				checkpointButton.setDisable(false);
			}
		});

	}

	@FXML
	void mass_StartTimer(ActionEvent event) {
		if (mass_StartButton.getText().equals("Mass start")) {
			mass_StartButton.setText("STOP RACE");
			newTimeline();
			mass_Start();

		} else {
			mass_StartButton.setText("Mass Start");
			this.timeLine.stop();
			stopAllSkiers();
			individualStartButton.setDisable(false);
			pursuitStartButton.setDisable(false);

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
		skierList.add(skier1);
		skierList.add(skier2);
		skierList.add(skier3);
		skierList.add(skier4);
		skierList.add(skier5);
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
		for (int i = 0; i < skierObservableList.size(); i++) {
			skierObservableList.get(i).startTime();
		}
		individualStartButton.setDisable(true);
		pursuitStartButton.setDisable(true);
	}

	@FXML
	void individualStartButtonPressed(ActionEvent event) {

		if (individualStartButton.getText().equals("Individual Start")) {
			individualStartButton.setText("STOP RACE");
			IndividualStart individualstart = new IndividualStart();
			individualstart.start();

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
			individualStartButton.setDisable(false);

		}

	}

	@FXML
	void lapButtonPressed(ActionEvent event) {
		selectedSkier = tableview.getSelectionModel().getSelectedItem();
		selectedSkier.setCheckpoint(selectedSkier.getTimeProperty());
	}

}
