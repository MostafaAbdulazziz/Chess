package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class HighlightManager {
    public HighlightManager() {
    }
    public JLabel[] addHighlightFeature(JLabel[] squares) {
        for (int i = 0; i < 64; ++i) {

            int finalI = i;
            squares[i].addMouseListener(new MouseAdapter() {

                @Override
                public void mouseClicked(MouseEvent e) {

                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }


                @Override
                public void mouseEntered(MouseEvent e) {
                    // Highlight the label by setting a border
                    squares[finalI].setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.BLUE)); // Yellow dashed marker
                    squares[finalI].revalidate();
                    squares[finalI].repaint();
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    // Remove the border when the mouse exits
                    squares[finalI].repaint();
                    squares[finalI].setBorder(null);
                }
            });


        }
        return squares;
    }
}


