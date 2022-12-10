package ru.smak.movie;

import ru.smak.graphics.Colorizer;
import ru.smak.graphics.FractalPainter;
import ru.smak.graphics.Plane;
import ru.smak.math.fractals.Fractal;


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
    int N,width,height;//общее количество добавляемых кадров, ширина и высота
    double K;//суммарное изменение фрактала(его площади)
    /**
     * Массив кадров, передаваемых пользователем.
     */
    private ArrayList<FractalPainter> keyFrames;//массив ключевых кадров
    private Colorizer color;
    private Fractal fractal;
    /**
     * Массив кадров, передаваемых пользователем.
     */
    private ArrayList<FractalPainter> keyFrames;//массив ключевых кадров
    private ArrayList<Double> coefficients;//массив коэффициентов
    private ArrayList<Integer> countOfFrames;//массив количества добавляемых кадров между соседними ключевыми кадрами

    private ArrayList<FractalPainter> frames;//массив всех кадров

    public MovieMaker(ArrayList<FractalPainter> keyFrames, int time, int fps){
        this.frames = new ArrayList<>();
        this.keyFrames = keyFrames;
        this.coefficients = getCoefficients();
        this.countOfFrames = getCountOfFrames();
        this.time = time;
        this.fps = fps;
        this.color = keyFrames.get(0).getColorFunc();
        this.fractal = keyFrames.get(0).getFractal();
        this.width = keyFrames.get(0).getWidth();
        this.height = keyFrames.get(0).getHeight();
        this.N = numberOfFrames();
        this.K = sumCoeff();
        System.out.println(keyFrames.size());
        System.out.println(coefficients.size());
        System.out.println(coefficients.get(0));
        System.out.println(countOfFrames.size());
        System.out.println(countOfFrames.get(0));
        System.out.println(frames.size());
    }

    public void create(){
        for (int i = 0; i < keyFrames.size()-1; i++){
            frames.add(keyFrames.get(i));
            int k = 0;
            double deltaXMin = keyFrames.get(i+1).getPlane().getXMin() - keyFrames.get(i).getPlane().getXMin();
            double deltaXMax = keyFrames.get(i+1).getPlane().getXMax() - keyFrames.get(i).getPlane().getXMax();
            double deltaYMin = keyFrames.get(i+1).getPlane().getYMin() - keyFrames.get(i).getPlane().getYMin();
            double deltaYMax = keyFrames.get(i+1).getPlane().getYMax() - keyFrames.get(i).getPlane().getYMax();
            for (int j = 0; j < countOfFrames.get(i); j++){
                //добавляем кадры
                double xMin = keyFrames.get(i).getPlane().getXMin() + k*deltaXMin/countOfFrames.get(i);
                double xMax = keyFrames.get(i+1).getPlane().getXMax() - k*deltaXMax/countOfFrames.get(i);
                double yMin = keyFrames.get(i).getPlane().getYMin() + k*deltaYMin/countOfFrames.get(i);
                double yMax = keyFrames.get(i+1).getPlane().getYMax() - k*deltaYMax/countOfFrames.get(i);
                k++;
                fractal = keyFrames.get(i).getFractal();
                color = keyFrames.get(i).getColorFunc();
                Plane p = new Plane(xMin, xMax, yMin, yMax, width, height);
                frames.add(new FractalPainter(p,fractal, color));
            }
        }
    }

    //метод, который возвращает коэффициент - во сколько раз изменилась плоскость
    public double getCoeff(FractalPainter p1, FractalPainter p2){
        return Math.abs((p1.getPlane().getXMax()-p1.getPlane().getXMin())*(p1.getPlane().getYMax()-p1.getPlane().getYMin())
                -(p2.getPlane().getXMax()-p2.getPlane().getXMin())*(p2.getPlane().getYMax()-p2.getPlane().getYMin()));
    }
    //массив коэффициентов
    public ArrayList<Double> getCoefficients(){
        coefficients = new ArrayList<>();
        for (int i = 0; i < keyFrames.size()-1; i++){
            coefficients.add(getCoeff(keyFrames.get(i), keyFrames.get(i+1)));
        }
        return coefficients;
    }
    //сумма коэффициентов К
    public double sumCoeff(){
        double res = 0;
        for (int i = 0; i < coefficients.size()-1; i++){
            res += coefficients.get(i);
        }
        return res;
    }

    //массив количества кадров
    public ArrayList<Integer> getCountOfFrames(){
        countOfFrames = new ArrayList<>();
        for(int i = 0; i < keyFrames.size()-1; i++){
            countOfFrames.add((int)(coefficients.get(i)*N/K));
        }
        return countOfFrames;
    }
    public int numberOfFrames(){
        return fps*time-keyFrames.size();
    }//общее число добавляемых кадров
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



    public void show(){

    }
}
