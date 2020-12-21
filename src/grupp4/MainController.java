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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;

public class MainController implements Initializable {

	// read and populate list from xml file

	private static ArrayList<Skier> skierList = new ArrayList<Skier>(XML.decode());
	// private static ArrayList<Skier> skierList = new ArrayList<Skier>();

	// populates the observablelist
	ObservableList<Skier> skierTable = FXCollections.observableArrayList(skierList);

	// variable to track the selected skier
	private static Skier selectedSkier;

	// for the timedisplay
	int milliseconds = 0;
	int seconds = 0;
	int minutes = 0;
	String timeToDisplay;
	Timeline timeLine;

	@FXML
	private Label welcomeLabel;

	@FXML
	private Button startButton;

	@FXML
	private Button resetButton;

	@FXML
	private Button testButton;

	@FXML
	private Button stopButton;

	@FXML
	private Button clearHistoryButton;

	@FXML
	private Label timerDisplay;

	@FXML
	private TableView<Skier> tableview;

	@FXML
	private TableColumn<Skier, String> skierColumn;

	@FXML
	private TableColumn<Skier, String> timeColumn;

	@FXML
	private TableColumn<Skier, String> lapColumn;

	@SuppressWarnings({ "unchecked", "unchecked", "rawtypes" })
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		skierTable = FXCollections.observableArrayList(skierList);
		skierColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
		lapColumn.setCellValueFactory(new PropertyValueFactory<>("lap"));

		tableview.setItems(skierTable);

		skierTable.get(0).timeProperty().addListener(new ChangeListener() {

			@Override
			public void changed(ObservableValue observable, Object oldValue, Object newValue) {
				tableview.refresh();
			}

		});

	}

	@FXML
	void resetTimer(ActionEvent event) {
		timeLine.stop();
		milliseconds = 0;
		seconds = 0;
		minutes = 0;

		timerDisplay.setText("00 : 00 : 000");
		startButton.setText("START");
		startButton.setStyle("-fx-background-color: green");
	}

	@FXML
	void startTimer(ActionEvent event) {
		if (startButton.getText().equals("START")) {
			startButton.setText("STOP");
			startButton.setStyle("-fx-background-color: red");
			newTimeline();
			for (int i = 0; i < skierTable.size(); i++) {
				skierTable.get(i).startTime();
			}

		} else {
			startButton.setText("START");
			startButton.setStyle("-fx-background-color: green");
			timeLine.stop();

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

	@FXML
	void clearHistory(ActionEvent event) {
		tableview.getItems().clear();
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
	void test(ActionEvent event) {
		selectedSkier = tableview.getSelectionModel().getSelectedItem();
		selectedSkier.startTime();
	}

	@FXML
	void stopButton(ActionEvent event) {
		selectedSkier = tableview.getSelectionModel().getSelectedItem();
		selectedSkier.stopTime();
	}

}
