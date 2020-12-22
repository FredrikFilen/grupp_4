package grupp4;

public class IndividualStart extends Thread {

	public void run() {
		for (int i = 0; i < MainController.getSkierList().size(); i++) {
			MainController.getSkierList().get(i).startTime();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}
