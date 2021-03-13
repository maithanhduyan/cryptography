package com.sha256;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

public class Main {

	public static void main(String[] args) {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window frame = new Window();
					frame.setVisible(true);
					frame.setLocation(dim.width / 2 - frame.getSize().width / 2,
							dim.height / 2 - frame.getSize().height / 2);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}
