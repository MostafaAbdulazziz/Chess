package Highlighting;

import Board.Square;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class HighlightManager {
    public HighlightManager() {
    }
    public Square[] addHighlightFeature(Square[] squares) {
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
                    if (squares[finalI].getBorder() == null )
                    {
                        squares[finalI].setBorder(BorderFactory.createLineBorder(new Color(238, 255, 0),3)); // Yellow dashed marker
                        squares[finalI].revalidate();

                    }
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    Border border = (Border) squares[finalI].getBorder();
                    if (border != null) {
                        Color borderColor = ((LineBorder) border).getLineColor();
                        if (borderColor != Color.GREEN && borderColor != Color.RED) {
                            squares[finalI].setBorder(null); // Remove border
                            squares[finalI].repaint(); // Repaint the square to ensure visual reset


                        }
                    }
                }

            });


        }
        return squares;
    }
}


