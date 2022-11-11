package ru.smak.gui;

import ru.smak.graphics.ColorFunction1;
import ru.smak.graphics.FractalPainter;
import ru.smak.graphics.Plane;
import ru.smak.math.fractals.Mandelbrot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class MainWindow extends JFrame {
    private final GraphicsPanel mainPanel = new GraphicsPanel();
    private final Plane p;
    private static final int GROW = GroupLayout.DEFAULT_SIZE;
    private static final int SHRINK = GroupLayout.PREFERRED_SIZE;
    private final Dimension minSz = new Dimension(600, 500);
    public MainWindow(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(minSz);
        Mandelbrot m = new Mandelbrot();
        p = new Plane(-2.0, 1.0, -1.0, 1.0, 0, 0);
        var colorFunc = new ColorFunction1();
        FractalPainter fp = new FractalPainter(p, m, colorFunc);
        mainPanel.setBackground(Color.WHITE);
        mainPanel.addPainter(fp, Priority.FRONT);
        mainPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                p.setWidth(mainPanel.getWidth());
                p.setHeight(mainPanel.getHeight());
            }
        });
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
