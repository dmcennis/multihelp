/**
 * HTMLFile
 *
 * Created Sep 15, 2010-3:13:22 PM by Daniel McEnnis
 * Distributed under the BSD license.  See www.fsf.org for license details.
 */
package org.jaudio.help.file;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;

import org.jaudio.help.HelpViewer;

/**
 * HTMLFile
 *
 * @author Daniel McEnnis
 */
public class HTMLFile extends FileNode {

	private File fileLocation;
	
	private boolean isDirectory;
	
	private Filter filter = new Filter();
	
	public HTMLFile(File file){
		super(file);
		fileLocation = file;
	}
	
	/* (non-Javadoc)
	 * @see org.jaudio.help.file.FileNode#setPage(org.jaudio.help.HelpViewer)
	 */
	@Override
	public void setPage(HelpViewer viewer) {
		try {
			if(isDirectory){
				File loc = new File(fileLocation.getCanonicalPath()+File.pathSeparator+"index.html");
				if(loc.exists()){
					viewer.set(loc.toURI().toURL());
				}else{
					viewer.set(generateDefaultIndex());
				}
			}else{
				viewer.set(fileLocation.toURI().toURL());
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getStackTrace(), "ERROR",
					JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getStackTrace(), "ERROR",
					JOptionPane.ERROR_MESSAGE);
		}
		
	}

	public class Filter implements FileFilter{

		/* (non-Javadoc)
		 * @see java.io.FileFilter#accept(java.io.File)
		 */
		public boolean accept(File pathname) {
			if(pathname.getName().endsWith("*.html")||pathname.getName().endsWith("*.htm")){
				if(pathname.getName().compareTo("index.html")==0){
					return false;
				}else{
					return true;
				}
			}
			return false;
		}
	}
	
	
	protected String generateDefaultIndex(){
		if(fileLocation.isDirectory()&&(fileLocation.listFiles().length>0)){
			File[] list = fileLocation.listFiles();
			StringBuffer ret = new StringBuffer("<html><body><ul>");
			for(int i=0;i<list.length;++i){
				ret.append("<li><a href=\""+list[i].getName()+"\">"+list[i].getName()+"</a></li>");
			}
			ret.append("<ul></body></html>");
			return ret.toString();
		}else if(fileLocation.isDirectory()){
			ResourceBundle bundle =ResourceBundle.getBundle("Translations");
			return bundle.getString("html.body.error.empty.directory.in.the.help.system.body.html");
		}else{
			ResourceBundle bundle =ResourceBundle.getBundle("Translations");
			return bundle.getString("html.body.internal.error.loading.non.existant.file.body.html");
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
						FileNode childNode = FileNode.determineType(children[i]);
						this.add(childNode);
						childNode.setParent(this);
						System.out.println(children[i].getAbsolutePath());
						childNode.traverseFileSystem(children[i], depth+1);
					}
				}
			}
		}
	}
}
