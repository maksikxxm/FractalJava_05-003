package ru.smak.data;
import ru.smak.graphics.*;
import ru.smak.gui.GraphicsPanel;
import ru.smak.gui.MainWindow;
import ru.smak.math.fractals.*;
import ru.smak.menu.InstrumentPanel;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class fileChooserOpen
{
    private final JFileChooser FileChooser = new JFileChooser();
    private   GraphicsPanel graphicsPanel;
    private Colorizer currentColorizer= new ColorFunctionDark();
    private Fractal currentFractal = new MandelbrotX2();
    private  String path;
    private MainWindow window;
    private FractalFunctions[] fractalFunctions;

    public fileChooserOpen()
    {
        FileChooser.setDialogTitle("Выберите файл");
        // Определение режима - только файл
        FileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        // Фильтр
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JSON", "JSON");
        FileChooser.setFileFilter(filter);
    }

    public void OpenFile()
    {

        int result = FileChooser.showOpenDialog(graphicsPanel);
        File fileOpen = FileChooser.getSelectedFile();
        if(fileOpen == null) {return;}
        path = fileOpen.getPath();
        dataGet.pathDontName = path;
        dataGet parser = new dataGet();
        Root Root = parser.parser();
        OpenPainter(Root);
        System.out.println("Data= "+ Root.toString());
        // Если файл выбран, то представим его в сообщении
        if (result == JFileChooser.APPROVE_OPTION )
            JOptionPane.showMessageDialog(graphicsPanel,
                    "Файл (" + FileChooser.getSelectedFile() +
                            " ) открыт");

    }
    public void OpenPainter(Root rootData)
    {
        graphicsPanel.removePaintersByType("class ru.smak.graphics.FractalPainter");
        InstrumentPanel instrumentPanel = window.getInstrumentPanel();
        double xMin = rootData.PlaneSave._xMin;
        double xMax = rootData.PlaneSave._xMax;
        double yMin = rootData.PlaneSave._yMin;
        double yMax = rootData.PlaneSave._yMax;
        Plane planeOpen = new Plane(xMin, xMax, yMin, yMax,  graphicsPanel.getWidth(), graphicsPanel.getHeight());

        switch (rootData.CurrentColorI) {
            case 0 -> currentColorizer = new ColorFunctionDark();
            case 1 -> currentColorizer = new ColorFunctionGreen();
            case 2 -> currentColorizer = new ColorFunctionRed();
        }
        switch (rootData.MandelbrotXi) {
            case 0 -> currentFractal = new MandelbrotX2();
            case 1 -> currentFractal = new MandelbrotX3();
            case 2 -> currentFractal = new MandelbrotX5();
            case 3 -> currentFractal = new MandelbrotX10();
            case 4 -> currentFractal = new MandelbrotSin();
            case 5 -> currentFractal = new MandelbrotCos();
        }

        instrumentPanel.getJComboBoxMandelbrot().setSelectedIndex(rootData.MandelbrotXi);

        instrumentPanel.getJComboBoxColorizer().setSelectedIndex(rootData.CurrentColorI);
        window.setPlane(planeOpen);
        graphicsPanel.addPainter( new FractalPainter(planeOpen,currentFractal, currentColorizer));
        graphicsPanel.repaint();
    }
    public void Panel(GraphicsPanel GraphicsPanel)
    {
        this.graphicsPanel = GraphicsPanel;

    }
    public void WindowOpen(MainWindow window)
    {
        this.window = window;
    }
}
