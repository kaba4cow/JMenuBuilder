package com.kaba4cow.jmenubuilder;

import java.awt.Dimension;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class JMenuBuilderExample {

	public JMenuBuilderExample(JFrame frame, JMenuBar menu) {
		new JMenuBuilder("File")//
				.item(new JMenuItem("New"), event -> System.out.println("New"))//
				.item(new JMenuItem("Open"), event -> System.out.println("Open"))//
				.separator()//
				.menu(new JMenuBuilder("Recent Files")//
						.execute(b -> {
							for (int i = 0; i < 5; i++)
								b.item(new JMenuItem("File " + i));
						})//
						.separator()//
						.item(new JMenuItem("Clear Files"))//
				)//
				.separator()//
				.item(new JMenuItem("Exit"), event -> frame.dispose())//
				.build(menu);

		new JMenuBuilder("Edit")//
				.item(new JMenuItem("Cut"))//
				.item(new JMenuItem("Copy"))//
				.item(new JMenuItem("Paste"))//
				.build(menu);

		Random random = new Random();
		new JMenuBuilder("Conditions")//
				.menu(new JMenuBuilder("If")//
						.execute(b1 -> {
							for (int i = 0; i < 10; i++) {
								b1.executeIf(() -> random.nextBoolean(), //
										b2 -> b2.item(new JMenuItem("Passed")));
							}
						}))//
				.menu(new JMenuBuilder("If/Else")//
						.execute(b1 -> {
							for (int i = 0; i < 10; i++) {
								b1.executeIfElse(() -> random.nextBoolean(), //
										b2 -> b2.item(new JMenuItem("Passed")), //
										b2 -> b2.item(new JMenuItem("Failed")));
							}
						}))//
				.build(menu);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception e) {
			}
			JFrame frame = new JFrame();
			frame.setTitle("JMenuBuilderExample");
			frame.setPreferredSize(new Dimension(320, 240));

			JMenuBar menu = new JMenuBar();
			new JMenuBuilderExample(frame, menu);
			frame.setJMenuBar(menu);

			frame.pack();
			frame.setLocationRelativeTo(null);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		});
	}

}
