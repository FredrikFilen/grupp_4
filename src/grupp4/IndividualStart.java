package grupp4;

public class IndividualStart extends Thread {
	int delay = 0;

	public void run() {
		MainController.individualstartRunning = true;

		for (int i = 0; i < MainController.getSkierList().size(); i++) {
			if (i != MainController.getSkierList().size() - 1) {
				MainController.getSkierList().get(i).startTime();
				try {
					Thread.sleep(delay);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {
				MainController.getSkierList().get(i).startTime();
			}

		}
		MainController.individualstartRunning = false;

	}
	
	public void setDelay(int delay) {
		this.delay = delay;
	}

}
