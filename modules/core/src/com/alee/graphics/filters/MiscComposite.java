/*
 * Copyright 2006 Jerry Huxtable
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.alee.graphics.filters;

import java.awt.*;
import java.awt.image.ColorModel;

/**
 * @author Jerry Huxtable
 */
public final class MiscComposite implements Composite
{
    public static final int BLEND = 0;
    public static final int ADD = 1;
    public static final int SUBTRACT = 2;
    public static final int DIFFERENCE = 3;

    public static final int MULTIPLY = 4;
    public static final int DARKEN = 5;
    public static final int BURN = 6;
    public static final int COLOR_BURN = 7;

    public static final int SCREEN = 8;
    public static final int LIGHTEN = 9;
    public static final int DODGE = 10;
    public static final int COLOR_DODGE = 11;

    public static final int HUE = 12;
    public static final int SATURATION = 13;
    public static final int VALUE = 14;
    public static final int COLOR = 15;

    public static final int OVERLAY = 16;
    public static final int SOFT_LIGHT = 17;
    public static final int HARD_LIGHT = 18;
    public static final int PIN_LIGHT = 19;

    public static final int EXCLUSION = 20;
    public static final int NEGATION = 21;
    public static final int AVERAGE = 22;

    public static final int STENCIL = 23;
    public static final int SILHOUETTE = 24;

    private static final int MIN_RULE = BLEND;
    private static final int MAX_RULE = SILHOUETTE;

    public static String[] RULE_NAMES = { "Normal", "Add", "Subtract", "Difference",

            "Multiply", "Darken", "Burn", "Color Burn",

            "Screen", "Lighten", "Dodge", "Color Dodge",

            "Hue", "Saturation", "Brightness", "Color",

            "Overlay", "Soft Light", "Hard Light", "Pin Light",

            "Exclusion", "Negation", "Average",

            "Stencil", "Silhouette", };

    protected float extraAlpha;
    protected int rule;

    private MiscComposite ( final int rule )
    {
        this ( rule, 1.0f );
    }

    private MiscComposite ( final int rule, final float alpha )
    {
        if ( alpha < 0.0f || alpha > 1.0f )
        {
            throw new IllegalArgumentException ( "alpha value out of range" );
        }
        if ( rule < MIN_RULE || rule > MAX_RULE )
        {
            throw new IllegalArgumentException ( "unknown composite rule" );
        }
        this.rule = rule;
        this.extraAlpha = alpha;
    }

    public static Composite getInstance ( final int rule, final float alpha )
    {
        switch ( rule )
        {
            case MiscComposite.BLEND:
                return AlphaComposite.getInstance ( AlphaComposite.SRC_OVER, alpha );
            //		case MiscComposite.ADD:
            //			return new AddComposite( alpha );
            //		case MiscComposite.SUBTRACT:
            //			return new SubtractComposite( alpha );
            //		case MiscComposite.DIFFERENCE:
            //			return new DifferenceComposite( alpha );
            //		case MiscComposite.MULTIPLY:
            //			return new MultiplyComposite( alpha );
            //		case MiscComposite.DARKEN:
            //			return new DarkenComposite( alpha );
            //		case MiscComposite.BURN:
            //			return new BurnComposite( alpha );
            //		case MiscComposite.COLOR_BURN:
            //			return new ColorBurnComposite( alpha );
            //		case MiscComposite.SCREEN:
            //			return new ScreenComposite( alpha );
            //		case MiscComposite.LIGHTEN:
            //			return new LightenComposite( alpha );
            //		case MiscComposite.DODGE:
            //			return new DodgeComposite( alpha );
            //		case MiscComposite.COLOR_DODGE:
            //			return new ColorDodgeComposite( alpha );
            //		case MiscComposite.HUE:
            //			return new HueComposite( alpha );
            //		case MiscComposite.SATURATION:
            //			return new SaturationComposite( alpha );
            //		case MiscComposite.VALUE:
            //			return new ValueComposite( alpha );
            //		case MiscComposite.COLOR:
            //			return new ColorComposite( alpha );
            //		case MiscComposite.OVERLAY:
            //			return new OverlayComposite( alpha );
            //		case MiscComposite.SOFT_LIGHT:
            //			return new SoftLightComposite( alpha );
            //		case MiscComposite.HARD_LIGHT:
            //			return new HardLightComposite( alpha );
            //		case MiscComposite.PIN_LIGHT:
            //			return new PinLightComposite( alpha );
            //		case MiscComposite.EXCLUSION:
            //			return new ExclusionComposite( alpha );
            //		case MiscComposite.NEGATION:
            //			return new NegationComposite( alpha );
            //		case MiscComposite.AVERAGE:
            //			return new AverageComposite( alpha );
            case MiscComposite.STENCIL:
                return AlphaComposite.getInstance ( AlphaComposite.DST_IN, alpha );
            case MiscComposite.SILHOUETTE:
                return AlphaComposite.getInstance ( AlphaComposite.DST_OUT, alpha );
        }
        return new MiscComposite ( rule, alpha );
    }

    @Override
    public CompositeContext createContext ( final ColorModel srcColorModel, final ColorModel dstColorModel, final RenderingHints hints )
    {
        return new MiscCompositeContext ( rule, extraAlpha, srcColorModel, dstColorModel );
    }

    public float getAlpha ()
    {
        return extraAlpha;
    }

    public int getRule ()
    {
        return rule;
    }

    @Override
    public int hashCode ()
    {
        return Float.floatToIntBits ( extraAlpha ) * 31 + rule;
    }

    @Override
    public boolean equals ( final Object o )
    {
        if ( !( o instanceof MiscComposite ) )
        {
            return false;
        }
        final MiscComposite c = ( MiscComposite ) o;
        return rule == c.rule && extraAlpha == c.extraAlpha;
    }
}