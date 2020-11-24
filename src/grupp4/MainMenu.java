package grupp4;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class MainMenu implements ActionListener {
	JFrame frame = new JFrame();
	JLabel label1 = new JLabel("Välkommen till vårt skidprogram");
	JButton individualButton = new JButton("Individuell start");
	JButton masstartButton = new JButton("Masstart");
	JButton jaktstartButton = new JButton("Jaktstart");
	

	MainMenu() {
		label1.setBounds(400, 50, 500, 100);

		individualButton.setBounds(50, 200, 250, 50);
		individualButton.setFont(new Font("Garamond", Font.PLAIN, 20));
		individualButton.setFocusable(false);
		individualButton.addActionListener(this);
		
		masstartButton.setBounds(350, 200, 250, 50);
		masstartButton.setFont(new Font("Garamond", Font.PLAIN, 20));
		masstartButton.setFocusable(false);
		masstartButton.addActionListener(this);
		
		jaktstartButton.setBounds(650, 200, 250, 50);
		jaktstartButton.setFont(new Font("Garamond", Font.PLAIN, 20));
		jaktstartButton.setFocusable(false);
		jaktstartButton.addActionListener(this);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000, 500);
		frame.setLayout(null);
		frame.setVisible(true);

		frame.add(label1);
		frame.add(individualButton);
		frame.add(masstartButton);
		frame.add(jaktstartButton);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == individualButton) {
			Stopwatch stopwatch = new Stopwatch();
			stopwatch.start();
		} else if (e.getSource() == masstartButton) {
			Stopwatch stopwatch = new Stopwatch();
			stopwatch.start();
		} else if (e.getSource() == jaktstartButton) {
			Stopwatch stopwatch = new Stopwatch();
			stopwatch.start();
		}

	}

}
