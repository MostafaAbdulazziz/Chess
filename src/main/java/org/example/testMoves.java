package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

public class testMoves implements possibleMoves {

    boolean ValidIndex(int i) {
        return i >= 0 && i < 64;
    }

    Vector<Integer> findMoves(int index) {
        Vector<Integer> moves = new Vector<>();
        for (int i = index - 8; i >= 0 && i < 64; i -= 8) {
            if (ValidIndex(i)) moves.add(i);


        }

        for (int i = index + 8; i >= 0 && i < 64; i += 8) {
            if (ValidIndex(i)) moves.add(i);

        }
///////////////////////////////Diagonally Movement///////////////////////////////
//        for (int i = index, cnt = 0; i >= 0 && i < 64 && cnt == 0; i -= 9) {
//            if (ValidIndex(i)) moves.add(i);
//            if (i % 8 == 0) cnt++;
//
//        }
//
//        for (int i = index, cnt = 0; i >= 0 && i < 64 && cnt == 0; i += 9) {
//            if (ValidIndex(i)) moves.add(i);
//            if ((i + 1) % 8 == 0) cnt++;

//        }

//        for (int i = index; i >= 0 && i < 64; i -= 7) {
//            if (ValidIndex(i)) moves.add(i);
//
//        }
//        for (int i = index; i >= 0 && i < 64; i += 7) {
//            if (ValidIndex(i)) moves.add(i);
//
//        }
        for (int i = index - 1, cnt = 0; i >= 0 && i < 64 && cnt == 0; i -= 1) {
            if (ValidIndex(i)) moves.add(i);
            if (i % 8 == 0) cnt++;

        }
        for (int i = index + 1, cnt = 0; i >= 0 && i < 64 && cnt == 0; i += 1) {
            if (ValidIndex(i)) moves.add(i);
            if ((i + 1) % 8 == 0) cnt++;

        }

        return moves;

    }

    @Override
    public JLabel[] highlightMoves(JLabel[] squares, int index, Piece piece) {
        return new JLabel[0];
    }

    @Override
    public JLabel[] highlightMoves(JLabel[] squares) {
        for (int index = 0; index < squares.length; index++) {


            int finalIndex = index;
            squares[index].addMouseListener(new MouseAdapter() {
                Vector<Integer> moves = new Vector<>();

                @Override
                public void mouseClicked(MouseEvent e) {

                }

                @Override
                public void mousePressed(MouseEvent e) {
                    moves.clear();
                    moves = findMoves(finalIndex);
                    squares[finalIndex].setBorder(BorderFactory.createLineBorder(Color.YELLOW));
                    for (Integer i : moves)
                        squares[i].setBorder(BorderFactory.createLineBorder(new Color(27, 140, 0), 5));


                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    Vector<Integer> moves = new Vector<>();
                    moves = findMoves(finalIndex);
                    for (Integer i : moves) {
                        squares[i].setBorder(null);


                    }

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });
        }
        return squares;
    }
}
