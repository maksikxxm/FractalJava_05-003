package ru.smak.data;

import com.google.gson.Gson;
import ru.smak.graphics.ColorFunction;
import ru.smak.graphics.Plane;
import ru.smak.math.fractals.Mandelbrot;

import java.io.FileWriter;
import java.io.PrintWriter;


public class dataInformationPut
{
    private  ColorFunction ColorSave;
    private  Plane  PlaneSave;
    private  Mandelbrot MandelbrotSave;
    private  String pathDontName;
    public dataInformationPut(Plane plane, Mandelbrot m, ColorFunction color)
    {
        this.PlaneSave = plane;
        this.MandelbrotSave = m;
        this.ColorSave = color;
        System.out.println(PlaneSave+ "Plane");

    }
    public dataInformationPut()
    {

    }
    public  Plane getPlaneSave()
    {
        return PlaneSave;
    }
    public  Mandelbrot getPMandelbrotSave()
    {
        return MandelbrotSave;
    }
    public  ColorFunction getColorSave()
    {
        return ColorSave;
    }
    // Записывает данные о фрактале в папку "DataInformationFile"
    public void put(Plane  PlaneSave,Mandelbrot MandelbrotSave,ColorFunction ColorSave)
    {
        String path = pathDontName +".json";
        if(path != null) {
            dataInformationPut data = new dataInformationPut(PlaneSave, MandelbrotSave, ColorSave);
            try (PrintWriter out = new PrintWriter(new FileWriter(path))) {
                Gson gson = new Gson();
                String jsonString = gson.toJson(data);
                out.write(jsonString);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void getPath(String path)
    {
        this.pathDontName = path;

    }

}
