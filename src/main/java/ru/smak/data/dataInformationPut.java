package ru.smak.data;

import com.google.gson.Gson;
import ru.smak.graphics.ColorFunction;
import ru.smak.graphics.Plane;
import ru.smak.math.fractals.Mandelbrot;

import java.io.FileWriter;
import java.io.PrintWriter;


public class dataInformationPut
{
    private final ColorFunction ColorSave;
    private final Plane PlaneSave;
    private final Mandelbrot MandelbrotSave;
    public dataInformationPut(Plane plane, Mandelbrot m, ColorFunction color)
    {
        this.PlaneSave = plane;
        this.MandelbrotSave = m;
        this.ColorSave = color;

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
    public dataInformationPut put()
    {
        dataInformationPut data = new dataInformationPut(PlaneSave,MandelbrotSave,ColorSave);
        String path = "C:\\Users\\nikit\\Desktop\\Прога\\FractalJava-2022\\src\\main\\java\\ru\\smak\\DataInformationFile\\test.json";

        try (PrintWriter out = new PrintWriter(new FileWriter(path))) {
            Gson gson = new Gson();
            String jsonString = gson.toJson(data);
            out.write(jsonString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
