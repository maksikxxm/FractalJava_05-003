package ru.smak.movie;

import ru.smak.gui.GraphicsPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class MovieWindow extends JFrame {
    private final GraphicsPanel moviePanel = new GraphicsPanel();
    private JPanel controlPanel;
    private final Dimension minSz = new Dimension(500, 400);
    private JButton SelectFile, OK, Play;
    private JSpinner FPS, Duration;
    private JLabel FPSlbl, Durationlbl;
    private ArrayList<File> files;
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

        files = new ArrayList<>();

        SelectFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //открывается диалоговое окно с выбором файла
                var a = new JFileChooser();
                files.add(a.getSelectedFile());
            }
        });

        OK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //видео начинает создаваться
            }
        });

        Play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //видео запускается (кнопка доступна только после того, как видео создано)
            }
        });
    }
}
