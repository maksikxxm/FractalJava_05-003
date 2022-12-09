package ru.smak.menu;

import ru.smak.data.fileChooser;
import ru.smak.graphics.ColorFunctionBlack;
import ru.smak.graphics.ColorFunctionDark;
import ru.smak.graphics.Colorizers;
import ru.smak.graphics.Plane;
import ru.smak.gui.GraphicsPanel;
import ru.smak.math.fractals.MandelbrotX2;
import ru.smak.math.fractals.MandelbrotX3;

import javax.swing.*;
import java.awt.event.*;

public class MainMenu extends JFrame {
    private JMenuBar menuBar;
    private GraphicsPanel mainPanel;
    private Plane PlaneSave;
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
        fileChooser fileChooser = new fileChooser(mainPanel);// Никитино
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                //сохранение в собственном формате

                fileChooser.setDataPut(PlaneSave,MandelbrotSave,ColorSave);
                fileChooser.SaveFile();

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
                //открыть в собственном формате (загрузить из файла)
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
    public void getMainPanel(GraphicsPanel mainPanel) // Передача mainPanel(Никита)
    {
        this.mainPanel = mainPanel;
    }
    public void setDataPutMainMenu(Plane PlaneSave, MandelbrotX2 MandelbrotSave, ColorFunctionDark ColorSave)
    {
        this.PlaneSave = PlaneSave;
        this.MandelbrotSave = MandelbrotSave;
        this.ColorSave = ColorSave;

    }
    public void getPlaneSaveMainMenu(Plane planeSave)
    {
      this.PlaneSave= planeSave;
    }

}
