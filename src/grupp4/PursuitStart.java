package grupp4;



/*Calculates the timedifference between the skiers from the last result and uses the result for the startdelay.
Starts each skier after that delay. Doesnt create a delay when it is the last skiers turn to start.
 * */

public class PursuitStart extends Thread {
	public void run() {

		for (int i = 0; i < MainController.getSkierList().size(); i++) {
			if (i != MainController.getSkierList().size() - 1) {
				long delay = MainController.getSkierList().get(i + 1).getResultInMilliseconds()
						- MainController.getSkierList().get(i).getResultInMilliseconds();
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

	}
}
