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

package com.alee.laf.menu;

import com.alee.managers.style.*;
import com.alee.painter.DefaultPainter;
import com.alee.painter.Painter;
import com.alee.painter.PainterSupport;
import com.alee.utils.swing.DataRunnable;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicMenuUI;
import java.awt.*;

/**
 * Custom UI for JMenu component.
 *
 * @author Mikle Garin
 */

public class WebMenuUI extends BasicMenuUI implements ShapeProvider, MarginSupport, PaddingSupport
{
    /**
     * Component painter.
     */
    @DefaultPainter (MenuPainter.class)
    protected IMenuPainter painter;

    /**
     * Runtime variables.
     */
    protected Insets margin = null;
    protected Insets padding = null;

    /**
     * Returns an instance of the WebMenuUI for the specified component.
     * This tricky method is used by UIManager to create component UIs when needed.
     *
     * @param c component that will use UI instance
     * @return instance of the WebMenuUI
     */
    @SuppressWarnings ("UnusedParameters")
    public static ComponentUI createUI ( final JComponent c )
    {
        return new WebMenuUI ();
    }

    /**
     * Installs UI in the specified component.
     *
     * @param c component for this UI
     */
    @Override
    public void installUI ( final JComponent c )
    {
        super.installUI ( c );

        // Applying skin
        StyleManager.installSkin ( menuItem );
    }

    /**
     * Uninstalls UI from the specified component.
     *
     * @param c component with this UI
     */
    @Override
    public void uninstallUI ( final JComponent c )
    {
        // Uninstalling applied skin
        StyleManager.uninstallSkin ( menuItem );

        // Uninstalling UI
        super.uninstallUI ( c );
    }

    @Override
    public Shape provideShape ()
    {
        return PainterSupport.getShape ( menuItem, painter );
    }

    @Override
    public Insets getMargin ()
    {
        return margin;
    }

    @Override
    public void setMargin ( final Insets margin )
    {
        this.margin = margin;
        PainterSupport.updateBorder ( getPainter () );
    }

    @Override
    public Insets getPadding ()
    {
        return padding;
    }

    @Override
    public void setPadding ( final Insets padding )
    {
        this.padding = padding;
        PainterSupport.updateBorder ( getPainter () );
    }

    /**
     * Returns menu item painter.
     *
     * @return menu item painter
     */
    public Painter getPainter ()
    {
        return PainterSupport.getAdaptedPainter ( painter );
    }

    /**
     * Sets menu item painter.
     * Pass null to remove menu item painter.
     *
     * @param painter new menu item painter
     */
    public void setPainter ( final Painter painter )
    {
        PainterSupport.setPainter ( menuItem, new DataRunnable<IMenuPainter> ()
        {
            @Override
            public void run ( final IMenuPainter newPainter )
            {
                WebMenuUI.this.painter = newPainter;
            }
        }, this.painter, painter, IMenuPainter.class, AdaptiveMenuPainter.class );
    }

    @Override
    public int getBaseline ( final JComponent c, final int width, final int height )
    {
        // todo return painter != null ? painter.getBaseline ( c, this, width, height ) : -1;
        return super.getBaseline ( c, width, height );
    }

    @Override
    public void paint ( final Graphics g, final JComponent c )
    {
        if ( painter != null )
        {
            painter.paint ( ( Graphics2D ) g, Bounds.component.of ( c ), c, this );
        }
    }

    /**
     * Returns menu item preferred size.
     *
     * @param c menu item component
     * @return menu item preferred size
     */
    @Override
    public Dimension getPreferredSize ( final JComponent c )
    {
        return PainterSupport.getPreferredSize ( c, painter );
    }
}