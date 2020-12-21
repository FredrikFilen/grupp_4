package grupp4;

import java.io.Serializable;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Skier implements Serializable {
	private String name;
	private int number;
	private String time;
	private String lap;

	Timeline timeLine;
	int milliseconds = 0;
	int seconds = 0;
	int minutes = 0;

	public Skier() {

	}

	public Skier(String name, int number, String time, String lap) {
		this.name = name;
		this.number = number;
		this.time = time;
		this.lap = lap;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getLap() {
		return lap;
	}

	public void setLap(String lap) {
		this.lap = lap;
	}

	// end of getters and setters

	public void start() {
		// start riding
		this.startTime();
	}

	public void stop() {
		// stop riding
		System.out.println("The competitor finished the race.");
	}

	public void startTime() {
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
			time = String.format("%02d : %02d : %03d", minutes, seconds, milliseconds);
			this.setTime(time);

		}));
		timeLine.setCycleCount(Animation.INDEFINITE);
		timeLine.play();
	}

	public void stopTime() {
		timeLine.stop();
	}

}
