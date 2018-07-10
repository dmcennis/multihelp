/**
 * HelpViewer
 *
 * Created Sep 15, 2010-2:19:09 PM by Daniel McEnnis
 * Distributed under the BSD license.  See www.fsf.org for license details.
 */
package org.multihelp;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;

import javax.swing.JEditorPane;
import javax.swing.JOptionPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

import org.multihelp.file.FileNode;
import org.multihelp.file.JavadocFile;

/**
 * HelpViewer
 *
 * @author Daniel McEnnis
 */
public class HelpViewer extends JEditorPane {
	
	public HelpViewer(){
		super();
	}

	protected FileTreeReader fileSource;
	
	protected FileNode currentNode;

	protected Locale nativeLocale = Locale.ENGLISH;

	public void setDefaultStringLocale(Locale l){
		nativeLocale = l;
	}

	private TreeSelectionListener processFileSelection = new TreeSelectionListener() {
		public void valueChanged(TreeSelectionEvent arg0) {
			
			// TODO Auto-generated method stub
			TreePath path = arg0.getNewLeadSelectionPath();
			FileNode node = (FileNode)path.getLastPathComponent();
			node.setPage(reflect());
		}
	};
	
	protected HelpViewer reflect(){
		return this;
	}

	protected HyperlinkListener urlHook = new HyperlinkListener(){
		public void hyperlinkUpdate(HyperlinkEvent arg0) {
			try {
				setPage(arg0.getURL());
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, e.getStackTrace(), "ERROR",
						JOptionPane.ERROR_MESSAGE);
			}
		}		
	};
	
	public HelpViewer(FileTreeReader fileSource){
		this.fileSource = fileSource;
		setEditable(false);
		try {
			JavadocFile n = new JavadocFile(new File("C:\\Users\\user\\Documents\\eclipse-source\\jAudioHelp\\extras\\javadoc\\"));
			n.setPage(this);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getStackTrace(), "ERROR",
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void set(URL source) throws IOException{
		setPage(source);
	}
	
	public void set(String source){
		this.setText(source);
		this.setContentType("text.html");
	}
}
