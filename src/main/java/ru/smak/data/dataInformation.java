package ru.smak.data;

import com.google.gson.Gson;
import ru.smak.graphics.ColorFunctionDark;
import ru.smak.graphics.Plane;
import ru.smak.math.fractals.MandelbrotX2;

import java.io.FileWriter;
import java.io.PrintWriter;


public class dataInformation
{
    private int MandelbrotXi;
    private  int CurrentColorI;
    private  ColorFunctionDark ColorSave;
    private  Plane  PlaneSave;
    private  MandelbrotX2 MandelbrotSave;
    private  String pathDontName;
    public dataInformation(Plane plane, MandelbrotX2 m, ColorFunctionDark color, Integer MandelbrotXi, Integer CurrentColorI)
    {
        this.PlaneSave = plane;
        this.MandelbrotSave = m;
        this.ColorSave = color;
        this.MandelbrotXi = MandelbrotXi;
        this.CurrentColorI = CurrentColorI;

    }
    public dataInformation()
    {

    }
    public  Plane getPlaneSave()
    {
        return PlaneSave;
    }
    public  MandelbrotX2 getPMandelbrotX2Save()
    {
        return MandelbrotSave;
    }
    public  ColorFunctionDark getColorDarkSave()
    {
        return ColorSave;
    }
//    public void put(Plane  PlaneSave, MandelbrotX2 MandelbrotSave, ColorFunctionDark ColorSave,Integer MandelbrotXi,Integer CurrentColorI)
//    {
//        String path = pathDontName +".json";
//        dataInformation data = new dataInformation(PlaneSave, MandelbrotSave, ColorSave,MandelbrotXi,CurrentColorI);
//        try (PrintWriter out = new PrintWriter(new FileWriter(path))) {
//            Gson gson = new Gson();
//            String jsonString = gson.toJson(data);
//            out.write(jsonString);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    public void getPath(String path)
//    {
//        this.pathDontName = path;
//
//    }

}
