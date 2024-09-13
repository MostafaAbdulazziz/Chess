package Pieces.piece;

import Board.Square;
import Game.GameWindow;

import java.util.Vector;

public class Pawn extends Piece {


    private  GameWindow gameWindow;  // To access the pop-up window in the GameWindow class

    public Pawn(boolean isWhite, GameWindow gameWindow) {
        super(isWhite);
        this.gameWindow = gameWindow;  // Pass GameWindow reference for promotions
        if (isWhite) {
            super.setPath("src\\sources\\wp.png");
        } else {
            super.setPath("src\\sources\\bp.png");
        }
    }


    public Pawn(boolean isWhite) {
        super(isWhite);
        if (isWhite) {
            super.setPath("src\\sources\\wp.png");

        } else {
            super.setPath("src\\sources\\bp.png");
        }
    }

    @Override
    public void findMoves(int index, Square[] squares, int[] gameBoard) {
        super.possible_Moves = new Vector<>();

        int row = index / 8;
        int col = index % 8;

        if (isWhite) {
            handleWhitePawnMoves(index, row, col, squares);
        } else {
            handleBlackPawnMoves(index, row, col, squares);
        }
    }

    private void handleWhitePawnMoves(int index, int row, int col, Square[] squares) {
        // Move forward by 1 square
        if (isValidMove(row - 1, col, squares)) {
            super.possible_Moves.add(index - 8);

            // Move forward by 2 squares if still on starting row (6th row, index 8 to 15)
            if (row == 6 && isValidMove(row - 2, col, squares)) {
                super.possible_Moves.add(index - 16);
            }
        }

        // Handle diagonal captures (left and right)
        handleDiagonalCapture(row - 1, col - 1, index - 9, squares);
        handleDiagonalCapture(row - 1, col + 1, index - 7, squares);
        if (row  == 0) {
            triggerPawnPromotion(index - 8, squares);
        }
    }

    private void handleBlackPawnMoves(int index, int row, int col, Square[] squares) {
        // Standard move forward by 1 square
        if (row < 7 && isValidMove(row + 1, col, squares)) {  // Ensure we're not out of bounds
            super.possible_Moves.add(index + 8);
        }

        // Move forward by 2 squares if still on the starting row (row 1)
        if (row == 1 && isValidMove(row + 2, col, squares) && isValidMove(row + 1, col, squares)) {
            super.possible_Moves.add(index + 16);
        }

        // Handle diagonal captures (left and right)
        handleDiagonalCapture(row + 1, col - 1, index + 7, squares);  // Capture to the left
        handleDiagonalCapture(row + 1, col + 1, index + 9, squares);  // Capture to the right

        // Check for promotion when the pawn reaches the last rank after the move
        if (row + 1 == 8) {
            triggerPawnPromotion(index + 8, squares);
        }
    }

    private void triggerPawnPromotion(int targetIndex, Square[] squares) {
        gameWindow.promotePawn(targetIndex , this.isWhite());
    }

    private void handleDiagonalCapture(int newRow, int newCol, int targetIndex, Square[] squares) {
        if (isWithinBounds(newRow, newCol) && squares[targetIndex].getPiece() != null) {
            Piece targetPiece = squares[targetIndex].getPiece();
            if (targetPiece.isWhite() != this.isWhite()) {
                super.possible_Moves.add(targetIndex);
            }
        }
    }

    private boolean isValidMove(int row, int col, Square[] squares) {
        if (isWithinBounds(row, col)) {
            int index = row * 8 + col;
            return squares[index].getPiece() == null;  // Move is valid if the square is empty

        }
        return false;
    }

    private boolean isWithinBounds(int row, int col) {
        return row >= 0 && row < 8 && col >= 0 && col < 8;
    }

    @Override
    public Vector<Integer> getPossibleMoves() {
        return super.possible_Moves;
    }
}
