package ru.smak.data;

import com.google.gson.Gson;

import java.io.FileReader;

public class dataGet
{
    public Root parser()
    {
        Gson gson = new Gson();
        try(FileReader reader = new FileReader(
                "C:\\Users\\nikit\\Desktop\\Прога\\FractalJava_05-003\\src\\main\\java\\ru\\smak\\data\\dataInformationFile\\new.json")) {
            Root Root = gson.fromJson(reader,Root.class);
            return Root;
        }
        catch (Exception e)
        {
            System.out.println("Parsing error" + e.toString());
        }


        return null;
    }
}
