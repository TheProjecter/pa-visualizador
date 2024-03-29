/*
 * @(#)QuaquaComboBoxVisualMargin.java  
 *
 * Copyright (c) 2007-2010 Werner Randelshofer
 * Hausmatt 10, CH-6405 Immensee, Switzerland
 * All rights reserved.
 *
 * The copyright of this software is owned by Werner Randelshofer. 
 * You may not use, copy or modify this software, except in  
 * accordance with the license agreement you entered into with  
 * Werner Randelshofer. For details see accompanying license terms. 
 */

package ch.randelshofer.quaqua;

import java.awt.*;
import javax.swing.*;
/**
 * QuaquaComboBoxVisualMargin.
 *
 * @author Werner Randelshofer
 * @version $Id: QuaquaComboBoxVisualMargin.java 204 2010-04-19 19:40:22Z wrandelshofer $
 */
public class QuaquaComboBoxVisualMargin extends VisualMargin {
    
    /** Creates a new instance. */
    public QuaquaComboBoxVisualMargin() {
        super();
    }
    
    /**
     * Creates a new VisualMargin.
     *
     * @param top Defines the margin from the clip bounds of the
     * component to its visual bounds.
     * @param left Defines the margin from the clip bounds of the
     * component to its visual bounds.
     * @param bottom Defines the margin from the clip bounds of the
     * component to its visual bounds.
     * @param right Defines the margin from the clip bounds of the
     * component to its visual bounds.
     */
    public QuaquaComboBoxVisualMargin(int top, int left, int bottom, int right) {
        super(top, left, bottom, right);
    }
    public QuaquaComboBoxVisualMargin(int top, int left, int bottom, int right, boolean ftop, boolean fleft, boolean fbottom, boolean fright) {
        super(top, left, bottom, right, ftop, fleft, fbottom, fright);
    }
    public QuaquaComboBoxVisualMargin(boolean ftop, boolean fleft, boolean fbottom, boolean fright) {
        super(ftop, fleft, fbottom, fright);
    }
    /**
     * Creates a new VisualMargin.
     *
     * @param layoutMargin Defines the margin from the clip bounds of the
     * component to its visual bounds. The margin has usually negative values!
     */
    public QuaquaComboBoxVisualMargin(Insets layoutMargin) {
        super(layoutMargin);
    }
    
    /**
     * Reinitializes the insets parameter with this Border's current Insets.
     * @param c the component for which this border insets value applies
     * @param insets the object to be reinitialized
     * @return the <code>insets</code> object
     */
    protected Insets getVisualMargin(Component c, Insets insets) {
        Insets i = super.getVisualMargin(c, insets);
        if (UIManager.getBoolean("ComboBox.harmonizePreferredHeight")) {
            if (! ((JComboBox) c).isEditable()) {
                i.top += 1;
                i.bottom += 1;
            }
        }
        return i;
    }
}
