package ru.smak.gui;

import ru.smak.graphics.FractalPainter;
import ru.smak.graphics.Plane;
import ru.smak.math.fractals.Mandelbrot;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private GraphicsPanel mainPanel = new GraphicsPanel();
    private Plane p;
    private final int GROW = GroupLayout.DEFAULT_SIZE;
    private final int SHRINK = GroupLayout.PREFERRED_SIZE;
    private final Dimension minSz = new Dimension(600, 500);
    public MainWindow(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(minSz);
        Mandelbrot m = new Mandelbrot();
        p = new Plane(-2.0, 1.0, -1.0, 1.0, 0, 0);
        FractalPainter fp = new FractalPainter(p, m);
        mainPanel.setBackground(Color.WHITE);
        mainPanel.addPainter(fp, Priority.FRONT);
        GroupLayout gl = new GroupLayout(getContentPane());
        gl.setHorizontalGroup(
                gl.createSequentialGroup()
                        .addGap(8)
                        .addComponent(mainPanel,GROW, GROW, GROW)
                        .addGap(8)
        );
        gl.setVerticalGroup(
                gl.createSequentialGroup()
                        .addGap(8)
                        .addComponent(mainPanel, GROW, GROW, GROW)
                        .addGap(8)
        );
        setLayout(gl);
    }

    @Override
    public void setVisible(boolean v){
        super.setVisible(v);
        p.setWidth(mainPanel.getWidth());
        p.setHeight(mainPanel.getHeight());
    }
}
