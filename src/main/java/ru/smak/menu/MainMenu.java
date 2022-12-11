package ru.smak.menu;

import javax.swing.*;
import java.awt.event.*;
import java.net.URL;

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
        //save.setIcon(new ImageIcon(getClass().getResource("/icons/save.png")));
        save.setIcon(new ImageIcon("icons/save.png"));
        saveAs.setIcon(new ImageIcon("icons/saveAs.png"));
        open.setIcon(new ImageIcon("icons/open.png"));
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
        edit.setIcon(createIcon("icons/edit.png"));
        cancel.setIcon(createIcon("icons/cancel.png"));
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
