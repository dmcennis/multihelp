/**
 * FileTreeReader
 *
 * Created Sep 15, 2010-12:23:30 PM by Daniel McEnnis
 * Distributed under the BSD license.  See www.fsf.org for license details.
 */
package org.multihelp;

import java.io.File;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JTree;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

import com.google.cloud.translate.Translate;
import org.multihelp.file.FileNode;

/**
 * FileTreeReader
 * 
 * @author Daniel McEnnis
 */
public class FileTreeReader extends JTree{

	public static JavaStringInternationalisation translator = new JavaStringInternationalisation();

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FileTreeReader(File root) {
		File source = root;
		if(source == null){
			source = new File(".");
			Logger.getLogger(this.getClass().getName()).log(Level.WARNING,translator.internationalize(Locale.ENGLISH,"The target top level directory for the help system is missing. Using '.' instead."));
		}
		if(!source.isDirectory()){
			source = new File(".");
			Logger.getLogger(this.getClass().getName()).log(Level.WARNING,translator.internationalize(Locale.ENGLISH,"The target top level directory for the help system is not a directory. Using '.' instead."));
		}

		if(JavaStringInternationalisation.nativeLocale == Locale.getDefault()){
			translator.autoTranslate=false;
		}else{
			File[] child = source.listFiles();
			translator.autoTranslate = true;
			for(File s: child){
				if(s.getName()==Locale.getDefault().getDisplayLanguage()){
					source = s;
					translator.autoTranslate=false;
				}
			}
		}
		FileNode base = FileNode.determineType(source);
		setModel(new DefaultTreeModel(base));
		if ((source != null) && source.exists() && source.isDirectory()) {
			base.traverseFileSystem( source, 0, translator);
		}
		getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
	}


	public void addTreeSelectionListener(TreeSelectionListener listener){
		getSelectionModel().addTreeSelectionListener(listener);		
	}
}
