package ru.smak.menu;

import javax.swing.*;

public class MainMenu extends JFrame {
    private JMenuBar menuBar;
    
    public MainMenu(JMenuBar m){
        menuBar = m;
        menuBar.add(createSaveMenu());
        menuBar.add(createOpenMenu());
    }

    public JMenu createOpenMenu() {
        JMenu save = new JMenu("Сохранить");
        JMenuItem asJPG = new JMenuItem("Как jpg");
        JMenuItem asFormatFile = new JMenuItem("Как собственный формат файла");
        return save;
    }

    public JMenu createSaveMenu() {
        JMenu open = new JMenu("Открыть");
        JMenuItem asFormatFile = new JMenuItem("Как собственный формат файла");
        return open;
    }
}
