package ru.smak.gui;

import kotlin.Pair;
import ru.smak.graphics.Plane;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class UndoRedoManager {
    private Plane plane;
    private int undoRedoPointer = -1;
    private Stack<Pair<Double, Double>> xEdgesStack;
    private Stack<Pair<Double, Double>> yEdgesStack;

    public UndoRedoManager(Plane plane) {
        this.plane = plane;
        xEdgesStack = new Stack<>();
        yEdgesStack = new Stack<>();
    }

    public void insertState() {
        deleteStatesAfterPointer(undoRedoPointer);
        xEdgesStack.push(plane.getXEdges());
        yEdgesStack.push(plane.getYEdges());
        undoRedoPointer++;
    }

    private void deleteStatesAfterPointer(int undoRedoPointer) {
        if(xEdgesStack.size() < 1)
            return;
        for(int i = xEdgesStack.size() - 1; i > undoRedoPointer; i--) {
            xEdgesStack.pop();
            yEdgesStack.pop();
        }
    }

    public void undo() {    //  Ctrl + Z
        if(undoRedoPointer < 0)
            return;
        plane.setXEdges(xEdgesStack.get(undoRedoPointer));
        plane.setYEdges(yEdgesStack.get(undoRedoPointer));
        undoRedoPointer--;
    }

    public void redo() {    //  Ctrl + Y
        if(undoRedoPointer == xEdgesStack.size() - 1)
            return;
        undoRedoPointer++;
        plane.setXEdges(xEdgesStack.get(undoRedoPointer));
        plane.setYEdges(yEdgesStack.get(undoRedoPointer));
    }
}
