package ru.smak.movie;

import ru.smak.graphics.FractalPainter;
import ru.smak.graphics.Plane;
import ru.smak.gui.GraphicsPanel;
import ru.smak.gui.MainWindow;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class MovieWindow extends JFrame {
    //private GraphicsPanel moviePanel = new GraphicsPanel();
    private JPanel controlPanel;
    private GroupLayout gl;
    private GroupLayout glcp;
    private final Dimension minSz = new Dimension(600, 500);
    private JButton AddFile, OK, Play;
    private JSpinner FPS, Duration;
    private JLabel FPSlbl, Durationlbl;
    private ArrayList<FractalPainter> frames;
    private JPanel container;
    private int fps, duration;
    private MovieMaker movie;
    public MovieWindow(MainWindow mainWindow){
        super("GridLayoutTest");
        container = new JPanel();
        GridLayout layout = new GridLayout(2,0,5,12);
        container.setBackground(Color.WHITE);
        container.setLayout(layout);
        //container.setLayout(new FlowLayout(FlowLayout.CENTER));
        controlPanel = new JPanel();
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setMinimumSize(minSz);
        //moviePanel.setBackground(Color.WHITE);
        GroupLayout gl = new GroupLayout(getContentPane());
        GroupLayout glcp = new GroupLayout(controlPanel);
        SpinnerNumberModel mdlFPS = new SpinnerNumberModel(30, 1, 1000, 1);
        SpinnerNumberModel mdlDuration = new SpinnerNumberModel(30, 5, 180, 1);
        AddFile = new JButton("Добавить файл");
        FPS = new JSpinner(mdlFPS);
        Duration = new JSpinner(mdlDuration);
        FPSlbl = new JLabel("FPS");
        Durationlbl = new JLabel("Duration");
        OK = new JButton("OK");
        Play = new JButton("Play");
        setLayout(gl);
        controlPanel.setBackground(Color.WHITE);
        controlPanel.setLayout(glcp);
        movie = new MovieMaker(frames, duration, fps);
        frames = new ArrayList<FractalPainter>();

        AddFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //добавление файла в список ключевых файлов
                frames.add((FractalPainter)mainWindow.getMainPanel().getAllPainters("class ru.smak.graphics.FractalPainter").get(0));
                GraphicsPanel moviePanel = new GraphicsPanel();
                moviePanel.setBackground(Color.WHITE);
                FractalPainter fp = new FractalPainter((FractalPainter)mainWindow.getMainPanel().getAllPainters("class ru.smak.graphics.FractalPainter").get(0));
                moviePanel.addPainter(fp);
                //moviePanel.repaint();
                container.add(moviePanel);
                container.revalidate();
            }
        });

        OK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //видео начинает создаваться
                movie.create();
            }
        });

        Play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //видео запускается (кнопка доступна только после того, как видео создано)
                movie.show();
            }
        });

        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addGap(8)
                .addGroup(gl.createParallelGroup()
                        .addComponent(container, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                        .addComponent(controlPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                )
                .addGap(8)
        );

        gl.setVerticalGroup(gl.createSequentialGroup()
                .addGap(8)
                .addComponent(container, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                .addGap(8)
                .addComponent(controlPanel, 70,70,70)
                .addGap(8)
        );

        glcp.setHorizontalGroup(glcp.createSequentialGroup()
                .addGap(8)
                .addComponent(AddFile, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                .addGap(8)
                .addComponent(FPSlbl, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                .addGap(8)
                .addComponent(FPS, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                .addGap(8)
                .addComponent(Durationlbl, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                .addGap(8)
                .addComponent(Duration, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                .addGap(8)
                .addComponent(OK, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                .addGap(8)
                .addComponent(Play, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                .addGap(8)
        );

        glcp.setVerticalGroup(glcp.createSequentialGroup()
                .addGap(8)
                .addGroup(glcp.createParallelGroup()
                        .addComponent(AddFile, GroupLayout.Alignment.CENTER)
                        .addGap(8)
                        .addComponent(FPSlbl, GroupLayout.Alignment.CENTER)
                        .addGap(8)
                        .addComponent(FPS, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                        .addGap(8)
                        .addComponent(Durationlbl, GroupLayout.Alignment.CENTER)
                        .addGap(8)
                        .addComponent(Duration, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                        .addGap(8)
                        .addComponent(OK, GroupLayout.Alignment.CENTER)
                        .addGap(8)
                        .addComponent(Play, GroupLayout.Alignment.CENTER)
                        .addGap(8)
                )
                .addGap(8)
        );
    }
}
