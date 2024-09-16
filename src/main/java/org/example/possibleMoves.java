package org.example;

import javax.swing.*;

public interface possibleMoves {
    public JLabel[] highlightMoves(JLabel[] squares,int index,Piece piece);
    public JLabel[] highlightMoves(JLabel[] squares);

}
