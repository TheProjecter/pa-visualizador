/*
 * @(#)QuaquaPantherFileChooserLAF.java
 *
 * Copyright (c) 2004-2010 Werner Randelshofer
 * Hausmatt 10, Immensee, CH-6405, Switzerland.
 * All rights reserved.
 *
 * The copyright of this software is owned by Werner Randelshofer. 
 * You may not use, copy or modify this software, except in  
 * accordance with the license agreement you entered into with  
 * Werner Randelshofer. For details see accompanying license terms. 
 */
package ch.randelshofer.quaqua.subset;

import ch.randelshofer.quaqua.*;
import ch.randelshofer.quaqua.util.*;
import javax.swing.*;
import javax.swing.plaf.*;
import java.awt.*;
import java.awt.image.*;
import java.util.*;
import java.net.*;
import java.security.*;

/**
 * The QuaquaPantherFileChooserLAF is an extension for Apple's Aqua Look and Feel
 * for Java 1.4 on Mac OS X 10.3 (Panther). 
 * <p>
 * The Quaqua Look and Feel can not be used on other platforms than Mac OS X.
 * <p>
 * <b>Important:</b> This class is a cut down version of the
 * QuaquaPantherLookAndFeel. It is for use in environments, where the size of
 * the whole Quaqua look and feel would be too excessive.
 * <p>
 * <h3>Fixes and Enhancements</h3>
 * This class provides the following bug fixes end enhancements to Apple's Aqua
 * Look and Feel:
 *
 * <h4>ComboBoxUI</h4>
 * <ul>
 * <li>Combo boxes use the font "Lucida Grande 13" instead of "Lucida Grande 14".</li>
 * </ul>
 *
 * <h4>FileChooserUI</h4>
 * <ul>
 * <li>FileChooserUI uses a column view similar to the native file dialog of
 * Mac OS X 10.3 Panther.</li>
 * <li>The look and feel provides an image showing a house for
 * <code>FileChooser.homeFolderIcon</code> and an icon showing an iMac for
 * <code>FileView.computerIcon</code> instead of an icon showing a computer
 * desktop for both properties. The FileChooserUI with column view does not use
 * these images, but your application might.</li>
 * <li>The FileChooserUI resolves aliases to files and folders. </li>
 * </ul>
 *
 * <h4>TableUI</h4>
 * <ul>
 * <li>Table headers use the font "Lucida Grande 11" instead of "Lucida Grande 13".
 * </li>
 * </ul>
 *
 * <h3>Usage</h3>
 * Please use the <code>QuaquaManager</code> to activate this look and feel in
 * your application. Or use the generic <code>QuaquaLookAndFeel</code>. Both
 * are designed to automatically detect the appropriate Quaqua Look and Feel
 * implementation for current Java VM.
 *
 * @see QuaquaManager
 * @see QuaquaLookAndFeel
 *
 * @author Werner Randelshofer
 * @version  $Id: QuaquaPantherFileChooserLAF.java 234 2010-06-20 14:21:35Z wrandelshofer $
 */
public class QuaquaPantherFileChooserLAF extends LookAndFeelProxy {
    protected final static String commonDir = "/ch/randelshofer/quaqua/images/";
    protected final static String jaguarDir = "/ch/randelshofer/quaqua/jaguar/images/";
    protected final static String pantherDir = "/ch/randelshofer/quaqua/panther/images/";
    /**
     * Holds a bug fixed version of the UIDefaults provided by the target
     * LookAndFeel.
     * @see #initialize
     * @see #getDefaults
     */
    private UIDefaults myDefaults;
    /**
     * The small system font (Lucida Grande Regular 11 pt) is used for
     * informative text in alerts. It is also the default font for column
     * headings in lists, for help tags, and for small controls. You can also
     * use it to provide additional information about settings in various
     * windows, such as the QuickTime pane in System Preferences.
     */
    protected static final FontUIResource SMALL_SYSTEM_FONT =
    new FontUIResource("Lucida Grande", Font.PLAIN, 11);
    
    /**
     * Creates a new instance.
     */
    public QuaquaPantherFileChooserLAF() {
        String targetClassName = "apple.laf.AquaLookAndFeel";
        try {
            setTarget((LookAndFeel) Class.forName(targetClassName).newInstance());
        } catch (Exception e) {
            throw new InternalError(
            "Unable to instanciate target Look and Feel \""
            +targetClassName
            +"\". "+e.getMessage()
            );
        }
    }
    
    /**
     * Return a one line description of this look and feel implementation,
     * e.g. "The CDE/Motif Look and Feel".   This string is intended for
     * the user, e.g. in the title of a window or in a ToolTip message.
     */
    public String getDescription() {
        return "The Quaqua Panther FileChooser Look and Feel";
    }
    
