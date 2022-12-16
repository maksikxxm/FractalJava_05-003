package ru.smak.math.fractals;

import ru.smak.math.Complex;

public class Julia extends Mandelbrot{

    private Complex z0;
    public Julia(Complex z){z0 = z;}
    @Override
    public float isInSet(Complex c) {
        Complex z = new Complex(c);
        int cnt = 0;
        var r2 = this.getR() * this.getR();
        while (++cnt < this.getMaxIterations()) {
            z = z.times(z).plus(z0);
            if (z.abs2() >= r2) break;
        }
        return (float)cnt / this.getMaxIterations();
    }
}
