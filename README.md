<img src="MultiHelp128.png" height="128" width="128" alt="MultiHelp Logo">
MultiHelp
=========

MultiHelp is a Java-based help menu emulating the original ubiqutous in-application help system. Its primary focus is on making it as easy as possible for a developer to deploy documentation. As a result, the library takes everything in the 'help' application sub-directory and processes it, self-determining the appropriate documentation system by a variant of the Unix 'magic' file determination method. Unlike other systems, no recompiling to a new help format is necessary - important for automatically generated documentation such as source code and Javadoc.

Each documentation type has control of its own directory parsing and control of its presentation in both the navigation tree and the HTML display panel (JEditor). The types are controlled by a registry system. Adding a new type requires a custom class and a new magic file entry to uniquely identify the new type without a configuration entry.

Localization (version 0.2 and later) is provided by separate documentation directories. Locale names are to be used as defined in the System.getLocale() method.

##Supported Documentation Types

+ ###HTML:	
Loads only HTML pages, but uses the Magic file checker on each subdirectory to determine its file type. The navigation title is the file name minus the .html extension

+ ###Javadoc:	
Loads all Javadoc html files normally navigable from the base index.html page. It assumes all subdirectories are also Javadoc directories.

+ ###Source:	
Pretty prints source code, html, and xml files. It uses the Magic check on sub-directories to determine what their sub-type is.

+ ###Default:	
Checks all files and sub-directories using the Magic check to determine their type. Only recognised files are included.

##Adding a New Documentation Type
Subclass FileNode and implement the following methods:

+ ###Constructor: 
builds the object with the given URL reference.

+ ###Traverse Directory: 
Either load the given file or descend into the directory, loading a JTree with the appropriate FileNode objects.

+ ###Display: 
Display the FileNode in the navigation window. If null, it uses the defaults. Otherwise, it is a DefaultTreeCellRenderer.

+ ###Load Page: 
Load a page of documentation into the given HTML browser.
Add a new 'Magic' entry to FileNode
