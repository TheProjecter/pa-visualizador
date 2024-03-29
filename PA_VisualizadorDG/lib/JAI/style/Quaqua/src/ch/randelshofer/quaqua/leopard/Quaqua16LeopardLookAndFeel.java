/*
 * @(#)Quaqua16LeopardLookAndFeel.java  1.0  2008-09-01
 *
 * Copyright (c) 2008-2010 Werner Randelshofer
 * Hausmatt 10, Immensee, CH-6405, Switzerland.
 * All rights reserved.
 *
 * The copyright of this software is owned by Werner Randelshofer. 
 * You may not use, copy or modify this software, except in  
 * accordance with the license agreement you entered into with  
 * Werner Randelshofer. For details see accompanying license terms. 
 */
package ch.randelshofer.quaqua.leopard;

import ch.randelshofer.quaqua.QuaquaLayoutStyle;
import javax.swing.LayoutStyle;

/**
 * The Quaqua16LeopardLookAndFeel provides bug fixes and enhancements for Apple's
 * Aqua Look and Feel for Java 1.6 on Mac OS X 10.5 (Leopard). 
 * <p>
 * The Quaqua Look and Feel can not be used on other platforms than Mac OS X.
 * <p>
 * <h3>Usage</h3>
 * Please use the <code>QuaquaManager</code> to activate this look and feel in
 * your application. Or use the generic <code>QuaquaLookAndFeel</code>. Both
 * are designed to autodetect the appropriate Quaqua Look and Feel
 * implementation.
 * <p>
 * 
 * @author Werner Randelshofer
 * @version 1.0 2008-09-01 Created. 
 */
public class Quaqua16LeopardLookAndFeel extends Quaqua15LeopardLookAndFeel {
    @Override
    public LayoutStyle getLayoutStyle() {
        return new QuaquaLayoutStyle();
        
    }
}
