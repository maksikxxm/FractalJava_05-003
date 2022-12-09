package ru.smak.menu;


import ru.smak.data.fileChooser;

import ru.smak.graphics.*;
import ru.smak.gui.GraphicsPanel;

import ru.smak.gui.MainWindow;
import ru.smak.math.fractals.FractalFunctions;
import ru.smak.math.fractals.*;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class InstrumentPanel extends JToolBar {
    private JToolBar toolBar;
    private JCheckBox dynamicStep;
    private JComboBox fractal;
    private JComboBox color;
    private JButton movie;
    private GraphicsPanel mainPanel;
    private Fractal currentFractal = new MandelbrotX2();
    private Colorizer currentColorizer = new ColorFunctionDark();
    public InstrumentPanel(JToolBar tool, MainWindow mainWindow){
        this.mainPanel = mainWindow.getMainPanel();
        toolBar = tool;
        toolBar.setRollover(true);
        dynamicStep = new JCheckBox("Динамический шаг", false);
        dynamicStep.setBorderPaintedFlat(true);
        dynamicStep.setFocusable(false);
        toolBar.add(dynamicStep);
        toolBar.addSeparator();
        movie = new JButton("Запись");
        movie.setFocusable(false);

        fractal = new JComboBox();
        var fractalFunctions = FractalFunctions.values();
        for(int i =0;i <fractalFunctions.length;i++)
            fractal.addItem(fractalFunctions[i].toString());

        fractal.setFocusable(false);
        toolBar.add(fractal);
        toolBar.addSeparator();

        color = new JComboBox();
        var colorizers = Colorizers.values();
        for(int i =0;i <colorizers.length;i++)
            color.addItem(colorizers[i].toString());


        color.setFocusable(false);

        toolBar.add(color);
        toolBar.addSeparator();
        toolBar.add(movie);
        toolBar.setFloatable(false);//перемещение панели инструментов

        dynamicStep.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //динамическое изменение числа итераций
            }
        });
        color.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //передача различных цветовых схем
                mainPanel.removePaintersByType("class ru.smak.graphics.FractalPainter");
                Plane plane = mainWindow.getPlane();
                switch (color.getSelectedIndex()) {
                    case 0 -> currentColorizer = new ColorFunctionDark();
                    case 1 -> currentColorizer = new ColorFunctionBlack();
                }
                fileChooser.CurrentColorI = color.getSelectedIndex();
                mainPanel.addPainter(new  FractalPainter(plane, currentFractal, currentColorizer));
                mainPanel.repaint();
            }
        });

        fractal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //передача различных функций
                mainPanel.removePaintersByType("class ru.smak.graphics.FractalPainter");
                Plane plane = mainWindow.getPlane();
                switch (fractal.getSelectedIndex()) {
                    case 0 -> currentFractal = new MandelbrotX2();
                    case 1 -> currentFractal = new MandelbrotX3();
                }
                fileChooser.MandelbrotXi = fractal.getSelectedIndex();
                mainPanel.addPainter(new  FractalPainter(plane, currentFractal, currentColorizer));
                mainPanel.repaint();
            }
        });
        movie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //анимированное плавное перемещение по фракталу
//                var movieWnd = new MovieWindow();
//                movieWnd.setVisible(true);
            }
        });
    }
}
