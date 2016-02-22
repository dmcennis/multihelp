/**
 * DefaultMagicCheck
 *
 * Created Sep 15, 2010-4:50:10 PM by Daniel McEnnis
 * Distributed under the BSD license.  See www.fsf.org for license details.
 */
package org.jaudio.help.file;

import java.io.File;

/**
 * DefaultMagicCheck
 *
 * @author Daniel McEnnis
 */
public class DefaultMagicCheck implements MagicCheck {

	/* (non-Javadoc)
	 * @see org.jaudio.help.file.MagicCheck#determineType(java.io.File)
	 */
	public FileNode determineType(File root) {
		if((root != null) &&(root.listFiles().length>0) ){
			File[] children = root.listFiles();
			boolean html = false;
			for(int i=0;i<children.length;++i){
				if(children[i].getName().compareTo("allclasses-frame.html")==0){
					return new JavadocFile(root);
				}
				if(children[i].getName().endsWith(".java")){
					return new SourceFile(root);
				}
				if(root.getName().endsWith("*.html")||root.getName().endsWith("*.htm")){
					return new HTMLFile(root);
				}
			}
		}
		if(root.getName().endsWith("*.java")){
			return new SourceFile(root);
		}
		if(root.getName().endsWith("*.html")||root.getName().endsWith("*.htm")){
			return new HTMLFile(root);
		}
		if(root.getName().endsWith("*.exe")||root.getName().endsWith("*.dll")
				||root.getName().endsWith("*.zip")
				||root.getName().endsWith("*.jar")
				||root.getName().endsWith("*.tar")
				||root.getName().endsWith("*.bz2")
				||root.getName().endsWith("*.gz")
				||root.getName().endsWith("*.tgz")){
			return new EmptyFile(root);
		}
		return new DefaultFile(root);
	}

}
