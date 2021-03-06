/*
 * Copyright 2007 Sun Microsystems, Inc.  All Rights Reserved.
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

package ae.sun.awt.image;

import ae.java.awt.GraphicsConfiguration;
import ae.java.awt.GraphicsEnvironment;
import ae.java.awt.ImageCapabilities;
import ae.java.awt.image.BufferedImage;
import ae.sun.java2d.SurfaceData;
import ae.sun.java2d.loops.CompositeType;

/**
 * This SurfaceManager variant manages the default (software) surface of a
 * BufferedImage.
 * All rendering to the image will use the software surface as the destination.
 * This is one of the more minimalist implementations of SurfaceManager.
 */
public class BufImgSurfaceManager extends SurfaceManager {
    /**
     * A reference to the BufferedImage whose contents are being managed.
     */
    protected BufferedImage bImg;

    /**
     * The default (software) surface containing the contents of the
     * BufferedImage.
     */
    protected SurfaceData sdDefault;

    public BufImgSurfaceManager(BufferedImage bImg) {
        this.bImg = bImg;
        this.sdDefault = BufImgSurfaceData.createData(bImg);
    }

    public SurfaceData getPrimarySurfaceData() {
        return sdDefault;
    }

    /**
     * Called from platform-specific SurfaceData objects to attempt to
     * auto-restore the contents of an accelerated surface that has been lost.
     */
    public SurfaceData restoreContents() {
        return sdDefault;
    }
}
