package ru.smak.graphics;

import java.awt.*;

/**
 * По умолчанию фрактал красного цвета.
 * r = (float)(Math.abs(Math.cos(30*value)));
 * g = (float)(Math.abs(Math.sin(45*value)));
 * b = (float)(1 - 0.5*(Math.abs(Math.cos(12*value)) + Math.abs(Math.sin(28*value))));
 */
public class ColorFunctionRed implements Colorizer{

    @Override
    public Color getColor(float value) {
        if (value == 1.0) return Color.RED;
        var r = (float)(Math.abs(Math.cos(30*value)));
        var g = (float)(Math.abs(Math.sin(45*value)));
        var b = (float)(1 - 0.5*(Math.abs(Math.cos(12*value)) + Math.abs(Math.sin(28*value))));
        return new Color(r, g, b);
    }

    @Override
    public Color getColor(float value, Color fractalColor) {
        if (value == 1.0) return fractalColor;
        var r = (float)(Math.abs(Math.cos(30*value)));
        var g = (float)(Math.abs(Math.sin(45*value)));
        var b = (float)(1 - 0.5*(Math.abs(Math.cos(12*value)) + Math.abs(Math.sin(28*value))));
        return new Color(r, g, b);
    }
}
