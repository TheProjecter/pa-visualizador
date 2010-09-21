/*
 * @(#)SplitPaneTest.java  1.0  13 February 2005
 *
 * Copyright (c) 2004 Werner Randelshofer
 * Hausmatt 10, Immensee, CH-6405, Switzerland.
 * All rights reserved.
 *
 * The copyright of this software is owned by Werner Randelshofer. 
 * You may not use, copy or modify this software, except in  
 * accordance with the license agreement you entered into with  
 * Werner Randelshofer. For details see accompanying license terms. 
 */

package test;

import javax.swing.*;
import javax.swing.border.*;
import java.util.*;
/**
 * SplitPaneTest.
 *
 * @author  Werner Randelshofer
 * @version 1.0  13 February 2005  Created.
 */
public class SplitPaneTest extends javax.swing.JPanel {
    
    /** Creates new form. */
    public SplitPaneTest() {
        //UIManager.put("SplitPane.border",new EmptyBorder(0,0,0,0));
        initComponents();
        
        splitPane1.putClientProperty("Quaqua.SplitPane.style","bar");
        splitPane3.putClientProperty("Quaqua.SplitPane.style","bar");
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        final long start = System.currentTimeMillis();
        final java.util.List argList = Arrays.asList(args);
        // Explicitly turn on font antialiasing.
        System.setProperty("swing.aatext", "true");
        
        // Launch the test program
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                long edtEnd = System.currentTimeMillis();
                int index = argList.indexOf("-laf");
                if (index != -1 && index < argList.size() - 1) {
                    try {
                        UIManager.setLookAndFeel((String) argList.get(index + 1));
                    } catch (Exception e) {
                        // can't do anything about this
                    }
                } else {
                    try {
                        UIManager.setLookAndFeel("ch.randelshofer.quaqua.QuaquaLookAndFeel");
                    } catch (Exception e) {
                        // can't do anything about this
                    }
                }
                
                JComponent c = new SplitPaneTest();
                JFrame f = new JFrame("SplitPane Test");
                f.getContentPane().add(c);
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f.pack();
                f.setVisible(true);
                long end = System.currentTimeMillis();
                System.out.println("total startup latency="+(end - start));
            }
        });
    }    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        splitPane1 = new javax.swing.JSplitPane();
        splitPane2 = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        splitPane3 = new javax.swing.JSplitPane();
        splitPane4 = new javax.swing.JSplitPane();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();

        setLayout(new java.awt.BorderLayout());

        splitPane1.setResizeWeight(0.5);
        splitPane2.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        splitPane2.setResizeWeight(0.5);
        splitPane2.setLeftComponent(jPanel1);

        splitPane2.setRightComponent(jPanel2);

        splitPane1.setLeftComponent(splitPane2);

        splitPane3.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        splitPane3.setResizeWeight(0.5);
        splitPane3.setOneTouchExpandable(true);
        splitPane4.setResizeWeight(0.5);
        splitPane4.setOneTouchExpandable(true);
        splitPane4.setLeftComponent(jPanel4);

        splitPane4.setRightComponent(jPanel5);

        splitPane3.setLeftComponent(splitPane4);

        splitPane3.setRightComponent(jPanel3);

        splitPane1.setRightComponent(splitPane3);

        add(splitPane1, java.awt.BorderLayout.CENTER);

    }//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JSplitPane splitPane1;
    private javax.swing.JSplitPane splitPane2;
    private javax.swing.JSplitPane splitPane3;
    private javax.swing.JSplitPane splitPane4;
    // End of variables declaration//GEN-END:variables
    
}