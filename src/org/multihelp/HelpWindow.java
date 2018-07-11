/**
 * HelpWindow
 *
 * Created Sep 15, 2010-12:20:16 PM by Daniel McEnnis
 * Distributed under the BSD license.  See www.fsf.org for license details.
 */
package org.multihelp;

import java.awt.*;
import java.io.File;

import javax.swing.*;

/**
 * HelpWindow
 * 
 * @author Daniel McEnnis
 */
public class HelpWindow extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JFrame leftFrame;

	private FileTreeReader leftPane;

	private HelpViewer rightPane;

	private FileNodeMomento momento;

	public HelpWindow() {
		super();
		leftFrame = new JFrame();
		leftFrame.setLayout(new BorderLayout());

		JButton back = new JButton();
		back.setIcon(new ImageIcon("BackActive.png"));
		back.setDisabledIcon(new ImageIcon("BackDisabled.png"));

		JButton forward = new JButton();
		forward.setIcon(new ImageIcon("forwardActive.png"));
		forward.setDisabledIcon(new ImageIcon("forwardDisabled.png"));

		JFrame buttonLayout = new JFrame();
		buttonLayout.setLayout(new FlowLayout());
		buttonLayout.add(back);
		buttonLayout.add(back);
		leftFrame.add(buttonLayout,BorderLayout.NORTH);

		leftPane= new FileTreeReader(
				new File(
						"."));
		momento = new FileNodeMomento(leftPane);
		back.addActionListener(momento.back);
		forward.addActionListener(momento.forward);
		rightPane = new HelpViewer(leftPane);
		
		JScrollPane rightScroll = new JScrollPane();
		rightScroll.setPreferredSize(new Dimension(600, 400));
		rightScroll.getViewport().add(rightPane);
		JSplitPane core = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true);
		Container root = getContentPane();
		root.add(core, BorderLayout.CENTER);
		JScrollPane leftScroll = new JScrollPane();
		leftScroll.setPreferredSize(new Dimension(200, 400));
		leftScroll.getViewport().add(leftPane);
		core.setLeftComponent(leftScroll);
		core.setRightComponent(rightScroll);
		pack();
		setVisible(true);
	}
}
