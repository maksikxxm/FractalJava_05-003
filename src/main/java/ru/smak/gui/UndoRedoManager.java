package ru.smak.gui;

import kotlin.Pair;
import ru.smak.graphics.Plane;

import java.util.ArrayList;
import java.util.List;

public class UndoRedoManager {
    private Plane plane;
    private List<Pair<Double, Double>> UndoRedoListX;
    private List<Pair<Double, Double>> UndoRedoListY;

    public UndoRedoManager(Plane plane) {
        this.plane = plane;
        UndoRedoListX = new ArrayList<>();
        UndoRedoListY = new ArrayList<>();
    }

    public void addState() {
        UndoRedoListX.add(new Pair<>(plane.getXMin(), plane.getXMax()));
        UndoRedoListY.add(new Pair<>(plane.getYMin(), plane.getYMax()));
    }

    public void undo() {     //  Ctrl + Z
        plane.setXEdges(UndoRedoListX.get(0));
        plane.setYEdges(UndoRedoListY.get(0));
    }

    public void redo() {
    }    //  Ctrl + Y
}
