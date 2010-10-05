/**
 * MagicCheck
 *
 * Created Sep 15, 2010-4:45:31 PM by Daniel McEnnis
 * Distributed under the BSD license.  See www.fsf.org for license details.
 */
package org.jaudio.help.file;

import java.io.File;

/**
 * MagicCheck
 *
 * @author Daniel McEnnis
 */
public interface MagicCheck {
	public FileNode determineType(File root);
}
