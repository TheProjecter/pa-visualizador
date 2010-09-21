/*
 * @(#)QuaquaMenuColorProblem.java  1.0  2010-07-02
 * 
 * Copyright (c) 2010 Werner Randelshofer
 * Staldenmattweg 2, Immensee, CH-6405, Switzerland.
 * All rights reserved.
 * 
 * The copyright of this software is owned by Werner Randelshofer. 
 * You may not use, copy or modify this software, except in  
 * accordance with the license agreement you entered into with  
 * Werner Randelshofer. For details see accompanying license terms.
 */

package test;

/**
 * QuaquaMenuColorProblem.
 *
 * @author Werner Randelshofer
 * @version 1.0 2010-07-02 Created.
 */
import java.awt.Color;
import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIManager;

public class QuaquaMenuColorProblem
{
  public static void main (final String [ ] args) throws Exception
  {
    UIManager.setLookAndFeel (UIManager.getSystemLookAndFeelClassName ( ));
    showExample ( );
    UIManager.setLookAndFeel ("ch.randelshofer.quaqua.QuaquaLookAndFeel");
    UIManager.setLookAndFeel ("ch.randelshofer.quaqua.leopard.Quaqua16LeopardLookAndFeel");
    showExample ( );
    System.exit (0);
  }
  private static void showExample ( )
  {
    final JMenu jMenu = new JMenu ("File");
    jMenu.setBackground (Color.GREEN);
    JMenuItem item = new JMenuItem ("Open");
    item.setBackground (Color.RED);
    jMenu.add(item);
    jMenu.add(new JMenuItem ("Close"));
    final JMenuBar jMenuBar = new JMenuBar ( );
    jMenuBar.setBackground (Color.GREEN);
    jMenuBar.add (jMenu);
    final JDialog jDialog = new JDialog ( );
    jDialog.setJMenuBar (jMenuBar);
    jDialog.setSize (600, 400);
    jDialog.setLocationRelativeTo (null);
    jDialog.setModal (true);
    jDialog.setVisible (true);
  }
}