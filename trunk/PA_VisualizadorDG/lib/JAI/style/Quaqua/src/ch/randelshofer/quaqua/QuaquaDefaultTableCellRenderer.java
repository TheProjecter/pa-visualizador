/*
 * @(#)QuaquaDefaultTableCellRenderer.java  
 *
 * Copyright (c) 2006-2010 Werner Randelshofer
 * Hausmatt 10, CH-6405 Immensee, Switzerland
 * All rights reserved.
 *
 * The copyright of this software is owned by Werner Randelshofer. 
 * You may not use, copy or modify this software, except in  
 * accordance with the license agreement you entered into with  
 * Werner Randelshofer. For details see accompanying license terms. 
 */

package ch.randelshofer.quaqua;

import javax.swing.table.*;
/**
 * QuaquaDefaultTableCellRenderer.
 *
 * @author Werner Randelshofer
 * @version $Id: QuaquaDefaultTableCellRenderer.java 174 2010-01-09 16:06:52Z wrandelshofer $
 */
public class QuaquaDefaultTableCellRenderer extends DefaultTableCellRenderer.UIResource {
    
    /** Creates a new instance. */
    public QuaquaDefaultTableCellRenderer() {
        setOpaque(false);
    }
    
}
