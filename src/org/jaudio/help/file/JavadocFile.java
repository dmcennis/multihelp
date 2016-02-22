/**
 * JavadocFile
 *
 * Created Sep 15, 2010-2:45:07 PM by Daniel McEnnis
 * Distributed under the BSD license.  See www.fsf.org for license details.
 */
package org.jaudio.help.file;

import java.io.File;
import java.io.FileFilter;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;

import org.jaudio.help.HelpViewer;

/**
 * JavadocFile
 *
 * @author Daniel McEnnis
 */
public class JavadocFile extends FileNode {

	File fileLocation;
	
	Filter filter = new Filter();
	
	boolean isDirectory;
	
	public JavadocFile(File file){
		super(file);
		fileLocation = file;
		if(file.getName().endsWith(".html")){
			isDirectory=false;
		}else{
			isDirectory=true;
		}
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
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getStackTrace(), "ERROR",
					JOptionPane.ERROR_MESSAGE);
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
			return bundle.getString("html.body.error.empty.directory.in.the.help.system.body.html1");
		}else{
			ResourceBundle bundle =ResourceBundle.getBundle("Translations");
			return bundle.getString("html.body.internal.error.loading.non.existant.file.body.html1");
		}
	}

	public class Filter implements FileFilter{

		public Filter(){
		}
		/* (non-Javadoc)
		 * @see java.io.FileFilter#accept(java.io.File)
		 */
		public boolean accept(File pathname) {
			if(isDirectory){
				return true;
			}else if(pathname.getName().endsWith(".html")||pathname.getName().endsWith(".htm")){
				if(pathname.getName().contentEquals("index.html")){
					return false;
				} else if(pathname.getName().contentEquals("index.html")){
					return false;
				} else if(pathname.getName().contentEquals("allclasses-frame.html")){
					return false;
				} else if(pathname.getName().contentEquals("allclasses-noframe.html")){
					return false;
				} else if(pathname.getName().contentEquals("constant-values.html")){
					return false;
				} else if(pathname.getName().contentEquals("deprecated-list.html")){
					return false;
				} else if(pathname.getName().contentEquals("index-all.html")){
					return false;
				} else if(pathname.getName().contentEquals("overview-frame.html")){
					return false;
				} else if(pathname.getName().contentEquals("overview-summary.html")){
					return false;
				} else if(pathname.getName().contentEquals("overview-tree.html")){
					return false;
				} else if(pathname.getName().contentEquals("package-frame.html")){
					return false;
				} else if(pathname.getName().contentEquals("package-list.html")){
					return false;
				} else if(pathname.getName().contentEquals("package-summary.html")){
					return false;
				} else if(pathname.getName().contentEquals("package-tree.html")){
					return false;
				} else if(pathname.getName().contentEquals("serialized-form.html")){
					return false;
				}
				return true;
			}
			return false;
		}
		
	}
	
	public void traverseFileSystem(File root, int depth) {
		if (depth < 512) {
			File[] children = root.listFiles();
			if ((children != null) && (children.length != 0)) {
				for (int i = 0; i < children.length; ++i) {
					if(filter.accept(children[i])){
						JavadocFile childNode = new JavadocFile(
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
