package ru.smak.movie;

import ru.smak.graphics.Colorizer;
import ru.smak.graphics.FractalPainter;
import ru.smak.graphics.Plane;
import ru.smak.math.fractals.Fractal;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public class MovieMaker {
    /**
     * Длительность видео в секундах.
     */
    int time;
    /**
     * Количество сменяемых кадров за одну секунду.
     */
    int fps;
    int N;//общее количество добавляемых кадров
    double K;//суммарное изменение фрактала(его площади)
    /**
     * Массив кадров, передаваемых пользователем.
     */
    private ArrayList<FractalPainter> keyFrames;
    private Colorizer color;
    private Fractal fractal;
    private ArrayList<Double> coefficients;

    ArrayList<FractalPainter> frames;

    public MovieMaker(ArrayList<FractalPainter> keyFrames, int time, int fps){
        this.keyFrames = keyFrames;
        this.coefficients = coefficients();
        this.time = time;
        this.fps = fps;
        N = numberOfFrames();
        K = sumCoeff();
    }

    public void create(){
        for (FractalPainter keyFrame : keyFrames){
            frames.add(keyFrame);
            frames.add();
        }
    }

    //метод, который возвращает коэффициент - во сколько раз изменилась плоскость
    public double getCoeff(FractalPainter p1, FractalPainter p2){
        return (p1.getPlane().getXMax()-p2.getPlane().getXMax())*(p1.getPlane().getYMax()-p2.getPlane().getYMax());
    }
    //массив коэффициентов
    public ArrayList<Double> coefficients(){
        for (int i = 0; i < keyFrames.size()-1; i++){
            coefficients().add(getCoeff(keyFrames.get(i), keyFrames.get(i+1)));
        }
        return coefficients();
    }

    public double sumCoeff(){
        double res = 0;
        for (int i = 0; i < coefficients.size(); i++){
            res +=coefficients.get(i);
        }
        return res;
    }
    //массив количества кадров
    public ArrayList<Integer> countOfFrames(){

        for(int i = 0; i < keyFrames.size(); i++){
            countOfFrames().add((int)(coefficients.get(i)*N/K));
        }
        return countOfFrames();
    }
    public int getFps() {
        return fps;
    }
    public void setFps(int fps){
        this.fps = fps;
    }

    public int getTime() {
        return time;
    }
    public void setTime(int time){
        this.time = time;
    }

    public int numberOfFrames(){
        return fps*time-keyFrames.size();
    }

    public void show(){

    }
}
