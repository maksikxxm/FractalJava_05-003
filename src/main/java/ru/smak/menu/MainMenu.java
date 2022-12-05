package ru.smak.menu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame {
    private JMenuBar menuBar;

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
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //сохранение в собственном формате
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
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //отмена операции
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
}
