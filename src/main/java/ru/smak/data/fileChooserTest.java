package ru.smak.data;

import ru.smak.gui.GraphicsPanel;

import javax.swing.*;
import java.io.File;

public class fileChooserTest
{
    private final JFileChooser FileChooser = new JFileChooser();
    private GraphicsPanel graphicsPanel = new GraphicsPanel();
    private String path;
    public void SaveFile()
    {
        FileChooser.setDialogTitle("Сохранение файла");
        // Определение режима - только файл
        FileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result = FileChooser.showSaveDialog(graphicsPanel);
        File file = FileChooser.getSelectedFile();
        path = file.getPath();
        System.out.println(path);
        // Если файл выбран, то представим его в сообщении
        if (result == JFileChooser.APPROVE_OPTION )
            JOptionPane.showMessageDialog(graphicsPanel,
                    "Файл (" + FileChooser.getSelectedFile() +
                            " ) сохранен");

    }

    public void getGraphics(GraphicsPanel graphicsPanel)
    {
        this.graphicsPanel = graphicsPanel;
    }
}
