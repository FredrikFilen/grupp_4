package grupp4;

import java.io.Serializable;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.util.Duration;

public class Skier implements Serializable, Comparable<Skier> {
	private String name;
	private int number;
	private StringProperty time = new SimpleStringProperty(this, "time", "");
	private String checkpoint;
	long resultInMilliseconds = 0;

	Timeline timeLine;
	int milliseconds = 0;
	int seconds = 0;
	int minutes = 0;

	public Skier() {

	}

	/**
	 * 
	 * @param name
	 * @param number
	 * @param time
	 * @param lap
	 */

	public Skier(String name, int number, String time, String lap) {
		this.name = name;
		this.number = number;
		this.setTimeProperty(time);
		this.checkpoint = lap;
	}

	public StringProperty timeProperty() {
		return time;
	}

	public void setTimeProperty(String time) {
		this.time.set(time);
	}

	public String getTimeProperty() {
		return time.get();
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

	public String getCheckpoint() {
		return checkpoint;
	}

	public void setCheckpoint(String lap) {
		this.checkpoint = lap;
	}

	public long getResultInMilliseconds() {
		return resultInMilliseconds;
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
		this.milliseconds = 0;
		this.seconds = 0;
		this.minutes = 0;
		this.resultInMilliseconds = 0;
		this.timeLine = new Timeline(new KeyFrame(Duration.millis(1), e -> {

			milliseconds++;
			this.resultInMilliseconds += 1;

			if (milliseconds == 1000) {
				milliseconds = 0;
				seconds++;
			}

			if (seconds == 60) {
				seconds = 0;
				minutes++;
			}

			this.setTimeProperty(String.format("%02d : %02d : %03d", minutes, seconds, milliseconds));

		}));
		this.timeLine.setCycleCount(Animation.INDEFINITE);
		this.timeLine.play();
	}

	public void stopTime() {
		this.timeLine.stop();
	}

	/*
	 * This method implements the comparable interface and sort the skiers in
	 * ascending time order. The result of this method will be used as input in
	 * pursuit start.
	 */

	@Override
	public int compareTo(Skier skier) {
		return (int) (this.getResultInMilliseconds() - skier.getResultInMilliseconds());
	}

}
