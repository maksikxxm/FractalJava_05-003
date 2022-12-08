package ru.smak.data;

import ru.smak.graphics.ColorFunction;
import ru.smak.graphics.Plane;
import ru.smak.gui.GraphicsPanel;
import ru.smak.math.fractals.Mandelbrot;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class fileChooserTest
{
    private Plane PlaneSave;
    private Mandelbrot MandelbrotSave;
    private ColorFunction ColorSave;
    private final JFileChooser FileChooser = new JFileChooser();
    private GraphicsPanel graphicsPanel;
    private  String path;
    private  dataInformationPut dataPut = new dataInformationPut();
    public fileChooserTest()
    {

    }
    public  fileChooserTest(GraphicsPanel graphicsPanel)
    {
        this.graphicsPanel = graphicsPanel;
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
        File file = FileChooser.getSelectedFile();
        if(file == null) {return;}
        path = file.getPath();
        dataPut.getPath(path);
        dataPut.put(PlaneSave,MandelbrotSave,ColorSave); // Никитино
        System.out.println(PlaneSave.getXMax()+" PlaneSaveFile");
        // Если файл выбран, то представим его в сообщении
        if (result == JFileChooser.APPROVE_OPTION )
            JOptionPane.showMessageDialog(graphicsPanel,
                    "Файл (" + FileChooser.getSelectedFile() +
                            " ) сохранен");

    }
    public void setDataPut(Plane PlaneSave, Mandelbrot MandelbrotSave, ColorFunction ColorSave)
    {
        this.PlaneSave = PlaneSave;
        this.MandelbrotSave = MandelbrotSave;
        this.ColorSave = ColorSave;



    }
    public Plane getPlaneSave()
    {
        return PlaneSave;
    }
    public Mandelbrot getPMandelbrotSave()
    {
        return MandelbrotSave;
    }
    public ColorFunction getColorSave()
    {
        return ColorSave;
    }

}
