/*
 * @(#)OSXTigerFileSystemView.java 
 *
 * Copyright (c) 2006-2010 Werner Randelshofer
 * Hausmatt 10, Immensee, CH-6405, Switzerland.
 * All rights reserved.
 *
 * The copyright of this software is owned by Werner Randelshofer. 
 * You may not use, copy or modify this software, except in  
 * accordance with the license agreement you entered into with  
 * Werner Randelshofer. For details see accompanying license terms. 
 */

package ch.randelshofer.quaqua.tiger.filechooser;

import ch.randelshofer.quaqua.filechooser.*;
import java.io.*;
import java.util.*;
/**
 * A file system view for Mac OS X 10.4 (Tiger).
 * <p>
 * Note: This file system view only works on top of Apple's Macintosh Runtime for Java.
 *
 * @author  Werner Randelshofer
 * @version $Id$
 */
public class OSXTigerFileSystemView extends QuaquaFileSystemView {
    private static final File volumesFolder = new File("/Volumes");
    private static final File networkFolder = new File("/Network");
    private static final File computer = new File("/");
    private static File systemVolume;
    
    
    public OSXTigerFileSystemView() {
    }
    
    public File getSystemVolume() {
        if (systemVolume == null) {
            File[] volumes = volumesFolder.listFiles();
            File sys = null;
            for (int i=0; i < volumes.length; i++) {
                try {
                    if (volumes[i].getCanonicalFile().equals(computer)) {
                        sys = volumes[i];
                        break;
                    }
                } catch (IOException e) {
                    // We get here because we can't determine the
                    // canonical path for the volume. We suppress this
                    // exception, in the hope that it did not happen for
                    // the system volume. If it happened for the system
                    // volume, there is fallback code in method
                    // getSystemVolume() that handles this problem.
                    
                    // System.err.println(
                    //   "Unable to canonicalize volume "+volumes[i]
                    // );
                    // e.printStackTrace();
                } catch (SecurityException e) {
                    // We get here because we are not allowed to read the
                    // file. We suppress this exception, in the hope that
                    // it did not happen for the system volume. If it
                    // happened for the system volume, there is fallback
                    // code in method getSystemVolume() that handles this
                    // problem.
                }
            }
            // If we couldn't determine the system volume, we use the
            // root folder instead.
            systemVolume = (sys == null) ? computer : sys;
        }
        return systemVolume;
    }
    public File getComputer() {
        return computer;
    }
    
    
    /**
     * This is a list of file names that are treated as invisible by the AWT
     * FileDialog when they are at the top directory level of a volume.
     * The file names are wrongly treated as visible by
     * Apple's implementation FileSystemView, so we use this HashSet here, to
     * hide them 'manually'.
     */
    private final static HashSet hiddenTopLevelNames = new HashSet();
    static {
        String[] names = {
            "AppleShare PDS",
            "automount",
            "bin",
            "Cleanup At Startup",
            "cores",
            "Desktop DB",
            "Desktop DF",
            "dev",
            "etc",
            "home",
            "mach",
            "mach_kernel",
            "mach_kernel.ctfsys",
            "mach.sym",
            "net",
            "opt",
            "private",
            "sbin",
            "Temporary Items",
            "TheVolumeSettingsFolder",
            "TheFindByContentFolder",
            "tmp",
            "Trash",
            "usr",
            "var",
            "Volumes",
            "\u0003\u0002\u0001Move&Rename",
        };
        
        hiddenTopLevelNames.addAll(Arrays.asList(names));
    };
    
    
    /**
     * Returns the parent directory of dir.
     * This method returns the system volume instead of the computer folder ("/").
     */
    @Override
    public File getParentDirectory(File dir) {
        File parent = (isRoot(dir)) ? null : super.getParentDirectory(dir);
        if (parent != null && parent.equals(computer)) {
            parent = getSystemVolume();
        }
        return parent;
    }
    
    /**
     * Returns all root partitians on this system.
     * This method returns the contents of the volumes folder.
     * The computer folder ("/") is considered as hidden and not
     * returned by this method.
     */
    @Override
    public File[] getRoots() {
        File[] fileArray;
        ArrayList roots = new ArrayList();
        /*
        fileArray = sysView.getRoots();
        if (fileArray != null) {
            roots.addAll(Arrays.asList(fileArray));
        }
         */
        //roots.add(computer);
        fileArray = volumesFolder.listFiles();
        if (fileArray != null) {
            roots.addAll(Arrays.asList(fileArray));
            //roots.remove(getSystemVolume());
        }
        roots.add(networkFolder);
        
        return (File[]) roots.toArray(new File[roots.size()]);
        
    }
    
    /**
     * Returns whether a file is hidden or not.
     */
    @Override
    public boolean isHiddenFile(File f) {
        if (f.isHidden()) {
            return true;
        } else {
            String name = f.getName();
            if (name.length() == 0) {
                return false;
            } else if (name.charAt(name.length() - 1) == (char) 0x0d) {
                // File names ending with 0x0d are considered as
                // hidden
                return true;
            } else if (hiddenTopLevelNames.contains(name)
            && (f.getParent() == null || isRoot(f.getParentFile()))) {
                return true;
            } else {
                return false;
            }
        }
    }
    
    
    /**
     * Determines if the given file is a root partition or drive.
     */
    @Override
    public boolean isRoot(File aFile) {
        return aFile.equals(computer)
        || aFile.equals(networkFolder)
        || aFile.getParentFile() != null && aFile.getParentFile().equals(volumesFolder);
    }
    
    /**
     * On Windows, a file can appear in multiple folders, other than its
     * parent directory in the filesystem. Folder could for example be the
     * "Desktop" folder which is not the same as file.getParentFile().
     *
     * @param folder a <code>File</code> object repesenting a directory or special folder
     * @param file a <code>File</code> object
     * @return <code>true</code> if <code>folder</code> is a directory or special folder and contains <code>file</code>.
     */
    @Override
    public boolean isParent(File folder, File file) {
        if (folder == null || file == null) {
            return false;
        } else {
            return folder.equals(file.getParentFile());
        }
    }
    
    /**
     *
     * @param parent a <code>File</code> object repesenting a directory or special folder
     * @param fileName a name of a file or folder which exists in <code>parent</code>
     * @return a File object. This is normally constructed with <code>new
     * File(parent, fileName)</code> except when parent and child are both
     * special folders, in which case the <code>File</code> is a wrapper containing
     * a <code>ShellFolder</code> object.
     */
    @Override
    public File getChild(File parent, String fileName) {
        return new File(parent, fileName);
    }
    
    
    /**
     * Is dir the root of a tree in the file system, such as a drive
     * or partition. Example: Returns true for "C:\" on Windows 98.
     *
     * @param dir a <code>File</code> object representing a directory
     * @return <code>true</code> if <code>f</code> is a root of a filesystem
     * @see #isRoot
     */
    @Override
    public boolean isFileSystemRoot(File dir) {
        File parentFile = dir.getParentFile();
        return parentFile == null || parentFile.equals(volumesFolder);
    }
    
    // Providing default implementations for the remaining methods
    // because most OS file systems will likely be able to use this
    // code. If a given OS can't, override these methods in its
    // implementation.
    
    @Override
    public File getHomeDirectory() {
        return createFileObject(System.getProperty("user.home"));
    }
    
    /**
     * Return the user's default starting directory for the file chooser.
     *
     * @return a <code>File</code> object representing the default
     *         starting folder
     */
    public File getDefaultDirectory() {
        return getHomeDirectory();
    }
}

