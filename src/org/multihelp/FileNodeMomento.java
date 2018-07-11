/**
 * Created by Daniel McEnnis on 7/11/2018
 * <p>
 * Copyright Daniel McEnnis 2015
 */

package org.multihelp;

import org.multihelp.file.FileNode;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Default Description Google Interview Project
 */
public class FileNodeMomento {

    private Vector<TreePath> memory = new Vector<>();

    public selectionListener fileNodeListener = new selectionListener();

    public forwardAction forward = new forwardAction();

    public backAction back = new backAction();

    private JButton backButton = null;

    private JButton forwardButton = null;

    private int position = 0;

    private FileTreeReader reader;

    /**
     * Default constructor for FileNodeMomento
     */
    public FileNodeMomento(FileTreeReader r) {
        reader = r;
        reader.addTreeSelectionListener(fileNodeListener);
    }

    public void add(TreePath f) {
        for (int i = 0; i < position; ++i) {
            memory.remove(0);
        }
        memory.add(0, f);
        position = 0;
        if(forwardButton != null){
            forwardButton.setEnabled(false);
        }
    }

    public TreePath back() {
        if (memory.size() > 0) {
            if ((position >= 0) || (position < memory.size())) {
                if(position == memory.size()-1){
                    backButton.setEnabled(false);
                }
                if(!forwardButton.isEnabled()){
                    forwardButton.setEnabled(true);
                }
                return memory.get(position++);
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, (new JavaStringInternationalisation()).internationalize("Internal Error: Position " + position + " is not between 0 and memory length " + memory.size()));
                return null;
            }
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, (new JavaStringInternationalisation()).internationalize("Internal Error: most recent FileNode requested of an empty memory"));
            return null;
        }
    }

    public TreePath forward() {
        if (memory.size() > 0) {
            if ((position > 0) || (position < memory.size())) {
                if(position==1){
                    forwardButton.setEnabled(false);
                }
                if(position == memory.size()-1){
                    backButton.setEnabled(true);
                }
                return memory.get(--position);
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, (new JavaStringInternationalisation()).internationalize("Internal Error: Position " + position + " is not between 0 and memory length " + memory.size()));
                return null;
            }
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, (new JavaStringInternationalisation()).internationalize("Internal Error: most recent FileNode requested of an empty memory"));
            return null;
        }
    }

    public class selectionListener implements TreeSelectionListener {
        @Override
        public void valueChanged(TreeSelectionEvent e) {
            add(e.getPath());
        }
    }

    public class forwardAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(forwardButton == null){
                forwardButton = (JButton)e.getSource();
            }
            reader.setSelectionPath(forward());
        }
    }

    public class backAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(backButton == null){
                backButton = (JButton)e.getSource();
            }
            reader.setSelectionPath(back());
        }
    }

}
