package ru.smak.menu;


import ru.smak.gui.GraphicsPanel;
import ru.smak.gui.MainWindow;
import ru.smak.gui.UndoRedoManager;

import ru.smak.data.fileChooserOpen;
import ru.smak.data.fileChooserSave;
import ru.smak.graphics.ColorFunctionDark;
import ru.smak.graphics.ColorFunctionDark;
import ru.smak.graphics.Colorizers;
import ru.smak.graphics.Plane;
import ru.smak.gui.GraphicsPanel;
import ru.smak.gui.MainWindow;
import ru.smak.math.fractals.Mandelbrot;
import ru.smak.math.fractals.MandelbrotX2;
import ru.smak.math.fractals.MandelbrotX3;


import javax.swing.*;
import java.awt.event.*;

import java.net.URL;


public class MainMenu extends JFrame {
    private JMenuBar menuBar;
    private GraphicsPanel mainPanel;

    private UndoRedoManager undoRedoManager;
    private Plane PlaneSave;
    private MainWindow window;
    private Mandelbrot MandelbrotSave;
    private ColorFunctionDark ColorSave;
    public MainMenu(JMenuBar m, MainWindow mainWindow) {
        this.mainPanel = mainWindow.getMainPanel();
        this.undoRedoManager = mainWindow.getUndoRedoManager();
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

        //save.setIcon(new ImageIcon(getClass().getResource("/icons/save.png")));
        save.setIcon(new ImageIcon("icons/save.png"));
        saveAs.setIcon(new ImageIcon("icons/saveAs.png"));

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
        JMenuItem undo = new JMenuItem("Отменить (Ctrl + Z)");
        JMenuItem redo = new JMenuItem("Вернуть (Ctrl + Y)");
        edit.add(undo);
        edit.add(redo);
        edit.setIcon(createIcon("icons/edit.png"));
        undo.setIcon(createIcon("icons/cancel.png"));
        undo.addMouseListener(new MouseAdapter() {      //  отмена операции
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                undoRedoManager.undo();
                mainPanel.repaint();
            }
        });
        redo.addMouseListener(new MouseAdapter() {      //  возвращение операции
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                undoRedoManager.redo();
                mainPanel.repaint();
            }
        });
        return edit;
    }

    public JMenu createHelpMenu() {
        JMenu help = new JMenu("О программе");
        help.setIcon(createIcon("icons/about.png"));
        help.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String about =
                        "Над программой работали:\n"
                                + "КФУ ИМиМ группа 05-003\n"
                                    + "Антонов Максим - 5-6 задание\n"
                                    + "Депресов Артём - 7 задание\n"
                                    + "Иванова Инна - 1 задание\n"
                                    + "Игнатьев Вадим - 8 задание\n"
                                    + "Кушаева Алиса - 10 задание\n"
                                    + "Кушаева Камила - 10 задание\n"
                                    + "Марданова Альбина - 2 задание\n"
                                    + "Сошников Никита - 3 задание\n"
                                    + "Филиппов Артур - 4 задание\n"
                                    + "Шумихина Анна - 9 задание";
                JOptionPane.showMessageDialog(null, about);
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
    public void getPlaneSaveMainMenu(Plane planeSave)
    {

      this.PlaneSave= planeSave;
        System.out.println(PlaneSave.getXMax()+"Plane");
    }




    protected static ImageIcon createIcon(String path) {
        URL imgURL = MainMenu.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("File not found " + path);
            return null;
        }
    }
}
