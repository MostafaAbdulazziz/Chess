package org.example;

import javax.swing.*;
import java.awt.*;

class Background_Panel extends JPanel {
    private final ImageIcon backgroundImage;

    public Background_Panel(ImageIcon backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the background image, scaled to fit the panel
       // g.drawImage((backgroundImage(), 0, 0, getWidth(), getHeight(), this);
    }
}