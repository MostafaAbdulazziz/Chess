package org.example;

import javax.swing.*;
import java.util.Map;

public class PiecesLoader {
    JLabel[] loadPieces(JLabel[] squares, Map<Integer,String>places)
    {
        for(int i = 0; i < squares.length; i++)
        {
            squares[i].setIcon(new ImageIcon(places.get(i)));
        }
        return squares;
    }
}
