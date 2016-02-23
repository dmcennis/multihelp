/**
 * MagicBase
 *
 * Created Sep 15, 2010-4:37:07 PM by Daniel McEnnis
 * Distributed under the BSD license.  See www.fsf.org for license details.
 */
package org.multihelp.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.multihelp.HelpViewer;

/**
 * MagicBase
 *
 * @author Daniel McEnnis
 */
public class DefaultFile extends FileNode {
	File base;
	public DefaultFile(File root){
		super(root);
		base = root;
	}
	
	/* (non-Javadoc)
	 * @see org.jaudio.org.multihelp.file.FileNode#setPage(org.jaudio.org.multihelp.HelpViewer)
	 */
	@Override
	public void setPage(HelpViewer viewer) {
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
			viewer.set(String.format(bundle.getString("html.body.error.help.file.s.not.found.body.html"),base.getAbsolutePath()));
			e.printStackTrace();
		} catch (IOException e) {
			ResourceBundle bundle =ResourceBundle.getBundle("Translations");
			viewer.set(String.format(bundle.getString("html.body.error.help.file.s.had.an.io.error.body.html"),base.getAbsolutePath()));
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see org.jaudio.org.multihelp.file.FileNode#traverseFileSystem(java.io.File, int)
	 */
	@Override
	public void traverseFileSystem(File root, int depth) {
		// TODO Auto-generated method stub

	}

}
