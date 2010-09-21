/*
 * @(#)QuaquaCaret.java  
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
package ch.randelshofer.quaqua;

import java.awt.*;
import java.awt.event.*;

import javax.swing.plaf.*;
import javax.swing.text.*;

/**
 * QuaquaCaret.
 *
 * @author  Werner Randelshofer
 * @version $Id: QuaquaCaret.java 284 2010-08-20 17:25:48Z wrandelshofer $
 */
public class QuaquaCaret extends DefaultCaret
        implements UIResource {

    boolean isFocused = false;

    public QuaquaCaret(Window window, JTextComponent textComponent) {
    }

    @Override
    protected Highlighter.HighlightPainter getSelectionPainter() {
        return QuaquaHighlighter.painterInstance;
    }

    @Override
    public void setVisible(boolean bool) {
            if (bool == true) {
            // Don't display the caret if text is selected.
                bool = getDot() == getMark();
            }
        super.setVisible(bool);
    }

    public boolean isVisible() {
        boolean bool = super.isVisible();
        // Display non-blinking caret when component is non-editable.
        bool|= !getComponent().isEditable() && getComponent().isFocusOwner();
        return bool;
    }

    @Override
    protected void fireStateChanged() {
        if (isFocused) {
            setVisible(getComponent().isEditable());
        }
        super.fireStateChanged();
    }

    /**
     * Called when the component containing the caret gains
     * focus.  This is implemented to set the caret to visible
     * if the component is editable.
     *
     * @param e the focus event
     * @see FocusListener#focusGained
     */
    @Override
    public void focusGained(FocusEvent focusevent) {
        JTextComponent component = getComponent();
        if (component.isEnabled()) {
            isFocused = true;
        }
        if (component.isEnabled()) {
            // if (component.isEditable()) {
            setVisible(true);
            // }
            setSelectionVisible(true);
        }
    }

    @Override
    public void focusLost(FocusEvent focusevent) {
        isFocused = false;
        super.focusLost(focusevent);
    }

    @Override
    public void mousePressed(MouseEvent evt) {
        if (!evt.isPopupTrigger()) {
            super.mousePressed(evt);
        }
    }
}
