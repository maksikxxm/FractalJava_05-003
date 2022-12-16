package ru.smak.JuliaSet;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import com.sun.tools.javac.Main;
import ru.smak.dynamic.MaxIterations;
import ru.smak.graphics.*;
import ru.smak.gui.*;
import ru.smak.math.Complex;
import ru.smak.math.fractals.Julia;


public class JuliaWindow extends JFrame {
    //private final Dimension minSz = new Dimension(600, 400);
    private Point firstScalePoint = null;
    private Point lastScalePoint = null;
    private Point firstDragPoint = null;
    private Point lastDragPoint = null;
    private int LastButtonPressed;
    private int LastButtonReleased;
    UndoRedoManager undoRedoManager;
    public JuliaWindow(Complex z0, MainWindow mainWindow){
        GraphicsPanel panel = new GraphicsPanel();
        Plane plane = mainWindow.getPlane();
        panel.addPainter(new FractalPainter(plane,new Julia(z0),mainWindow.getInstrumentPanel().getCurrentColorizer()));
        Dimension Sz = new Dimension(plane.getWidth() + 15, plane.getHeight() + 39);
        setMinimumSize(Sz);
        GroupLayout gl = new GroupLayout(getContentPane());
        Scaler scaler = new Scaler(plane);
        gl.setHorizontalGroup(gl.createSequentialGroup().addComponent(panel));
        gl.setVerticalGroup(gl.createSequentialGroup().addComponent(panel));
        undoRedoManager = new UndoRedoManager(scaler);

        panel.setFocusable(true);

        panel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                plane.setWidth(panel.getWidth());
                plane.setHeight(panel.getHeight());
                scaler.scale();
                panel.repaint();
            }
        });

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                LastButtonPressed = e.getButton();
                if(LastButtonPressed == 1)
                {
                    firstScalePoint = e.getPoint();
                }
                else if(LastButtonPressed == 3)
                {
                    firstDragPoint = e.getPoint();
                }
            }

            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                LastButtonReleased = e.getButton();
                if(LastButtonReleased == 1 && lastScalePoint != null) {
                    var g = panel.getGraphics();
                    g.setXORMode(Color.WHITE);
                    g.drawRect(Math.min(firstScalePoint.x, lastScalePoint.x), Math.min(firstScalePoint.y, lastScalePoint.y), Math.abs(lastScalePoint.x - firstScalePoint.x), Math.abs(lastScalePoint.y - firstScalePoint.y));
                    g.setPaintMode();
                    double selectedXMin = Converter.INSTANCE.xScrToCrt(Math.min(firstScalePoint.x, lastScalePoint.x), plane);
                    double selectedXMax = Converter.INSTANCE.xScrToCrt(Math.max(firstScalePoint.x, lastScalePoint.x), plane);
                    double selectedYMax = Converter.INSTANCE.yScrToCrt(Math.min(firstScalePoint.y, lastScalePoint.y), plane);
                    double selectedYMin = Converter.INSTANCE.yScrToCrt(Math.max(firstScalePoint.y, lastScalePoint.y), plane);
                    scaler.setScaleBorders(selectedXMin, selectedXMax, selectedYMin, selectedYMax);
                    undoRedoManager.insertState();
                    lastScalePoint = firstScalePoint = null;
                    panel.repaint();
                }
                if(LastButtonPressed == 3 && lastDragPoint != null){
                    scaler.setScaleBorders(plane.getXMin(), plane.getXMax(), plane.getYMin(), plane.getYMax());
                    undoRedoManager.insertState();
                    lastDragPoint = firstDragPoint = null;
                }
            }
        });

        panel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                if(LastButtonPressed == 1)
                {
                    var g = panel.getGraphics();
                    g.setXORMode(Color.WHITE);
                    g.setColor(Color.BLACK);
                    if (lastScalePoint !=null) {
                        g.drawRect(Math.min(firstScalePoint.x, lastScalePoint.x), Math.min(firstScalePoint.y, lastScalePoint.y), Math.abs(lastScalePoint.x- firstScalePoint.x), Math.abs(lastScalePoint.y- firstScalePoint.y));
                    }
                    g.drawRect(Math.min(firstScalePoint.x, e.getX()), Math.min(firstScalePoint.y, e.getY()), Math.abs(e.getX()- firstScalePoint.x), Math.abs(e.getY()- firstScalePoint.y));
                    g.setPaintMode();
                    lastScalePoint = e.getPoint();
                }
                if(LastButtonPressed == 3)
                {
                    lastDragPoint = e.getPoint();
                    Drag a =new Drag(plane,firstDragPoint,lastDragPoint);
                    firstDragPoint = new Point(lastDragPoint);
                    panel.repaint();
                }
                if(LastButtonPressed == 2)
                {
                    lastScalePoint = e.getPoint();
                }
            }
        });

        panel.addKeyListener(new KeyAdapter() {     //слушатель для прослушивания событий клавиатуры
            @Override
            public void keyPressed(KeyEvent e){
                if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_Z){
                    undoRedoManager.undo();
                    panel.repaint();
                }
                if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_Y){
                    undoRedoManager.redo();
                    panel.repaint();
                }
            }
        });

    }

}
