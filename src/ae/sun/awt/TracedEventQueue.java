/*
 * Copyright 1997 Sun Microsystems, Inc.  All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Sun designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Sun in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Sun Microsystems, Inc., 4150 Network Circle, Santa Clara,
 * CA 95054 USA or visit www.sun.com if you need additional information or
 * have any questions.
 */

/**
 * An EventQueue subclass which adds selective tracing of events as they
 * are posted to an EventQueue.  Tracing is globally enabled and disabled
 * by the AWT.TraceEventPosting property in awt.properties.  <P>
 *
 * The optional AWT.NoTraceIDs property defines a list of AWTEvent IDs
 * which should not be traced, such as MouseEvent.MOUSE_MOVED or PaintEvents.
 * This list is declared by specifying the decimal value of each event's ID,
 * separated by commas.
 *
 * @author  Thomas Ball
 */

package ae.sun.awt;

import ae.java.awt.EventQueue;
import ae.java.awt.AWTEvent;
import ae.java.awt.Toolkit;
import java.util.StringTokenizer;

public class TracedEventQueue extends EventQueue {

    // Determines whether any event tracing is enabled.
    static boolean trace = false;

    // The list of event IDs to ignore when tracing.
    static int suppressedIDs[] = null;

    static {
        String s = Toolkit.getProperty("AWT.IgnoreEventIDs", "");
        if (s.length() > 0) {
            StringTokenizer st = new StringTokenizer(s, ",");
            int nIDs = st.countTokens();
            suppressedIDs = new int[nIDs];
            for (int i = 0; i < nIDs; i++) {
                String idString = st.nextToken();
                try {
                    suppressedIDs[i] = Integer.parseInt(idString);
                } catch (NumberFormatException e) {
                    System.err.println("Bad ID listed in AWT.IgnoreEventIDs " +
                                       "in awt.properties: \"" +
                                       idString + "\" -- skipped");
                    suppressedIDs[i] = 0;
                }
            }
        } else {
            suppressedIDs = new int[0];
        }
    }

    public void postEvent(AWTEvent theEvent) {
        boolean printEvent = true;
        int id = theEvent.getID();
        for (int i = 0; i < suppressedIDs.length; i++) {
            if (id == suppressedIDs[i]) {
                printEvent = false;
                break;
            }
        }
        if (printEvent) {
            System.out.println(Thread.currentThread().getName() +
                               ": " + theEvent);
        }
        super.postEvent(theEvent);
    }
}