    /**
     * Return a short string that identifies this look and feel, e.g.
     * "CDE/Motif".  This string should be appropriate for a menu item.
     * Distinct look and feels should have different names, e.g.
     * a subclass of MotifLookAndFeel that changes the way a few components
     * are rendered should be called "CDE/Motif My Way"; something
     * that would be useful to a user trying to select a L&F from a list
     * of names.
     */
    public String getName() {
        return "Quaqua FileChooser-only LAF";
    }
    
    /**
     * UIManager.setLookAndFeel calls this method before the first
     * call (and typically the only call) to getDefaults().  Subclasses
     * should do any one-time setup they need here, rather than
     * in a static initializer, because look and feel class uiDefaults
     * may be loaded just to discover that isSupportedLookAndFeel()
     * returns false.
     *
     * @see #uninitialize
     * @see UIManager#setLookAndFeel
     */
    public void initialize() {
        // Note: We initialize in a privileged block, because if we are
        //       installed as a Standard Extension in the Java VM, we
        //       are allowed to access our resources (i.e. images),
        //       even then, when the calling application is not allowed
        //       to do so.
        AccessController.doPrivileged(new PrivilegedAction() {
            public Object run() {
                target.initialize();
                myDefaults = target.getDefaults();
                initResourceBundle(myDefaults);
                initClassDefaults(myDefaults);
                initGeneralDefaults(myDefaults);
                initComponentDefaults(myDefaults);
                return null;
            }
        });
    }
    /**
     * This method is called once by UIManager.setLookAndFeel to create
     * the look and feel specific defaults table.  Other applications,
     * for example an application builder, may also call this method.
     *
     * @see #initialize
     * @see #uninitialize
     * @see UIManager#setLookAndFeel
     */
    public UIDefaults getDefaults() {
        return myDefaults;
    }
    protected void initResourceBundle(UIDefaults table) {
        // The following line of code does not work, when Quaqua has been loaded with
        // a custom class loader. That's why, we have to inject the labels
        // by ourselves:
        //table.addResourceBundle( "ch.randelshofer.quaqua.Labels" );
        ResourceBundle bundle = ResourceBundle.getBundle(
                "ch.randelshofer.quaqua.Labels",
                Locale.getDefault(),
                getClass().getClassLoader()
                );
        for (Enumeration i = bundle.getKeys(); i.hasMoreElements(); ) {
            String key = (String) i.nextElement();
            table.put(key, bundle.getObject(key));
        }
    }
    /**
     * Initialize the uiClassID to BasicComponentUI mapping.
     * The JComponent classes define their own uiClassID constants
     * (see AbstractComponent.getUIClassID).  This table must
     * map those constants to a BasicComponentUI class of the
     * appropriate type.
     *
     * @see #getDefaults
     */
    protected void initClassDefaults(UIDefaults table) {
        String basicPrefix = "javax.swing.plaf.basic.Basic";
        String quaquaPrefix = "ch.randelshofer.quaqua.Quaqua";
        String quaquaPantherPrefix = "ch.randelshofer.quaqua.panther.QuaquaPanther";
        
        // NOTE: Uncomment parts of the code below, to override additional
        // UI classes of the target look and feel.
        Object[] uiDefaults = {
            "BrowserUI", quaquaPrefix + "BrowserUI",
            "FileChooserUI", quaquaPantherPrefix + "FileChooserUI",
        };
        table.putDefaults(uiDefaults);
    }
    protected void initGeneralDefaults(UIDefaults table) {
        Object[] uiDefaults;
        uiDefaults = new Object[]{
            "ClassLoader", getClass().getClassLoader(),
        };
        table.putDefaults(uiDefaults);
    }
    protected void initComponentDefaults(UIDefaults table) {
        Font smallSystemFont = SMALL_SYSTEM_FONT;
        Color grayedFocusCellBorderColor = (Color) table.get("listHighlight");
        Integer zero = new Integer(0);
        Integer one = new Integer(1);
        Integer two = new Integer(2);
        Integer three = new Integer(3);
        Integer four = new Integer(4);
        Integer five = new Integer(5);
        Integer six = new Integer(6);
        
        Object[] uiDefaults = {
            "Browser.expandedIcon", new UIDefaults.ProxyLazyValue("ch.randelshofer.quaqua.QuaquaIconFactory", "createIcon",
            new Object[]{jaguarDir + "Browser.disclosureIcons.png", six, Boolean.TRUE, zero}),
            "Browser.expandingIcon", new UIDefaults.ProxyLazyValue("ch.randelshofer.quaqua.QuaquaIconFactory", "createIcon",
            new Object[]{jaguarDir + "Browser.disclosureIcons.png", six, Boolean.TRUE, one}),
            "Browser.focusedSelectedExpandedIcon", new UIDefaults.ProxyLazyValue("ch.randelshofer.quaqua.QuaquaIconFactory", "createIcon",
            new Object[]{jaguarDir + "Browser.disclosureIcons.png", six, Boolean.TRUE, two}),
            "Browser.focusedSelectedExpandingIcon", new UIDefaults.ProxyLazyValue("ch.randelshofer.quaqua.QuaquaIconFactory", "createIcon",
            new Object[]{jaguarDir + "Browser.disclosureIcons.png", six, Boolean.TRUE, three}),
            "Browser.selectedExpandedIcon", new UIDefaults.ProxyLazyValue("ch.randelshofer.quaqua.QuaquaIconFactory", "createIcon",
            new Object[]{jaguarDir + "Browser.disclosureIcons.png", six, Boolean.TRUE, four}),
            "Browser.selectedExpandingIcon", new UIDefaults.ProxyLazyValue("ch.randelshofer.quaqua.QuaquaIconFactory", "createIcon",
            new Object[]{jaguarDir + "Browser.disclosureIcons.png", six, Boolean.TRUE, five}),
            //
            "Browser.selectionBackground", new ColorUIResource(56,117,215),
            "Browser.selectionForeground", new ColorUIResource(255,255,255),
            "Browser.inactiveSelectionBackground", new ColorUIResource(208,208,208),
            "Browser.inactiveSelectionForeground", new ColorUIResource(0,0,0),
            "Browser.sizeHandleIcon", new UIDefaults.ProxyLazyValue(
            "ch.randelshofer.quaqua.QuaquaIconFactory", "createIcon",
            new Object[]{commonDir + "Browser.sizeHandleIcon.png", one, Boolean.TRUE, one}),

            "FileChooser.homeFolderIcon", LookAndFeel.makeIcon(getClass(), commonDir+"FileChooser.homeFolderIcon.png"),
            //
            "FileView.computerIcon", LookAndFeel.makeIcon(getClass(), commonDir+"FileView.computerIcon.png"),
            //
            "FileChooser.previewLabelForeground", new ColorUIResource(0x000000),
            "FileChooser.previewValueForeground", new ColorUIResource(0x000000),
            "FileChooser.previewLabelFont", smallSystemFont,
            "FileChooser.previewValueFont", smallSystemFont,
            "FileChooser.splitPaneDividerSize", new Integer(6),
            "FileChooser.previewLabelInsets",new InsetsUIResource(0,0,0,4),
            "FileChooser.cellTipOrigin", new Point(18, 1),
            "FileChooser.autovalidate", Boolean.TRUE,
            "FileChooser.browserFocusCellHighlightBorder",
            new UIDefaults.ProxyLazyValue(
                    "javax.swing.plaf.BorderUIResource$EmptyBorderUIResource",
                    new Object[] { new Insets(1,1,1,1) }
            ),
            "FileChooser.browserFocusCellHighlightBorderGrayed",
            new UIDefaults.ProxyLazyValue(
                    "javax.swing.plaf.BorderUIResource$MatteBorderUIResource",
                   new Object[] { new Integer(1),new Integer(1),new Integer(1),new Integer(1), grayedFocusCellBorderColor }
            ),
            "FileChooser.browserCellBorder",
            new UIDefaults.ProxyLazyValue(
                    "javax.swing.plaf.BorderUIResource$EmptyBorderUIResource",
                    new Object[] { new Insets(1,1,1,1) }
            ),
            "FileChooser.browserUseUnselectedExpandIconForLabeledFile", Boolean.TRUE,
            
            "Sheet.showAsSheet", Boolean.TRUE,
            
        };
        table.putDefaults(uiDefaults);
    }
    protected URL getResource(String location) {
        URL url = getClass().getResource(location);
        if (url == null) {
            throw new InternalError("image resource missing: "+location);
        }
        return url;
    }
    
    protected Image createImage(String location) {
        return Toolkit.getDefaultToolkit().createImage(getResource(location));
    }
    protected Icon[] makeIcons(String location, int count, boolean horizontal) {
        Icon[] icons = new Icon[count];
        
        BufferedImage[] images = Images.split(
        createImage(location),
        count, horizontal
        );
        
        for (int i=0; i < count; i++) {
            icons[i] = new IconUIResource(new ImageIcon(images[i]));
        }
        return icons;
    }

    @Override
    public String getID() {
        return "Aqua";
    }
}


