/*
 * Copyright 2004 Sun Microsystems, Inc.  All Rights Reserved.
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

package ae.sun.font;

import ae.java.awt.Rectangle;
import ae.java.awt.Shape;
import ae.java.awt.geom.AffineTransform;
import ae.java.awt.geom.PathIterator;
import ae.java.awt.geom.Point2D;
import ae.java.awt.geom.Rectangle2D;

/**
 * To avoid people downcasting Shape to a known mutable subclass and
 * mucking with its internals, we need to interpose a subclass that
 * cannot be mutated or downcasted.
 */
public final class DelegatingShape implements Shape {
    Shape delegate;

    public DelegatingShape(Shape delegate) {
        this.delegate = delegate;
    }

    public Rectangle getBounds() {
        return delegate.getBounds(); // assumes all delegates are immutable via the returned Rectangle
    }

    public Rectangle2D getBounds2D() {
        return delegate.getBounds2D();  // assumes all delegates are immutable via the returned Rectangle2D
    }

    public boolean contains(double x, double y) {
        return delegate.contains(x, y);
    }

    public boolean contains(Point2D p) {
        return delegate.contains(p);
    }

    public boolean intersects(double x, double y, double w, double h) {
        return delegate.intersects(x, y, w, h);
    }

    public boolean intersects(Rectangle2D r) {
        return delegate.intersects(r);
    }

    public boolean contains(double x, double y, double w, double h) {
        return delegate.contains(x, y, w, h);
    }

    public boolean contains(Rectangle2D r) {
        return delegate.contains(r);
    }

    public PathIterator getPathIterator(AffineTransform at) {
        return delegate.getPathIterator(at);
    }

    public PathIterator getPathIterator(AffineTransform at, double flatness) {
        return delegate.getPathIterator(at, flatness);
    }
}
