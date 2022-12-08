package ru.smak.menu;

import ru.smak.graphics.ColorFunction;
import ru.smak.graphics.FractalPainter;
import ru.smak.graphics.Plane;
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
        var a = FractalFunctions.values();
        for(int i =0;i <a.length;i++)
            fractal.addItem(a[i].toString());
        fractal.setFocusable(false);
        toolBar.add(fractal);
        toolBar.addSeparator();
        color = new JComboBox(new String[]{"Красный", "Зеленый", "Синий", "Желтый"});
        color.setFocusable(false);
        color.setSelectedItem(color.getItemAt(1));
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
            }
        });

        fractal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //передача различных функций
                mainPanel.removePaintersByType("class ru.smak.graphics.FractalPainter");
                Plane plane = mainWindow.getPlane();
                var colorFunc = new ColorFunction();
                switch (fractal.getSelectedIndex())
                {
                    case 0:
                        MandelbrotX2 mX2 = new MandelbrotX2();
                        FractalPainter mx2 = new  FractalPainter(plane, mX2, colorFunc);

                        mainPanel.addPainter(mx2);
                        mainPanel.repaint();
                        break;
                    case 1:
                        MandelbrotX3 mX3 = new MandelbrotX3();
                        FractalPainter mx3 = new  FractalPainter(plane, mX3, colorFunc);

                        mainPanel.addPainter(mx3);
                        mainPanel.repaint();
                        break;
                }

            }
        });
        movie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //анимированное плавное перемещение по фракталу
            }
        });
    }
}
