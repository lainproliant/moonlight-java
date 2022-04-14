package com.lainproliant.moonlight.color;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.With;

import static java.lang.Float.max;
import static java.lang.Float.min;
import static java.lang.Math.abs;

@Data
@With
public class HSV implements Color {
    public final float h;
    public final float s;
    public final float v;

    public HSV(float h, float s, float v) {
        float hx = h % 360.0f;
        if (hx < 0.0f) {
            hx += 360.0f;
        }

        this.h = hx;
        this.s = max(min(1.0f, s), 0.0f);
        this.v = max(min(1.0f, v), 0.0f);
    }

    public HSV rotate(final float angle) {
        return new HSV(h + angle, s, v).normalize();
    }

    @Override
    public RGB toRGB() {
        return toFloatRGB().toRGB();
    }

    @Override
    public FloatRGB toFloatRGB() {
        float c = 0.0f;
        float m = 0.0f;
        float x = 0.0f;
        float hx = h % 360.0f;

        c = v * s;
        x = c * (1.0f - abs((hx / 60.0f) % 2.0f - 1.0f));
        m = v - c;

        if (hx >= 0.0f && hx < 60.0f) {
            return new FloatRGB(c + m, x + m, m);
        } else if (hx >= 60.0f && hx < 120.0f) {
            return new FloatRGB(x + m, c + m, m);
        } else if (hx >= 120.0f && hx < 180.0f) {
            return new FloatRGB(m, c + m, x + m);
        } else if (hx >= 180.0f && hx < 240.0f) {
            return new FloatRGB(m, x + m, c + m);
        } else if (hx >= 240.0f && hx < 300.0f) {
            return new FloatRGB(x + m, m, c + m);
        } else {
            return new FloatRGB(c + m, m, x + m);
        }
    }

    @Override
    public HSV toHSV() {
        return this;
    }

    private HSV normalize() {
        float hx = h % 360.0f;
        if (hx < 0.0f) {
            hx += 360.0f;
        }
        return new HSV(
                hx,
                max(min(1.0f, s), 0.0f),
                max(min(1.0f, v), 0.0f));
    }

}
