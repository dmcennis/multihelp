/**
 * HelpWindow
 *
 * Created Sep 15, 2010-12:20:16 PM by Daniel McEnnis
 * Distributed under the BSD license.  See www.fsf.org for license details.
 */
package org.multihelp;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

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

	private FileTreeReader leftPane;

	private HelpViewer rightPane;
	
	public HelpWindow() {
		super();
		leftPane= new FileTreeReader(
				new File(
						"C:\\Users\\user\\Documents\\eclipse-source\\jAudioHelp\\extras\\javadoc"));
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
