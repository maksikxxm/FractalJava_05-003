package ru.smak.movie;

import javax.swing.*;
import java.awt.*;

public class MovieWindow extends JFrame {
    private final Dimension minSz = new Dimension(500, 400);
    private JButton SelectFile, FPS, Duration, OK, Play;
    public MovieWindow(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(minSz);
        SelectFile = new JButton("Выбрать файл");
        FPS = new JButton("FPS");
        Duration = new JButton("Длительность");
        OK = new JButton("OK");
        Play = new JButton("Play");
    }
}
