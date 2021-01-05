package grupp4;

public class IndividualStart extends Thread {
	int delay = 0;

	public void run() {
		for (int i = 0; i < MainController.getSkierList().size(); i++) {
			MainController.getSkierList().get(i).startTime();
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}
	
	public void setDelay(int delay) {
		this.delay = delay;
	}

}
