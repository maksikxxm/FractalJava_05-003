package ru.smak.data;
import com.google.gson.Gson;
import ru.smak.graphics.ColorFunctionDark;
import ru.smak.graphics.Plane;
import ru.smak.math.fractals.Mandelbrot;
import java.io.FileWriter;
import java.io.PrintWriter;
public class dataPut {
    private String pathDontName;

    public void put(Plane PlaneSave, Mandelbrot MandelbrotSave, ColorFunctionDark ColorSave, int MandelbrotXi, int ColorXi ) {
        String path = pathDontName + ".json";
        dataInformation data = new dataInformation(PlaneSave, MandelbrotSave, ColorSave,MandelbrotXi,ColorXi);
        System.out.println(MandelbrotXi + "put");
        try (PrintWriter out = new PrintWriter(new FileWriter(path))) {
            Gson gson = new Gson();
            String jsonString = gson.toJson(data);
            out.write(jsonString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getPath(String path) {
        this.pathDontName = path;
    }
}

