/*
 * This file is part of WebLookAndFeel library.
 *
 * WebLookAndFeel library is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * WebLookAndFeel library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with WebLookAndFeel library.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.alee.extended.overlay;

import com.alee.painter.AdaptivePainter;
import com.alee.painter.Painter;

/**
 * Simple {@link IOverlayPainter} adapter class.
 * It is used to install simple non-specific painters into {@link WOverlayUI}.
 *
 * @param <C> component type
 * @param <U> component UI type
 * @author Mikle Garin
 */
public final class AdaptiveOverlayPainter<C extends WebOverlay, U extends WOverlayUI> extends AdaptivePainter<C, U>
        implements IOverlayPainter<C, U>
{
    /**
     * Constructs new {@link AdaptiveOverlayPainter} for the specified {@link Painter}.
     *
     * @param painter {@link Painter} to adapt
     */
    public AdaptiveOverlayPainter ( final Painter painter )
    {
        super ( painter );
    }
}