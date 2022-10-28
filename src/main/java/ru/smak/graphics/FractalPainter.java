package ru.smak.graphics;

import org.jetbrains.annotations.NotNull;
import ru.smak.gui.Painter;
import ru.smak.math.Complex;
import ru.smak.math.fractals.Fractal;

import java.awt.*;

public class FractalPainter implements Painter {

    private Plane plane;
    private Fractal f;

    public FractalPainter(Plane p, Fractal f){
        plane = p;
        this.f = f;
    }

    @Override
    public int getHeight() {
        return plane.getHeight();
    }

    @Override
    public void setHeight(int h) {
        plane.setHeight(h);
    }

    @Override
    public int getWidth() {
        return plane.getWidth();
    }

    @Override
    public void setWidth(int w) {
        plane.setWidth(w);
    }

    @Override
    public void paint(@NotNull Graphics g) {
        g.setColor(Color.BLACK);
        for (int i = 0; i < getWidth(); i++){
            for (int j = 0; j < getHeight(); j++){
                var x = Converter.INSTANCE.xScrToCrt(i, plane);
                var y = Converter.INSTANCE.yScrToCrt(j, plane);
                var r = f.isInSet(new Complex(x, y));
                if (r){
                    g.drawLine(i, j, i+1, j);
                }
            }
        }
    }
}
