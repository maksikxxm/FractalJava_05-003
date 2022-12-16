package ru.smak.data;

import ru.smak.graphics.ColorFunctionDark;
import ru.smak.graphics.Plane;
import ru.smak.gui.GraphicsPanel;
import ru.smak.math.fractals.Mandelbrot;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.util.ArrayList;

public class fileChooserSave
{
    public static int MandelbrotXi;
    public static int MandelbrotInt;
    public static int CurrentColorI;
    public  static  boolean MaxIterationsSave;
    private Plane PlaneSave;
    ArrayList<String> filenames = new ArrayList<String>();
    private Mandelbrot MandelbrotSave;
    private ColorFunctionDark ColorSave;
    private final JFileChooser FileChooser = new JFileChooser();
    private final GraphicsPanel graphicsPanel;
    private final dataPut dataPut = new dataPut();
    public fileChooserSave(GraphicsPanel graphicsPanel)
    {
        FileChooser.setDialogTitle("Сохранение файла");
        // Определение режима - только файл
        FileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        FileChooser.setAcceptAllFileFilterUsed(false);
        // Фильтр
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JSON", "JSON");
        FileChooser.setFileFilter(filter);
        this.graphicsPanel = graphicsPanel;
    }

    public void SaveFile()
    {
        int result = FileChooser.showSaveDialog(graphicsPanel);
        File fileSave = FileChooser.getSelectedFile();
        if (result == JFileChooser.APPROVE_OPTION) {
            String path = fileSave.getPath();
            if (fileExists(result))
            {
                dataPut.getPath(path);
                if (MandelbrotInt == 0) {
                    dataPut.put(PlaneSave, MandelbrotSave, ColorSave, MandelbrotXi, CurrentColorI, MaxIterationsSave); // Никитино
                } else {
                    MandelbrotSave.setMaxIterations(MandelbrotInt);
                    dataPut.put(PlaneSave, MandelbrotSave, ColorSave, MandelbrotXi, CurrentColorI, MaxIterationsSave);
                }
            }
        }
    }
    public void setDataPut(Plane PlaneSave, Mandelbrot MandelbrotSave, ColorFunctionDark ColorSave)
    {
        this.PlaneSave = PlaneSave;
        this.MandelbrotSave = MandelbrotSave;
        this.ColorSave = ColorSave;
    }
    public Boolean fileExists(int result)
    {
        File[] filesInDirectory = FileChooser.getCurrentDirectory().listFiles();
        assert filesInDirectory != null;
        String obj = FileChooser.getSelectedFile().getName();
        if (!obj.endsWith(".json")) obj += ".json";
        for (File file : filesInDirectory ) {
            if(file.getName().equals(obj))
            {
                if (result == JFileChooser.APPROVE_OPTION) {
                    JOptionPane.showMessageDialog(graphicsPanel,
                            "Файл (" + FileChooser.getSelectedFile().getName() +
                                    " ) уже существует");
                    return false;
                }
            }
        }
        // Если файл выбран, то представим его в сообщении
        JOptionPane.showMessageDialog(graphicsPanel,
                "Файл (" + FileChooser.getSelectedFile().getName() +".json"+
                        " ) сохранен");
        return true;

    }
}