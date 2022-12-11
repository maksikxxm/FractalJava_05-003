package ru.smak.menu;

import ru.smak.data.Root;
import ru.smak.data.fileChooserOpen;
import ru.smak.data.fileChooserSave;
import ru.smak.dynamic.MaxIterations;
import ru.smak.graphics.*;
import ru.smak.gui.GraphicsPanel;

import ru.smak.gui.MainWindow;
import ru.smak.math.fractals.FractalFunctions;
import ru.smak.math.fractals.*;
import ru.smak.movie.MovieWindow;


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
        fileChooser fileChooser = new fileChooser(mainPanel);
        fractal = new JComboBox();
        var fractalFunctions = FractalFunctions.values();
        for(int i =0;i <fractalFunctions.length;i++) {


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

        mainPanel.addPainter(new FractalPainter(mainWindow.getPlane(), currentFractal, currentColorizer));
        dynamicStep.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //динамическое изменение числа итераций
                MaxIterations maxIterations =  new MaxIterations(mainWindow);
                fileChooserSave.MaxIterationsSave = maxIterations.getMaxIterationsSave();
                System.out.println(  maxIterations.getMaxIterationsSave() + " fileChooserSave.MaxIterationsSave ");
                mainPanel.repaint();
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
                    case 1 -> currentColorizer = new ColorFunctionGreen();
                    case 2 -> currentColorizer = new ColorFunctionRed();
                }
                fileChooserSave.CurrentColorI = color.getSelectedIndex();
                mainPanel.addPainter(new FractalPainter(plane, currentFractal, currentColorizer));
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
                    case 2 -> currentFractal = new MandelbrotX5();
                    case 3 -> currentFractal = new MandelbrotX10();
                    case 4 -> currentFractal = new MandelbrotSin();
                    case 5 -> currentFractal = new MandelbrotCos();

                }
                fileChooserSave.MandelbrotXi = fractal.getSelectedIndex();
                mainPanel.addPainter(new FractalPainter(plane, currentFractal, currentColorizer));
                mainPanel.repaint();
            }
        });
        movie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //анимированное плавное перемещение по фракталу
                var movieWnd = new MovieWindow();
                movieWnd.setVisible(true);
            }
        });
    }
   public JComboBox getJComboBoxMandelbrot()
   {
        return  fractal;
   }
    public JComboBox getJComboBoxColorizer()
    {
        return  color;
    }
    public Mandelbrot getCurrentFractal(){return (Mandelbrot) currentFractal;}
    public boolean getDynamicStepStatus(){return dynamicStep.isSelected();}
    public JCheckBox getJCheckBoxMaxIterations()
    {
        return dynamicStep;
    }
}
