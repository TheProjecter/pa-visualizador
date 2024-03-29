/*
 * @(#)OptionPaneTest.java  
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

import ch.randelshofer.quaqua.QuaquaUtilities;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
/**
 * OptionPaneTest.
 *
 * @author  Werner Randelshofer
 * @version $Id: OptionPaneTest.java 282 2010-08-20 16:58:47Z wrandelshofer $
 */
public class OptionPaneTest extends javax.swing.JPanel {
    
    /** Creates new form. */
    public OptionPaneTest() {
        initComponents();
    }

    private Window getWindowAncestor(Component c) {
        for(Container p = c.getParent(); p != null; p = p.getParent()) {
            if (p instanceof Window) {
                return (Window)p;
            }
        }
        return null;
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        javaAlertsLabel = new javax.swing.JLabel();
        javaAlertsPanel = new javax.swing.JPanel();
        questionAlertButton = new javax.swing.JButton();
        errorAlertButton = new javax.swing.JButton();
        warningAlertButton = new javax.swing.JButton();
        infoAlertButton = new javax.swing.JButton();
        ahigAlertsLabel = new javax.swing.JLabel();
        ahigAlertsPanel = new javax.swing.JPanel();
        reviewChangesAlertButton = new javax.swing.JButton();
        saveChangesAlertButton = new javax.swing.JButton();
        optionPaneAlertsLabel = new javax.swing.JLabel();
        optionPaneAlertsPanel = new javax.swing.JPanel();
        confirmDialogButton = new javax.swing.JButton();
        inputDialogButton = new javax.swing.JButton();
        messageDialogButton = new javax.swing.JButton();
        longMessageDialogButton = new javax.swing.JButton();
        othersLabel = new javax.swing.JLabel();
        optionPaneAlertsPanel1 = new javax.swing.JPanel();
        confirmDialogButton1 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        FormListener formListener = new FormListener();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(12, 20, 20, 20));
        setLayout(new java.awt.GridBagLayout());

