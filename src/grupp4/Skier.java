package grupp4;

public class Skier {
	private String name = "";
	static int jerseyNumber = 10;
	private int position;

	public Skier(String name, int position) {
		this.name = name;
		this.position = position;
	}

	public void move() {
		System.out.println("The competitor is racing.");
	}

	public void stop() {
		System.out.println("The competitor finished the race.");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static int getJerseyNumber() {
		return jerseyNumber;
	}

	public static void setJerseyNumber(int jerseyNumber) {
		Skier.jerseyNumber = jerseyNumber;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

}
