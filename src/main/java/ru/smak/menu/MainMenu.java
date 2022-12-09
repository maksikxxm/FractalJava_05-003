package ru.smak.menu;

import ru.smak.data.fileChooserOpen;
import ru.smak.data.fileChooserSave;
import ru.smak.graphics.ColorFunctionDark;
import ru.smak.data.fileChooser;
import ru.smak.graphics.ColorFunctionBlack;
import ru.smak.graphics.ColorFunctionDark;
import ru.smak.graphics.Colorizers;
import ru.smak.graphics.Plane;
import ru.smak.gui.GraphicsPanel;
import ru.smak.gui.MainWindow;
import ru.smak.math.fractals.Mandelbrot;
import ru.smak.math.fractals.MandelbrotX2;
import ru.smak.math.fractals.MandelbrotX3;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainMenu extends JFrame {
    private JMenuBar menuBar;
    private GraphicsPanel mainPanel;
    private Plane PlaneSave;
    private MainWindow window;
    private Mandelbrot MandelbrotSave;
    private ColorFunctionDark ColorSave;
    private MandelbrotX2 MandelbrotSave;
    private ColorFunctionDark ColorSave;

    public MainMenu(JMenuBar m) {
        menuBar = m;
        menuBar.add(createFileMenu());
        menuBar.add(createEditMenu());
        menuBar.add(createHelpMenu());
    }

    public JMenu createFileMenu() {
        JMenu file = new JMenu("Файл");
        JMenuItem save = new JMenuItem("Сохранить");//сохранить как собственный формат файла
        JMenuItem saveAs = new JMenuItem("Сохранить как");//сохранить как jpg
        JMenuItem open = new JMenuItem("Открыть");//открыть как собственный формат файла (загрузить из файла)
        file.add(save);
        file.add(saveAs);
        file.add(open);
        fileChooserSave fileChooserSave = new fileChooserSave(mainPanel);// Никитино
        fileChooserOpen fileChooserOpen = new fileChooserOpen();
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                //Вызов окошка сохранения файла(пока что без формата)
                fileChooserSave.setDataPut(PlaneSave,MandelbrotSave,ColorSave);
                fileChooserSave.SaveFile();
            }
        });
        saveAs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //сохранение в формате картинки jpg
            }
        });
        open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileChooserOpen.WindowOpen(window);
                fileChooserOpen.Panel(mainPanel);
                fileChooserOpen.OpenFile();

            }
        });
        return file;
    }

    public JMenu createEditMenu() {
        JMenu edit = new JMenu("Правка");
        JMenuItem cancel = new JMenuItem("Отмена");
        edit.add(cancel);
        //отмена операции
        cancel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
        return edit;
    }

    public JMenu createHelpMenu() {
        JMenu help = new JMenu("О программе");
        help.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //вывод текста о программе
            }
        });
        return help;
    }
    public void setMainPanel(GraphicsPanel mainPanel) // Передача mainPanel(Никита)
    {
        this.mainPanel = mainPanel;
    }
    public void setDataPutMainMenu(Plane PlaneSave, Mandelbrot MandelbrotSave, ColorFunctionDark ColorSave)
    {
        this.PlaneSave = PlaneSave;
        this.MandelbrotSave = MandelbrotSave;
        this.ColorSave = ColorSave;

    }
    public void setWindow(MainWindow window)
    {
        this.window= window;
    }

}