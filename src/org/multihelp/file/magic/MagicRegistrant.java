/**
 * Created by Daniel McEnnis on 7/10/2018
 * <p>
 * Copyright Daniel McEnnis 2015
 */

package org.multihelp.file.magic;

import org.multihelp.file.MagicCheck;

/**
 * Default Description Interface MagicRegistrant
 */
public interface MagicRegistrant extends MagicCheck {
    public void registerMagicCheck(LegacyMagicRegistrant reigistry);
}
