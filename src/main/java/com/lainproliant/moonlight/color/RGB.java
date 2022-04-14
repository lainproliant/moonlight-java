package com.lainproliant.moonlight.color;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.With;

@Data
@With
@AllArgsConstructor
public class RGB implements Color {
    public final int r;
    public final int g;
    public final int b;

    public static RGB of(final int c) {
        return new RGB(
                (c >> 16) & 0xFF,
                (c >> 8) & 0xFF,
                c & 0xFF);
    }

    public static RGB of(final String s) {
        if (! s.startsWith("#")) {
            throw new IllegalArgumentException("Input is not a hex color string, must start with '#'.");
        }
        return RGB.of(Integer.parseInt(s.substring(1)));
    }

    @Override
    public RGB toRGB() {
        return this;
    }

    @Override
    public FloatRGB toFloatRGB() {
        return new FloatRGB((float) r / 255.0f, (float) g / 255.0f, (float) b / 255.0f);
    }

    @Override
    public HSV toHSV() {
        return toFloatRGB().toHSV();
    }
}
