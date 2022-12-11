package ru.smak.gui;

import ru.smak.graphics.Plane;

public class UndoRedoManager {
    private Plane plane;
    //private List<> UndoRedoList;
    public UndoRedoManager(Plane plane){
        this.plane = plane;
    }

    public void undo(){     //  Ctrl + Z

    }

    public void redo(){}    //  Ctrl + Y
}
