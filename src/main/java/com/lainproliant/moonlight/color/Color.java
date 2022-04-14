package com.lainproliant.moonlight.color;

import com.diogonunes.jcolor.Attribute;

import static com.diogonunes.jcolor.Attribute.BACK_COLOR;
import static com.diogonunes.jcolor.Attribute.TEXT_COLOR;

public interface Color {
    RGB toRGB();
    FloatRGB toFloatRGB();
    HSV toHSV();

    default Attribute toAnsiBack() {
        final RGB rgb = toRGB();
        return BACK_COLOR(rgb.r, rgb.g, rgb.b);
    }

    default Attribute toAnsiText() {
        final RGB rgb = toRGB();
        return TEXT_COLOR(rgb.r, rgb.g, rgb.b);
    }
}
