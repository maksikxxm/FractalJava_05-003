package ru.smak.movie;

import javax.swing.*;
import java.awt.*;

public class MovieWindow extends JFrame {
    private final Dimension minSz = new Dimension(600, 500);
    public MovieWindow(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(minSz);
    }
}