        javaAlertsLabel.setText("Java Look and Feel Guidelines:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        add(javaAlertsLabel, gridBagConstraints);

        javaAlertsPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        questionAlertButton.setText("Question Alert");
        questionAlertButton.addActionListener(formListener);
        javaAlertsPanel.add(questionAlertButton);

        errorAlertButton.setText("Error Alert");
        errorAlertButton.addActionListener(formListener);
        javaAlertsPanel.add(errorAlertButton);

        warningAlertButton.setText("Warning Alert");
        warningAlertButton.addActionListener(formListener);
        javaAlertsPanel.add(warningAlertButton);

        infoAlertButton.setText("Info Alert");
        infoAlertButton.addActionListener(formListener);
        javaAlertsPanel.add(infoAlertButton);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        add(javaAlertsPanel, gridBagConstraints);

        ahigAlertsLabel.setText("Apple Human Interface Guidelines:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        add(ahigAlertsLabel, gridBagConstraints);

        ahigAlertsPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        reviewChangesAlertButton.setText("Review Changes Alert");
        reviewChangesAlertButton.addActionListener(formListener);
        ahigAlertsPanel.add(reviewChangesAlertButton);

        saveChangesAlertButton.setText("Save Changes Alert");
        saveChangesAlertButton.addActionListener(formListener);
        ahigAlertsPanel.add(saveChangesAlertButton);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        add(ahigAlertsPanel, gridBagConstraints);

        optionPaneAlertsLabel.setText("JOptionPane Examples:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        add(optionPaneAlertsLabel, gridBagConstraints);

        optionPaneAlertsPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        confirmDialogButton.setText("Confirm Dialog");
        confirmDialogButton.addActionListener(formListener);
        optionPaneAlertsPanel.add(confirmDialogButton);

        inputDialogButton.setText("Input Dialog");
        inputDialogButton.addActionListener(formListener);
        optionPaneAlertsPanel.add(inputDialogButton);

        messageDialogButton.setText("Message Dialog");
        messageDialogButton.addActionListener(formListener);
        optionPaneAlertsPanel.add(messageDialogButton);

        longMessageDialogButton.setText("Long Message Dialog");
        longMessageDialogButton.addActionListener(formListener);
        optionPaneAlertsPanel.add(longMessageDialogButton);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        add(optionPaneAlertsPanel, gridBagConstraints);

        othersLabel.setText("Other Examples");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        add(othersLabel, gridBagConstraints);

        optionPaneAlertsPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        confirmDialogButton1.setText("JLabels Alert");
        confirmDialogButton1.addActionListener(formListener);
        optionPaneAlertsPanel1.add(confirmDialogButton1);

        jButton1.setText("JTable Alert");
        jButton1.addActionListener(formListener);
        optionPaneAlertsPanel1.add(jButton1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(optionPaneAlertsPanel1, gridBagConstraints);
    }

    // Code for dispatching events from components to event handlers.

    private class FormListener implements java.awt.event.ActionListener {
        FormListener() {}
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            if (evt.getSource() == questionAlertButton) {
                OptionPaneTest.this.questionAlert(evt);
            }
            else if (evt.getSource() == errorAlertButton) {
                OptionPaneTest.this.errorAlert(evt);
            }
            else if (evt.getSource() == warningAlertButton) {
                OptionPaneTest.this.warningAlert(evt);
            }
            else if (evt.getSource() == infoAlertButton) {
                OptionPaneTest.this.infoAlert(evt);
            }
            else if (evt.getSource() == reviewChangesAlertButton) {
                OptionPaneTest.this.reviewChangesAlert(evt);
            }
            else if (evt.getSource() == saveChangesAlertButton) {
                OptionPaneTest.this.saveChangesAlert(evt);
            }
            else if (evt.getSource() == confirmDialogButton) {
                OptionPaneTest.this.confirmDialog(evt);
            }
            else if (evt.getSource() == inputDialogButton) {
                OptionPaneTest.this.inputDialog(evt);
            }
            else if (evt.getSource() == messageDialogButton) {
                OptionPaneTest.this.messageDialog(evt);
            }
            else if (evt.getSource() == longMessageDialogButton) {
                OptionPaneTest.this.longMessageDialogButtonPerformed(evt);
            }
            else if (evt.getSource() == confirmDialogButton1) {
                OptionPaneTest.this.showJLabelsAlert(evt);
            }
            else if (evt.getSource() == jButton1) {
                OptionPaneTest.this.showJTableAlert(evt);
            }
        }
    }// </editor-fold>//GEN-END:initComponents
    
    private void showJLabelsAlert(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showJLabelsAlert
        JLabel l1 = new JLabel("The first JLabel is bold");
        l1.setFont(l1.getFont().deriveFont(Font.BOLD));
        JLabel l2 = new JLabel("The second JLabel is in italics");
        l2.setFont(l1.getFont().deriveFont(Font.ITALIC));
        JLabel[] labels = { l1, l2 };
        JOptionPane.showMessageDialog(this, labels);
    }//GEN-LAST:event_showJLabelsAlert
    
    private void longMessageDialogButtonPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_longMessageDialogButtonPerformed
        JOptionPane.showMessageDialog(this,
                "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. " +
                "Vestibulum ligula neque, sagittis sit amet, tempor quis, pharetra " +
                "eu, mi. Praesent lacus. Suspendisse nunc tellus, nonummy quis, " +
                "iaculis sit amet, congue a, elit. Aliquam et dolor. Nunc a lorem. " +
                "Nunc orci tortor, aliquet ut, ullamcorper sit amet, varius id, " +
                "turpis. Aliquam bibendum arcu at est. Integer dictum, tortor " +
                "fermentum rhoncus bibendum, nisi quam commodo leo, et luctus velit " +
                "elit eget nisl. Vestibulum ante ante, convallis non, posuere vel, " +
                "viverra ut, turpis. Integer convallis rutrum neque. Suspendisse " +
                "elit sapien, sollicitudin et, rhoncus in, sodales id, eros. Sed " +
                "sodales velit pellentesque sapien. Phasellus in nunc. In vestibulum " +
                "augue a diam. Fusce aliquet vulputate nisi."
                );
    }//GEN-LAST:event_longMessageDialogButtonPerformed
    
    private void messageDialog(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_messageDialog
        JOptionPane.showMessageDialog(this, "You have got a message.");
    }//GEN-LAST:event_messageDialog
    
    private void inputDialog(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputDialog
        System.out.println("user entered "+JOptionPane.showInputDialog(this, "Enter your name."));
    }//GEN-LAST:event_inputDialog
    
    private void confirmDialog(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmDialog
        analyzeOption(JOptionPane.showConfirmDialog(this, "Do you want to confirm this dialog?"));
    }//GEN-LAST:event_confirmDialog
    
    private void reviewChangesAlert(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reviewChangesAlert
        // Example taken from
        // http://developer.apple.com/documentation/UserExperience/Conceptual/OSXHIGuidelines/XHIGWindows/chapter_17_section_6.html#//apple_ref/doc/uid/20000961-TPXREF11
        JOptionPane pane = new JOptionPane(
                "<html>"+
                "<head>"+
                "<style type=\"text/css\">"+
                "b { font: 13pt \"Lucida Grande\" }"+
                "p { font: 11pt \"Lucida Grande\"; margin-top: 8px }"+
                "</style>"+
                "</head>"+
                "<b>You have 4 documents with unsaved changes. Do you want to "+
                "review these changes before quitting?</b><p>"+
                "If you don't review your documents, all your changes will be lost.",
                JOptionPane.QUESTION_MESSAGE
                );
        Object[] options = { "Review Changes...", "Cancel", "Discard Changes" };
        pane.setOptions(options);
        pane.setInitialValue(options[0]);
        pane.putClientProperty("Quaqua.OptionPane.destructiveOption", new Integer(2));
        JDialog dialog = pane.createDialog(this, "On this alert, \"Review Changes\" should be the default action.");
        dialog.setVisible(true);
        analyzeValue(pane.getValue());
        
    }//GEN-LAST:event_reviewChangesAlert
    
    private void saveChangesAlert(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveChangesAlert
        // Example taken from
        // http://developer.apple.com/documentation/UserExperience/Conceptual/OSXHIGuidelines/XHIGWindows/chapter_17_section_6.html#//apple_ref/doc/uid/20000961-TPXREF11
        JOptionPane pane = new JOptionPane(
                "<html>"+
                "<head>"+
                "<style type=\"text/css\">"+
                "body { font: 11pt \"Lucida Grande\" }"+
                "b { font: 13pt \"Lucida Grande\" }"+
                "p { font: 11pt \"Lucida Grande\"; margin-top: 8px }"+
                "</style>"+
                "</head>"+
                "<b>Do you want to save the changes you made "+
                "to the project \"Apple HIG\"?</b><p>"+
                "Your changes will be lost if you don't save them.",
                JOptionPane.WARNING_MESSAGE
                );
        Object[] options = { "Save", "Cancel", "Don't Save" };
        pane.putClientProperty("Quaqua.OptionPane.destructiveOption", new Integer(2));
        pane.setOptions(options);
        pane.setInitialValue(options[0]);
        JDialog dialog = pane.createDialog(this, "On this alert, \"Save\" should be the default action.");
        dialog.setVisible(true);
        analyzeValue(pane.getValue());
        
        
    }//GEN-LAST:event_saveChangesAlert
    
    private void questionAlert(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_questionAlert
        // Example taken from
        // http://java.sun.com/products/jlf/ed2/book/HIG.Dialogs5.html#41765
        JOptionPane pane = new JOptionPane(
                "<html>"+
        /*
        "<head>"+
        "<style type=\"text/css\">"+
        "b { font: 13pt \"Lucida Grande\" }"+
        "p { font: 11pt \"Lucida Grande\"; margin-top: 8px }"+
        "</style>"+
        "</head>"+
         */
                "<b>Enter your name.</b>",
                JOptionPane.QUESTION_MESSAGE
                );
        pane.setWantsInput(true);
        JDialog dialog = pane.createDialog(this, "Name Needed - Metal Database");
        dialog.setVisible(true);
        if (pane.getValue() == null) {
            System.out.println("user closed option pane");
        } else {
            analyzeOption(((Integer) pane.getValue()).intValue());
            analyzeValue(pane.getInputValue());
        }
    }//GEN-LAST:event_questionAlert
    
    private void errorAlert(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_errorAlert
        // Example taken from
        // http://java.sun.com/products/jlf/ed2/book/HIG.Dialogs5.html#35722
        JOptionPane pane = new JOptionPane(
                "<html>"+
        /*
        "<head>"+
        "<style type=\"text/css\">"+
        "b { font: 13pt \"Lucida Grande\" }"+
        "p { font: 11pt \"Lucida Grande\"; margin-top: 8px }"+
        "</style>"+
        "</head>"+*/
                "<b>Out of Paper</b><p>"+
                "To continue printing, add more paper to the printer "
                +"and press Continue.",
                JOptionPane.ERROR_MESSAGE
                );
        Object[] options = { "Continue", "Cancel", "Help" };
        pane.setOptions(options);
        pane.setInitialValue(options[0]);
        JDialog dialog = pane.createDialog(this, "Error 87 - MetalEdit");
        dialog.setVisible(true);
        analyzeValue(pane.getValue());
        
    }//GEN-LAST:event_errorAlert
    
    private void warningAlert(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_warningAlert
        // Example taken from
        // http://java.sun.com/products/jlf/ed2/book/HIG.Dialogs5.html#43221
        
        JOptionPane pane = new JOptionPane(
                "<html>"+
        /*
        "<head>"+
        "<style type=\"text/css\">"+
        "b { font: 13pt \"Lucida Grande\" }"+
        "p { font: 11pt \"Lucida Grande\"; margin-top: 8px }"+
        "</style>"+
        "</head>"+*/
                "<b>File Exists</b><p>"+
                "A file named \"patience.gif\" already exists.<br>"+
                "Replace existing file?",
                JOptionPane.WARNING_MESSAGE
                );
        Object[] options = { "Replace", "Cancel" };
        pane.setOptions(options);
        pane.setInitialValue(null);
        JDialog dialog = pane.createDialog(this, "Warning without default button");
        dialog.setVisible(true);
        analyzeValue(pane.getValue());
        
    }//GEN-LAST:event_warningAlert
    
    private void infoAlert(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_infoAlert
        // Example taken from
        // http://java.sun.com/products/jlf/ed2/book/HIG.Dialogs5.html#40758
        
        JOptionPane.showMessageDialog(this,
                "<html>"+
        /*
        "<head>"+
        "<style type=\"text/css\">"+
        "b { font: 13pt \"Lucida Grande\" }"+
        "p { font: 11pt \"Lucida Grande\"; margin-top: 8px }"+
        "</style>"+
        "</head>"+
         */
                "<b>Reminder</b><p>"+
                "11:00 am - 12:00 noon<br>"+
                "Human Interface Staff meeting<br>"+
                "Corthout conference room.",
                "Appointment - MetalButler", JOptionPane.INFORMATION_MESSAGE
                );
    }//GEN-LAST:event_infoAlert

    private void showJTableAlert(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showJTableAlert
       final DefaultTableModel tableModel = new DefaultTableModel (new Integer [ ]
[ ] { {1}, {2}},
                                                                new String [ ]
{"header"});
     JTable table = new JTable (tableModel);
     JScrollPane sp = new JScrollPane (table);
     final JOptionPane op = new JOptionPane (sp);
     final JDialog d = new JDialog ((Frame)getWindowAncestor(this) );
    d.add (op);
    d.setSize (600, 400);
    d.setLocationRelativeTo (this);
    d.setModal (true);
    op.addPropertyChangeListener(new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent event) {
                // Let the defaultCloseOperation handle the closing
                // if the user closed the window without selecting a button
                // (newValue = null in that case).  Otherwise, close the dialog.
                if (d.isVisible() && event.getSource() == op &&
                  (event.getPropertyName().equals(JOptionPane.VALUE_PROPERTY)) &&
                  event.getNewValue() != null &&
                  event.getNewValue() != JOptionPane.UNINITIALIZED_VALUE) {
                    d.setVisible(false);
                }
            }
        });
    d.setVisible (true);

    }//GEN-LAST:event_showJTableAlert
    
    private void analyzeOption(int option) {
        switch (option) {
            case JOptionPane.CANCEL_OPTION:
                System.out.println("user canceled option pane");
                break;
            case JOptionPane.CLOSED_OPTION :
                System.out.println("user closed option pane");
                break;
            case JOptionPane.NO_OPTION :
                System.out.println("user chose no");
                break;
            case JOptionPane.OK_OPTION :
                //case JOptionPane.YES_OPTION : (same as OK_OPTION)
                System.out.println("user chose ok or yes");
                break;
            default :
                System.out.println("user chose "+option);
                break;
        }
    }
    private void analyzeValue(Object value) {
        System.out.println("user chose "+value);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ahigAlertsLabel;
    private javax.swing.JPanel ahigAlertsPanel;
    private javax.swing.JButton confirmDialogButton;
    private javax.swing.JButton confirmDialogButton1;
    private javax.swing.JButton errorAlertButton;
    private javax.swing.JButton infoAlertButton;
    private javax.swing.JButton inputDialogButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel javaAlertsLabel;
    private javax.swing.JPanel javaAlertsPanel;
    private javax.swing.JButton longMessageDialogButton;
    private javax.swing.JButton messageDialogButton;
    private javax.swing.JLabel optionPaneAlertsLabel;
    private javax.swing.JPanel optionPaneAlertsPanel;
    private javax.swing.JPanel optionPaneAlertsPanel1;
    private javax.swing.JLabel othersLabel;
    private javax.swing.JButton questionAlertButton;
    private javax.swing.JButton reviewChangesAlertButton;
    private javax.swing.JButton saveChangesAlertButton;
    private javax.swing.JButton warningAlertButton;
    // End of variables declaration//GEN-END:variables
    
}
