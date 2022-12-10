package ru.smak.data;

import ru.smak.data.dataClass.ColorSave;
import ru.smak.data.dataClass.MandelbrotSave;
import ru.smak.data.dataClass.PlaneSave;

import java.util.ArrayList;
import java.util.List;

public class Root
{
    public Integer MandelbrotXi;
    public Integer CurrentColorI;
    public ColorSave ColorSave;
    public PlaneSave PlaneSave;
    public MandelbrotSave MandelbrotSave;


    public  Integer getMandelbrotXi()
    {
        return  MandelbrotXi;
    }
    public  Integer getCurrentColorI()
    {
        return  CurrentColorI;
    }
    public  ColorSave getColorSave()
    {
        return  ColorSave;
    }
    public PlaneSave getPlaneSave() {return  PlaneSave;}
    public MandelbrotSave getMandelbrotSave() {return  MandelbrotSave;}

    public void setMandelbrotXi(Integer MandelbrotXi)
    {
        this.MandelbrotXi = MandelbrotXi;
    }

    public void setCurrentColorI(Integer CurrentColorI)
    {
        this.CurrentColorI = CurrentColorI;
    }
    public  void setColorSave(ColorSave ColorSave)
    {
        this.ColorSave = ColorSave;
    }
    public  void setPlaneSave(PlaneSave PlaneSave)
    {
        this.PlaneSave= PlaneSave;
    }
    public  void setMandelbrotSave(MandelbrotSave MandelbrotSave)
    {
        this.MandelbrotSave = MandelbrotSave;
    }

    @Override
    public String toString()
    {

        return "Root{"+"PlaneSave='"+PlaneSave+'\''+ ", MandelbrotSave="+ MandelbrotSave +'}';
    }
    public void print()
    {

        System.out.println(PlaneSave._xMax + "What?");
    }
}
