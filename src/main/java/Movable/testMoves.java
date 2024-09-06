package Movable;

import Board.Square;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

public class testMoves implements possibleMoves {
    boolean previuosPressed = false;
    boolean[] isHighlighted = new boolean[64];

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
//            Piece p = new Piece();


        }

        return moves;

    }



    @Override
    public Square[] highlightMoves(Square[] squares) {
        for (int index = 0; index < squares.length; index++) {


            int finalIndex = index;
            squares[index].addMouseListener(new MouseAdapter() {
                Vector<Integer> moves = new Vector<>();
                boolean f = true;

                @Override
                public void mouseClicked(MouseEvent e) {
                    if(f && !previuosPressed &&  squares[finalIndex].getPiece() != null)
                    {
                        moves.clear();
                        moves = findMoves(finalIndex);
                        squares[finalIndex].setBorder(BorderFactory.createLineBorder(Color.YELLOW));
                        for (Integer i : moves)
                            squares[i].setBorder(BorderFactory.createLineBorder(Color.GREEN, 5));
                        f = false;
                        previuosPressed = true;
                    }
                    else
                    {
                        Vector<Integer> moves = new Vector<>();
                        moves = findMoves(finalIndex);
                        for (Integer i : moves) {
                            squares[i].setBorder(null);


                        }
                        f = true;
                        previuosPressed = false;

                    }
                }

                @Override
                public void mousePressed(MouseEvent e) {



                }

                @Override
                public void mouseReleased(MouseEvent e) {

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
