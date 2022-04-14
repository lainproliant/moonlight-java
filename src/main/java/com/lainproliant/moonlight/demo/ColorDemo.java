package com.lainproliant.moonlight.demo;

import com.lainproliant.moonlight.color.HSV;
import com.lainproliant.moonlight.color.RGB;

import static com.diogonunes.jcolor.Ansi.colorize;

public class ColorDemo {
    public static void main(final String[] argv) {
        var hsv = new RGB(255, 0, 0).toHSV();

        for (int v = 1; v <= 4; v++) {
            for (int s = 1; s <= 4; s++) {
                for (int h = 0; h < 80; h++) {
                    System.out.print(colorize(" ", new HSV(
                            360.0f * ((float) h) / 80.0f,
                            ((float) s) / 4.0f,
                            ((float) v) / 4.0f).toAnsiBack()));
                }
                System.out.println();
            }
        }
    }
}
