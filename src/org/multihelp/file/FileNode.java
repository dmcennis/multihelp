/**
 * FileNode
 *
 * Created Sep 15, 2010-3:16:25 PM by Daniel McEnnis
 * Distributed under the BSD license.  See www.fsf.org for license details.
 */
package org.multihelp.file;

import java.io.File;

import javax.swing.tree.DefaultMutableTreeNode;

import org.multihelp.HelpViewer;

/**
 * FileNode
 *
 * @author Daniel McEnnis
 */
public abstract class FileNode extends DefaultMutableTreeNode{
	
	public FileNode(File root){
		super(root);
	}
	
	protected static MagicCheck magic = new DefaultMagicCheck();
	
	public abstract void setPage(HelpViewer viewer);
	
	public abstract void traverseFileSystem(File root, int depth);
	
	public static FileNode determineType(File root){
		return magic.determineType(root);
	}
	
	public static void setMagic(MagicCheck newMagic){
		if(newMagic != null){
			magic=newMagic;
		}
	}
	
	public static MagicCheck getMagic(){
		return magic;
	}
}
