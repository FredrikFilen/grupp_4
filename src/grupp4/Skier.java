package grupp4;

public class Skier {
	private String name = "";
	static int jerseyNumber = 0;
	private int position = 0;

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

}
