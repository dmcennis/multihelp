/**
 * Created by Daniel McEnnis on 7/10/2018
 * <p>
 * Copyright Daniel McEnnis 2015
 */

package org.multihelp.file.magic;

import org.multihelp.file.FileNode;
import org.multihelp.file.HTMLFile;
import org.multihelp.file.MagicCheck;

import java.io.File;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Default Description Google Interview Project
 */
public class LegacyMagicRegistrant implements MagicCheck {

    private LinkedList<MagicCheck> registrants = new LinkedList<>();

    /**
     * Default constructor for LegacyMagicRegistrant
     */
    public LegacyMagicRegistrant() {

    }

    @Override
    public FileNode determineType(File root) {
        for(MagicCheck item: registrants){
            FileNode type = item.determineType(root);
            if(type != null){
                return type;
            }
        }
        return new HTMLFile(root);
    }

    public void register(MagicCheck item) {
        if (!registrants.contains(item)) {
            registrants.add(item);
        }
    }

    public void setPlace(MagicCheck item, int place) {
        if (!registrants.contains(item)) {
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "The check " + item.getClass().getName() + " is not yet registered. Ignoring the ordering request.");
        } else if ((place < 0)) {
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Rank " + place + " is not between 0 and the number of magic check items: " + registrants.size() + ". Ignoring the ordering request.");
        } else {
            registrants.remove(item);
            registrants.add(place, item);
        }
    }

    public void reverseOrder(MagicCheck item1, MagicCheck item2) {
        if ((!registrants.contains(item1)) && (!registrants.contains(item2))) {
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "MagicCheck " + item1.getClass().getName() + " and " + item2.getClass().getName() + " are not yet registered. Request ignored");
        } else if (!registrants.contains(item1)) {
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "MagicCheck " + item1.getClass().getName() + " and " + item2.getClass().getName() + " are not yet registered. Request ignored");
        } else if (!registrants.contains(item2)) {
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "MagicCheck " + item1.getClass().getName() + " and " + item2.getClass().getName() + " are not yet registered. Request ignored");
        } else {
            int left = 0;
            int right = 0;
            int index = 0;
            for (MagicCheck item : registrants) {
                if (item == item1) {
                    left = index;
                }
                if (item == item2) {
                    right = index;
                }
                index++;
            }
            registrants.set(left, item2);
            registrants.set(right, item1);
        }
    }
}
