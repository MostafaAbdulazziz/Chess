package org.example;

import javax.swing.*;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class addMovements {
    static Square[] board;

    public static Square[] add_Movements(Square[] squares) {
        board = squares;

        // Add mouse listeners to all squares on the chessboard
        for (int i = 0; i < 64; ++i) {
            board[i].addMouseListener(new MouseAdapter() {
                int from = -1;
                int to = -1;
                boolean selected = false;  // To track if a piece is selected

                @Override
                public void mouseClicked(MouseEvent e) {
                    Square clickedSquare = (Square) e.getSource();  // Get the square clicked

                    // First click: select the piece
                    if (!selected) {
                        from = clickedSquare.getIndex();  // Store the index of the square
                        if (board[from].hasPiece()) {  // Ensure there's a piece to move
                            selected = true;
                            System.out.println("Piece selected from square: " + from);
                        }
                    }
                    // Second click: move the piece
                    else {
                        to = clickedSquare.getIndex();  // Get the destination square index
                        if (from != to) {
                            selected = false;
                            // Perform the move and update the board
                            board = new Move(from, to, squares).getBoard();
                            System.out.println("Moved piece from square " + from + " to " + to);
                        }
                    }
                }
            });
        }

        return board;
    }
}
