/*
 * Copyright 2001 Sun Microsystems, Inc.  All Rights Reserved.
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

package ae.sun.java2d.pipe;

import ae.java.awt.Color;
import ae.java.awt.Image;
import ae.java.awt.image.BufferedImage;
import ae.java.awt.image.BufferedImageOp;
import ae.java.awt.image.ImageObserver;
import ae.java.awt.geom.AffineTransform;
import ae.sun.java2d.SunGraphics2D;

/**
 * This interface defines the set of calls that pipeline objects
 * can use to pass on responsibility for performing various
 * image copy commands.
 * There are 3 types of image copies handled by this class:
 *    - copyImage: These methods simply copy the pixels
 *      from the src to dest, either from (0, 0) (implicit)
 *      or from a given (sx, sy) location.
 *    - scaleImage: These methods copy from src to dest while
 *      scaling the source image.  The src and dest rectangles
 *      are used to specify the scale.
 *    - copyImageBg: These methods behave the same as the
 *      copyImage methods except they substitute the given
 *      background color for any transparent pixels.
 *    - scaleImageBg: These methods behave the same as the
 *      scaleImage methods except they substitute the given
 *      background color for any transparent pixels.
 *    - transformImage....
 */
public interface DrawImagePipe {

    public boolean copyImage(SunGraphics2D sg, Image img,
                             int x, int y,
                             Color bgColor,
                             ImageObserver observer);

    public boolean copyImage(SunGraphics2D sg, Image img,
                             int dx, int dy, int sx, int sy, int w, int h,
                             Color bgColor,
                             ImageObserver observer);

    public boolean scaleImage(SunGraphics2D sg, Image img, int x, int y,
                              int width, int height,
                              Color bgColor,
                              ImageObserver observer);

    public boolean scaleImage(SunGraphics2D sg, Image img,
                              int dx1, int dy1, int dx2, int dy2,
                              int sx1, int sy1, int sx2, int sy2,
                              Color bgColor,
                              ImageObserver observer);

    public boolean transformImage(SunGraphics2D sg, Image img,
                                  AffineTransform atfm,
                                  ImageObserver observer);

    public void transformImage(SunGraphics2D sg, BufferedImage img,
                               BufferedImageOp op, int x, int y);


}
