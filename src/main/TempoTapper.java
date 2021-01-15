package main;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class TempoTapper extends JFrame {
	private static final long serialVersionUID = 1L;
	
	JButton button;
	JLabel bpm_label;
	
	private enum TAPStatus { IDLE, TAP };
	TAPStatus status = TAPStatus.IDLE;
	
	long first_ms = 0, second_ms = 0;
	
	private void create() {
		button = new JButton("TAP");
		button.setMaximumSize(new Dimension(300, 300));
		button.setFont(new Font("Arial", Font.BOLD, 75));
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				switch(status) {
					case IDLE:
						first_ms = System.currentTimeMillis();
						bpm_label.setText("Tap again");
						
						status = TAPStatus.TAP;
						break;
						
					case TAP:
						second_ms = System.currentTimeMillis();
						
						int bpm = 60000 / (int)(second_ms - first_ms);
						
						if(bpm <= 30) {
							bpm_label.setText("Too slow");
							bpm_label.setForeground(Color.RED);
						} else if(bpm >= 300) {
							bpm_label.setText("Too fast");
							bpm_label.setForeground(Color.RED);
						} else {
							bpm_label.setText("" + bpm);
							bpm_label.setForeground(Color.BLACK);
						}
						

						first_ms = second_ms;
						
						break;
				}
				
				System.currentTimeMillis();
			}
		});
		
		bpm_label = new JLabel("Tap to begin", SwingConstants.CENTER);
		bpm_label.setMaximumSize(new Dimension(300, 100));
		bpm_label.setFont(new Font("Arial", Font.BOLD, 30));
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panel.add(button);
		panel.add(bpm_label);
		
		getContentPane().add(panel);
	}
	
	public TempoTapper() {
		setSize(new Dimension(300, 400));
		setTitle("Tempo tapper");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		create();
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new TempoTapper();
	}
}