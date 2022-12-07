package ru.smak.movie;

import ru.smak.gui.GraphicsPanel;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class MovieWindow extends JFrame {
    private JPanel moviePanel;
    private JPanel controlPanel;
    private GroupLayout gl;
    private GroupLayout glcp;
    private final Dimension minSz = new Dimension(600, 500);
    private JButton SelectFile, OK, Play;
    private JSpinner FPS, Duration;
    private JLabel FPSlbl, Durationlbl;
    private ArrayList<File> files;
    private int fps, duration;
    public MovieWindow(){
        moviePanel = new JPanel();
        controlPanel = new JPanel();
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setMinimumSize(minSz);
        moviePanel.setBackground(Color.WHITE);
        GroupLayout gl = new GroupLayout(getContentPane());
        GroupLayout glcp = new GroupLayout(controlPanel);
        SpinnerNumberModel mdlFPS = new SpinnerNumberModel(30, 1, 1000, 1);
        SpinnerNumberModel mdlDuration = new SpinnerNumberModel(30, 5, 180, 1);
        SelectFile = new JButton("Выбрать файл");
        FPS = new JSpinner(mdlFPS);
        Duration = new JSpinner(mdlDuration);
        FPSlbl = new JLabel("FPS");
        Durationlbl = new JLabel("Duration");
        OK = new JButton("OK");
        Play = new JButton("Play");
        setLayout(gl);
        controlPanel.setBackground(Color.WHITE);
        controlPanel.setLayout(glcp);


        files = new ArrayList<>();

        SelectFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //открывается диалоговое окно с выбором файла
                var a = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                        "JSON Images", "json");
                a.setFileFilter(filter);
                a.showOpenDialog(null);
                files.add(a.getSelectedFile());
            }
        });

        FPS.addChangeListener(e -> {

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

        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addGap(8)
                .addGroup(gl.createParallelGroup()
                        .addComponent(moviePanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                        .addComponent(controlPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                )
                .addGap(8)
        );

        gl.setVerticalGroup(gl.createSequentialGroup()
                .addGap(8)
                .addComponent(moviePanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                .addGap(8)
                .addComponent(controlPanel, 70,70,70)
                .addGap(8)
        );

        glcp.setHorizontalGroup(glcp.createSequentialGroup()
                .addGap(8)
                .addComponent(SelectFile, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
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
                        .addComponent(SelectFile, GroupLayout.Alignment.CENTER)
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
