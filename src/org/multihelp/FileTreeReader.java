/**
 * FileTreeReader
 *
 * Created Sep 15, 2010-12:23:30 PM by Daniel McEnnis
 * Distributed under the BSD license.  See www.fsf.org for license details.
 */
package org.multihelp;

import java.io.File;

import javax.swing.JTree;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

import org.multihelp.file.FileNode;

/**
 * FileTreeReader
 * 
 * @author Daniel McEnnis
 */
public class FileTreeReader extends JTree{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FileTreeReader(File root) {
		FileNode base = FileNode.determineType(new File("."));
		setModel(new DefaultTreeModel(base));
		if ((root != null) && root.exists() && root.isDirectory()) {
			base.traverseFileSystem( root, 0);
		}
		getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
	}


	public void addTreeSelectionListener(TreeSelectionListener listener){
		getSelectionModel().addTreeSelectionListener(listener);		
	}
}
