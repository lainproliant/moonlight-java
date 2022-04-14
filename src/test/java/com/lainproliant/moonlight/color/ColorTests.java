package com.lainproliant.moonlight.color;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ColorTests {
    @Test
    void colorConversionTest() {
        final var rgb = RGB.of(0xFF00FF);
        final var hsv = rgb.toHSV();

        assertEquals(300.0f, hsv.h, 0.01f);
        assertEquals(1.0f, hsv.s, 0.01f);
        assertEquals(1.0f, hsv.v, 0.01f);
        assertEquals(rgb, hsv.toRGB());
    }

    @Test
    void colorWheelRotationTest() {
        final var hsvRed = RGB.of(0xAC0000).toHSV();
        final var hsvCyan = new HSV(hsvRed.h + 180.0f, hsvRed.s, hsvRed.v);
        assertEquals(RGB.of(0x00ACAC), hsvCyan.toRGB());
    }
}
