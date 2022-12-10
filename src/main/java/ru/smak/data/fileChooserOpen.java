package ru.smak.data;
import ru.smak.gui.GraphicsPanel;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class fileChooserOpen
{
    private final JFileChooser FileChooser = new JFileChooser();
    private final GraphicsPanel graphicsPanel;
    public fileChooserOpen(GraphicsPanel graphicsPanel)
    {
        FileChooser.setDialogTitle("Выберите файл");
        // Определение режима - только файл
        FileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        // Фильтр
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JSON", "JSON");
        FileChooser.setFileFilter(filter);
        this.graphicsPanel = graphicsPanel;
    }

    public void OpenFile()
    {

        int result = FileChooser.showOpenDialog(graphicsPanel);
        File fileOpen = FileChooser.getSelectedFile();
        if(fileOpen == null) {return;}
        dataGet parser = new dataGet();
        Root Root = parser.parser();
        System.out.println("Data= "+ Root.toString());
        // Если файл выбран, то представим его в сообщении
        if (result == JFileChooser.APPROVE_OPTION )
            JOptionPane.showMessageDialog(graphicsPanel,
                    "Файл (" + FileChooser.getSelectedFile() +
                            " ) открыт");

    }
}
