package grupp4;

import java.util.ArrayList;

public class Skier {
	private String name = "";
	static int jerseyNumber;
	private int position = 0;
	ArrayList<Skier> jerseyNumbers = new ArrayList();

	public Skier(String name, int position) {
		this.name = name;
		this.position = position;
	}

	public void move() {
		System.out.println("The competitor is racing.");
	}

	public void speed(double speed) {
		// register here speed of competitors
		// speed = position / time;
		System.out.println("The speed is " + speed);
	}

	public void stop() {
		System.out.println("The competitor finished the race.");
	}

}
