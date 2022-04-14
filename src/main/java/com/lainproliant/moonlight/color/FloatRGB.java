package com.lainproliant.moonlight.color;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.With;

import static java.lang.Float.max;
import static java.lang.Float.min;

@Data
@With
@AllArgsConstructor
public class FloatRGB implements Color {
    public final float r;
    public final float g;
    public final float b;

    @Override
    public RGB toRGB() {
        return new RGB((int) (255 * r), (int) (255 * g), (int) (255 * b));
    }

    @Override
    public FloatRGB toFloatRGB() {
        return this;
    }

    @Override
    public HSV toHSV() {
        float h = 0.0f;
        float s = 0.0f;
        float v;

        float M = max(r, max(g, b));
        float m = min(r, min(g, b));
        float c = M - m;

        v = M;

        if (c != 0.0f) {
            if (M == r) {
                h = ((g - b) / c) % 6.0f;
            } else if (M == g) {
                h = (b - r) / c + 2.0f;
            } else {
                h = (r - g) / c + 4.0f;
            }
            h *= 60.0f;
            s = c / v;
        }

        return new HSV(h, s, v);
    }
}
