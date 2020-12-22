package grupp4;

public class Jaktstart extends Thread {
	public void run() {

		for (int i = 0; i < MainController.getSkierList().size(); i++) {
			if (i != MainController.getSkierList().size() - 1) {
				long delay = MainController.getSkierList().get(i + 1).getResultInMilliseconds()
						- MainController.getSkierList().get(i).getResultInMilliseconds();
				MainController.getSkierList().get(i).startTime();
				try {
					Thread.sleep(delay);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				MainController.getSkierList().get(i).startTime();
			}

		}

	}
}
