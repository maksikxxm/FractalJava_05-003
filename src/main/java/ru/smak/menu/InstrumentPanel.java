package ru.smak.menu;

import ru.smak.gui.MainWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InstrumentPanel extends JToolBar {
    private JToolBar toolBar;
    private JCheckBox dynamicStep;
    private JComboBox fractal;
    private JComboBox color;
    private JButton movie;

    public InstrumentPanel(JToolBar tool){
        toolBar = tool;
        toolBar.setRollover(true);
        dynamicStep = new JCheckBox("Динамический шаг", false);
        dynamicStep.setBorderPaintedFlat(true);
        dynamicStep.setFocusable(false);
        toolBar.add(dynamicStep);
        toolBar.addSeparator();
        movie = new JButton("Запись");
        movie.setFocusable(false);
        fractal = new JComboBox(new String[]{"x^2","x^3","x^4","x^5"});
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
            }
        });
        movie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //анимированное плавное перемещение по фракталу
            }
        });
    }

    public boolean getDynamicStepStatus(){
        return dynamicStep.isSelected();
    }
}
