package ru.smak.movie;

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
    /**
     * Массив кадров, передаваемых пользователем.
     */
    ArrayList<File> frames;
    public MovieMaker(ArrayList<File> frames, int time, int fps){

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

    /**
     * Метод, который возвращает общее количество кадров.
     * @param fps
     * @param time
     * @return
     */
    public int CountOfFrames(int fps, int time){
        return fps*time;
    }

    public void create(){

    }

    public void show(){

    }
}
