package grupp4;

public class Mass_start extends Thread{
	public void run(){
	
	for (int i = 0; i < MainController.getSkierList().size(); i++) {
		MainController.getSkierList().get(i).startTime();
	}
	
	}
}
