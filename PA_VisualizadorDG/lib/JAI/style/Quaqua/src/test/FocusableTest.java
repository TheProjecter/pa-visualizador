/*
 * @(#)FocusableTest.java  1.0  2010-07-11
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

import javax.swing.*;

/**
 * FocusableTest.
 *
 * @author Werner Randelshofer
 * @version 1.0 2010-07-11 Created.
 */
public class FocusableTest{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("ch.randelshofer.quaqua.QuaquaLookAndFeel");
        } catch (Exception ex) {ex.printStackTrace();};
        System.out.println("Focusable:" + new JRadioButton().isFocusable());
    }

}
