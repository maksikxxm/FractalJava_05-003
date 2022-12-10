package ru.smak.data;

import ru.smak.graphics.ColorFunctionDark;
import ru.smak.graphics.Plane;
import ru.smak.gui.GraphicsPanel;
import ru.smak.math.fractals.Mandelbrot;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class fileChooserSave
{
    public static int MandelbrotXi;
    public static int CurrentColorI;
    private Plane PlaneSave;
    private Mandelbrot MandelbrotSave;
    private ColorFunctionDark ColorSave;
    private final JFileChooser FileChooser = new JFileChooser();
    private final GraphicsPanel graphicsPanel;
    private  String path;
    private  dataPut dataPut = new dataPut();

    public fileChooserSave(GraphicsPanel graphicsPanel)
    {
        FileChooser.setDialogTitle("Сохранение файла");
        // Определение режима - только файл
        FileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        // Фильтр
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JSON", "JSON");
        FileChooser.setFileFilter(filter);
        this.graphicsPanel = graphicsPanel;
    }

    public void SaveFile()
    {

        int result = FileChooser.showSaveDialog(graphicsPanel);
        System.out.println(result);
        File fileSave = FileChooser.getSelectedFile();
        if(fileSave == null) {return;}
        path = fileSave.getPath();
        dataPut.getPath(path);
        System.out.println(MandelbrotXi + "Path");
        dataPut.put(PlaneSave,MandelbrotSave,ColorSave,MandelbrotXi,CurrentColorI); // Никитино
        // Если файл выбран, то представим его в сообщении
        if (result == JFileChooser.APPROVE_OPTION )
            JOptionPane.showMessageDialog(graphicsPanel,
                    "Файл (" + FileChooser.getSelectedFile() +
                            " ) сохранен");

    }
    public void setDataPut(Plane PlaneSave, Mandelbrot MandelbrotSave, ColorFunctionDark ColorSave)
    {
        this.PlaneSave = PlaneSave;
        this.MandelbrotSave = MandelbrotSave;
        this.ColorSave = ColorSave;
    }
}