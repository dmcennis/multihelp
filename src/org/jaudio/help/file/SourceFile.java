/**
 * SourceFile
 *
 * Created Sep 15, 2010-3:09:19 PM by Daniel McEnnis
 * Distributed under the BSD license.  See www.fsf.org for license details.
 */
package org.jaudio.help.file;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.tree.DefaultMutableTreeNode;

import org.jaudio.help.HelpViewer;

/**
 * SourceFile
 *
 * @author Daniel McEnnis
 */
public class SourceFile extends FileNode {
	File base;
	
	boolean isDirectory;
	
	Filter filter = new Filter();
	
	public SourceFile(File root){
		super(root);
		base = root;
		isDirectory = base.isDirectory();
	}
	/* (non-Javadoc)
	 * @see org.jaudio.help.file.FileNode#setPage(org.jaudio.help.HelpViewer)
	 */
	@Override
	public void setPage(HelpViewer viewer) {
		if(isDirectory){
			String header = "<html><body><ul>";
			String footer = "</ul></body></html>";
			StringBuffer content = new StringBuffer();
			File[] children = base.listFiles();
			if((children != null)&&(children.length>0)){
				for(int i=0;i<children.length;++i){
					content.append("<li>"+children[i].getName()+"</li>");
				}
			}
			viewer.set(header+content.toString()+footer);
		}else{
		FileReader stream;
		try {
			stream = new FileReader(base);
		char[] buffer = new char[10240];
		StringBuffer fileBuffer = new StringBuffer();
		int read = 0;
		while((read = stream.read(buffer)) >0){
			fileBuffer.append(buffer, 0, read);
		}
		String file = fileBuffer.toString();

		Pattern  lt = Pattern.compile("<");
		Matcher match = lt.matcher(file);
		file = match.replaceAll("&lt;");

		Pattern  gt = Pattern.compile(">");
		match = gt.matcher(file);
		file = match.replaceAll("&gt;");

		Pattern  amp = Pattern.compile("&");
		match = amp.matcher(file);
		file = match.replaceAll("&amp;");	
		viewer.set("<html><body><pre>"+file+"</pre></body></html>");
		} catch (FileNotFoundException e) {
			ResourceBundle bundle =ResourceBundle.getBundle("Translations");
			viewer.set(String.format(bundle.getString("html.body.error.help.file.s.not.found.body.html1"),base.getAbsolutePath()));
			e.printStackTrace();
		} catch (IOException e) {
			ResourceBundle bundle =ResourceBundle.getBundle("Translations");
			viewer.set(String.format(bundle.getString("html.body.error.help.file.s.had.an.io.error.body.html1"),base.getAbsolutePath()));
			e.printStackTrace();
		}
		}
	}
	
	public class Filter implements FileFilter{

		/* (non-Javadoc)
		 * @see java.io.FileFilter#accept(java.io.File)
		 */
		public boolean accept(File pathname) {
			if(pathname.isDirectory()){
				return true;
			}else if(pathname.getName().endsWith(".java")){
				return true;
			}else if(pathname.getName().endsWith(".xml")){
				return true;
			}
			return false;
		}
		
	}

	/* (non-Javadoc)
	 * @see org.jaudio.help.file.FileNode#traverseFileSystem(java.io.File, int)
	 */
	@Override
	public void traverseFileSystem(File root, int depth) {
		if (depth < 512) {
			File[] children = root.listFiles();
			if ((children != null) && (children.length != 0)) {
				for (int i = 0; i < children.length; ++i) {
					if(filter.accept(children[i])){
						SourceFile childNode = new SourceFile(
								children[i]);
						this.add(childNode);
						childNode.setParent(this);
						System.out.println(childNode.toString());
						childNode.traverseFileSystem(children[i], depth+1);
					}
				}
			}
		}
	}

}
