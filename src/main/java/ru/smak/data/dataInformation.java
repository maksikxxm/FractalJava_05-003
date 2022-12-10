package ru.smak.data;

import com.google.gson.Gson;
import ru.smak.graphics.ColorFunctionDark;
import ru.smak.graphics.Plane;
import ru.smak.math.fractals.Mandelbrot;
import ru.smak.math.fractals.MandelbrotX2;

import java.io.FileWriter;
import java.io.PrintWriter;


public class dataInformation {
    private int MandelbrotXi;
    private int CurrentColorI;
    private ColorFunctionDark ColorSave;
    private Plane PlaneSave;
    private Mandelbrot MandelbrotSave;

    public dataInformation(Plane plane, Mandelbrot m, ColorFunctionDark color, int MandelbrotXi, int CurrentColorI) {
        this.PlaneSave = plane;
        this.MandelbrotSave = m;
        this.ColorSave = color;
        this.MandelbrotXi = MandelbrotXi;
        this.CurrentColorI = CurrentColorI;

    }

    public Plane getPlaneSave() {
        return PlaneSave;
    }

    public Mandelbrot getPMandelbrotX2Save() {
        return MandelbrotSave;
    }

    public ColorFunctionDark getColorDarkSave() {
        return ColorSave;
    }
    public Integer getPMandelbrotXiSave() {
        return MandelbrotXi;
    }
    public Integer getCCurrentColorISave() {
        return CurrentColorI;
    }
//    @Override
//    public String toString()
//    {
//
//        return "Data{"+"Plane='"+PlaneSave+'\''+
//                ", Mandelbrot="+ MandelbrotSave + '\'' +
//                ", Color="+ ColorSave + + '\'' +
//                ", MandelbrotXi="+ MandelbrotXi +
//                ", CurrentColorI="+ CurrentColorI +
//                '}';
//    }
}
