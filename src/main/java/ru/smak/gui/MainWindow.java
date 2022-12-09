package ru.smak.gui;

import kotlin.Pair;
import ru.smak.graphics.*;
import ru.smak.math.fractals.MandelbrotX2;
import ru.smak.menu.InstrumentPanel;
import ru.smak.menu.MainMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainWindow extends JFrame {
    private final GraphicsPanel mainPanel = new GraphicsPanel();
    private final Plane plane;
    private static final int GROW = GroupLayout.DEFAULT_SIZE;
    private static final int SHRINK = GroupLayout.PREFERRED_SIZE;
    private final Dimension minSz = new Dimension(600, 500);

    private Point p1 = null;
    private Point pp = null;

    private int LastButtonPressed;
    private int LastButtonReleased;

    public Plane getPlane() {
        return plane;
    }

    public GraphicsPanel getMainPanel()
    {
        return mainPanel;
    }

    public MainWindow(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(minSz);

        MandelbrotX2 m = new MandelbrotX2();

        plane = new Plane(-2.0, 1.0, -1.0, 1.0, 0, 0);
        var colorFunc = new ColorFunctionDark();
        FractalPainter fp = new FractalPainter(plane, m, colorFunc);
        mainPanel.setBackground(Color.WHITE);

        JMenuBar menuBar = new JMenuBar();
        MainMenu menu = new MainMenu(menuBar);
        setJMenuBar(menuBar);
        JToolBar toolBar = new JToolBar();
        InstrumentPanel tool = new InstrumentPanel(toolBar, this);

        mainPanel.addPainter(fp);

        mainPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                plane.setWidth(mainPanel.getWidth());
                plane.setHeight(mainPanel.getHeight());
            }
        });
        //region Расположение
        GroupLayout gl = new GroupLayout(getContentPane());
        gl.setHorizontalGroup(
                gl.createSequentialGroup()
                        .addGap(4)
                        .addGroup(gl.createParallelGroup()
                                .addComponent(toolBar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                                .addGap(4)
                                .addComponent(mainPanel,GROW, GROW, GROW)
                        )
                        .addGap(4)
        );
        gl.setVerticalGroup(
                gl.createSequentialGroup()
                        .addGap(4)
                        .addComponent(toolBar, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(4)
                        .addComponent(mainPanel, GROW, GROW, GROW)
                        .addGap(4)
        );
        setLayout(gl);
        //endregion
        mainPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                LastButtonPressed = e.getButton();
                if(LastButtonPressed == 1)
                {
                    p1 = e.getPoint();
                }
                else if(LastButtonPressed == 2)
                {

                }
            }

            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                LastButtonReleased = e.getButton();
                if(LastButtonReleased == 1)
                {
                    if (pp!=null) {
                        var g = mainPanel.getGraphics();
                        g.setXORMode(Color.WHITE);
                        g.drawRect(Math.min(p1.x, pp.x), Math.min(p1.y, pp.y), Math.abs(pp.x-p1.x), Math.abs(pp.y-p1.y));
                        g.setPaintMode();
                    }
                    var xMin = Converter.INSTANCE.xScrToCrt(Math.min(p1.x, pp.x), plane);
                    var xMax = Converter.INSTANCE.xScrToCrt(Math.max(p1.x, pp.x), plane);
                    var yMin = Converter.INSTANCE.yScrToCrt(Math.min(p1.y, pp.y), plane);
                    var yMax = Converter.INSTANCE.yScrToCrt(Math.max(p1.y, pp.y), plane);
                    plane.setXEdges(new Pair<>(xMin, xMax));
                    plane.setYEdges(new Pair<>(yMin, yMax));
                    pp = p1 = null;
                    mainPanel.repaint();
                }
            }
        });

        mainPanel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                if(LastButtonPressed == 1)
                {
                    var g = mainPanel.getGraphics();
                    g.setXORMode(Color.WHITE);
                    g.setColor(Color.BLACK);
                    if (pp!=null) {
                        g.drawRect(Math.min(p1.x, pp.x), Math.min(p1.y, pp.y), Math.abs(pp.x-p1.x), Math.abs(pp.y-p1.y));
                    }
                    g.drawRect(Math.min(p1.x, e.getX()), Math.min(p1.y, e.getY()), Math.abs(e.getX()-p1.x), Math.abs(e.getY()-p1.y));
                    g.setPaintMode();
                    pp = e.getPoint();
                    setNewMaxIterations(m, tool.getDynamicStepStatus());
                }
            }
        });

    }

    @Override
    public void setVisible(boolean v){
        super.setVisible(v);
        plane.setWidth(mainPanel.getWidth());
        plane.setHeight(mainPanel.getHeight());
        var g = mainPanel.getGraphics();
        //костыль
        g.setXORMode(Color.WHITE);
        g.drawRect(-1000, -1000, 1, 1);
        g.setPaintMode();
    }

    private double getPlaneShape(Plane plane){
        double xSize = plane.getXMax() - plane.getXMin();
        double ySize = plane.getYMax() - plane.getYMin();
        return xSize*ySize;
    }

    public static int getNewMaxIterations(double shape){
        return (int) (200 + 10 * Math.log(6/shape));
    }

    public void setNewMaxIterations(MandelbrotX2 m, boolean isSelected){
        if(isSelected){
            m.setMaxIterations(getNewMaxIterations(getPlaneShape(plane)));
        }
        else {
            m.setMaxIterations(200);
        }
        mainPanel.repaint();
    }
}
