package ru.smak.math;

import org.jetbrains.annotations.NotNull;

public class Complex{
    private double re, im;
    public double getRe(){
        return re;
    }
    public double getIm(){
        return im;
    }
    public void setRe(double re){
        this.re = re;
    }
    public void setIm(double im){
        this.im = im;
    }
    public Complex(double r, double i){
        re = r;
        im = i;
    }
    public Complex(Complex other){
        re = other.re;
        im = other.im;
    }
    public Complex(double r){
        re = r;
        im = 0.0;
    }
    public Complex plus(@NotNull Complex other){
        return new Complex(re + other.re, im + other.im);
    }
    public Complex minus(@NotNull Complex other){
        return new Complex(re - other.re, im - other.im);
    }
    public Complex times(@NotNull Complex other){
        return new Complex(
                re * other.re - im * other.im,
                re * other.im + im * other.re
                );
    }
    public Complex div(@NotNull Complex other){
        double den = other.abs2();
        return new Complex(
                (re * other.re + im * other.im) / den,
                (re * other.im - im * other.re) / den
        );
    }
    public double abs(){
        return Math.sqrt(re * re + im * im);
    }
    public double abs2(){
        return re * re + im * im;
    }
}
