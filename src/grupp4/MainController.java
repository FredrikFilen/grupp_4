package grupp4;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
	private Button clearHistoryButton;

	@FXML
	private Label timerDisplay;

	@FXML
	private TableView<DateTimeLap> dateTimeLapTable;

	@FXML
	private TableColumn<DateTimeLap, String> dateColumn;

	@FXML
	private TableColumn<DateTimeLap, String> timeColumn;

	@FXML
	private TableColumn<DateTimeLap, String> lapColumn;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
		timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));

		dateTimeLapTable.setItems(savedTime);

	}

	ObservableList<DateTimeLap> savedTime = FXCollections.observableArrayList();

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

		} else {
			startButton.setText("START");
			startButton.setStyle("-fx-background-color: green");
			timeLine.stop();
			savedTime.add(new DateTimeLap(timeToDisplay));
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
		dateTimeLapTable.getItems().clear();
	}

}
