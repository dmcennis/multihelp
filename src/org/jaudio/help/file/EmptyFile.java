/**
 * EmptyFile
 *
 * Created Sep 15, 2010-5:01:49 PM by Daniel McEnnis
 * Distributed under the BSD license.  See www.fsf.org for license details.
 */
package org.jaudio.help.file;

import java.io.File;

import org.jaudio.help.HelpViewer;

/**
 * EmptyFile
 *
 * @author Daniel McEnnis
 */
public class EmptyFile extends FileNode {
	
	public EmptyFile(File root){
		super(root);
	}
	/* (non-Javadoc)
	 * @see org.jaudio.help.file.FileNode#setPage(org.jaudio.help.HelpViewer)
	 */
	@Override
	public void setPage(HelpViewer viewer) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.jaudio.help.file.FileNode#traverseFileSystem(java.io.File, int)
	 */
	@Override
	public void traverseFileSystem(File root, int depth) {
		// TODO Auto-generated method stub

	}

}
