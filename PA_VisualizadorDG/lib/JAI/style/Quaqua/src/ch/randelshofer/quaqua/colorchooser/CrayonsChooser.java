/*
 * @(#)CrayonsChooser.java  
 *
 * Copyright (c) 2005-2010 Werner Randelshofer
 * Hausmatt 10, Immensee, CH-6405, Switzerland.
 * All rights reserved.
 *
 * The copyright of this software is owned by Werner Randelshofer. 
 * You may not use, copy or modify this software, except in  
 * accordance with the license agreement you entered into with  
 * Werner Randelshofer. For details see accompanying license terms. 
 */

package ch.randelshofer.quaqua.colorchooser;


import java.awt.*;
import java.beans.*;
import javax.swing.*;
import javax.swing.colorchooser.*;
import javax.swing.plaf.*;

/**
 * A color chooser which provides a choice of Crayons.
 *
 * @author  Werner Randelshofer
 * @version $Id: CrayonsChooser.java 253 2010-07-08 21:00:25Z wrandelshofer $
 */
public class CrayonsChooser extends AbstractColorChooserPanel implements UIResource {
    private Crayons crayons;
    
    /**
     * Creates a new instance.
     */
    public CrayonsChooser() {
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents

    }//GEN-END:initComponents
    
    protected void buildChooser() {
        initComponents();

        crayons = new Crayons();
        add(crayons);
        crayons.addPropertyChangeListener(new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals("Color")) {
                    setColorToModel(crayons.getColor());
                }
            }
        });
    }
    
    public String getDisplayName() {
        return UIManager.getString("ColorChooser.crayons");
    }    
    
    public javax.swing.Icon getLargeDisplayIcon() {
        return UIManager.getIcon("ColorChooser.crayonsIcon");
    }
    
    public Icon getSmallDisplayIcon() {
        return getLargeDisplayIcon();
    }
    
    public void updateChooser() {
        crayons.setColor(getColorFromModel());
    }
    public void setColorToModel(Color color) {
        getColorSelectionModel().setSelectedColor(color);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    
}
