package ru.smak.movie;

import ru.smak.gui.GraphicsPanel;

import javax.swing.*;
import java.awt.*;

public class MovieWindow extends JFrame {
    private final GraphicsPanel moviePanel = new GraphicsPanel();
    private JPanel controlPanel;
    private final Dimension minSz = new Dimension(500, 400);
    private JButton SelectFile, OK, Play;
    private JSpinner FPS, Duration;
    private JLabel FPSlbl, Durationlbl;
    public MovieWindow(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(minSz);
        moviePanel.setBackground(Color.WHITE);
        GroupLayout gl = new GroupLayout(getContentPane());

        SpinnerNumberModel mdlFPS = new SpinnerNumberModel(30, 1, 1000, 1);
        SpinnerNumberModel mdlDuration = new SpinnerNumberModel(30, 5, 180, 1);
        SelectFile = new JButton("Выбрать файл");
        FPS = new JSpinner(mdlFPS);
        Duration = new JSpinner(mdlDuration);
        FPSlbl = new JLabel("FPS");
        Durationlbl = new JLabel("Duration");
        OK = new JButton("OK");
        Play = new JButton("Play");

    }
}
