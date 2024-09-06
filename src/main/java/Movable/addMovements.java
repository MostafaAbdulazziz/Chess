package Movable;

import Board.Square;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class addMovements {
    static Square[] board;

    public static Square[] add_Movements(Square[] squares) {
        board = squares;

        // Add mouse listeners to all squares on the chessboard
        for (int i = 0; i < 64; ++i) {
            board[i].addMouseListener(new MouseAdapter() {


                @Override
                public void mouseClicked(MouseEvent e) {

                }
            });
        }

        return board;
    }
}
