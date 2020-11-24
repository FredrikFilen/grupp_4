package grupp4;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MainMenu implements ActionListener {
	JFrame frame = new JFrame();
	JLabel label1 = new JLabel("Välkommen till vårt skidprogram");
	JButton newTimerButton = new JButton("Starta timer");

	MainMenu() {
		label1.setBounds(400, 50, 500, 100);

		newTimerButton.setBounds(300, 200, 500, 50);
		newTimerButton.setFont(new Font("Garamond", Font.PLAIN, 20));
		newTimerButton.setFocusable(false);
		newTimerButton.addActionListener(this);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000, 1000);
		frame.setLayout(null);
		frame.setVisible(true);

		frame.add(label1);
		frame.add(newTimerButton);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == newTimerButton) {
			Stopwatch stopwatch = new Stopwatch();
			stopwatch.start();
		}

	}

}
