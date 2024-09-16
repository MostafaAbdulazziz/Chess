package Pieces.piece;

import Board.Square;
import java.util.Vector;

public class Queen extends Piece {
    private static final int BOARD_SIZE = 8;
    Square[] squares;

    public Queen(boolean isWhite) {
        super(isWhite);
        if (isWhite) {
            super.setPath("src\\sources\\wq.png");
            super.setIconPath("src\\sources\\swq.png");
        } else {
            super.setPath("src\\sources\\bq.png");
            super.setIconPath("src\\sources\\sbq.png");
        }
    }

    @Override
    public void findMoves(int index, Square[] squares, int[] gameBoard) {
        this.squares = squares;
        super.possible_Moves = new Vector<>();

        int row = index / BOARD_SIZE;
        int col = index % BOARD_SIZE;

        // Horizontal and Vertical moves (like the Rook)
        findMovesInDirection(row, col, 0, -1);  // Move left
        findMovesInDirection(row, col, 0, 1);   // Move right
        findMovesInDirection(row, col, -1, 0);  // Move up
        findMovesInDirection(row, col, 1, 0);   // Move down

        // Diagonal moves (like the Bishop)
        findMovesInDirection(row, col, -1, -1); // Move up-left
        findMovesInDirection(row, col, -1, 1);  // Move up-right
        findMovesInDirection(row, col, 1, -1);  // Move down-left
        findMovesInDirection(row, col, 1, 1);   // Move down-right
    }

    // Helper function to find moves in any direction (straight or diagonal)
    private void findMovesInDirection(int startRow, int startCol, int rowDelta, int colDelta) {
        int row = startRow + rowDelta;
        int col = startCol + colDelta;

        // Continue moving in the direction until out of bounds or blocked
        while (row >= 0 && row < BOARD_SIZE && col >= 0 && col < BOARD_SIZE) {
            int targetIndex = row * BOARD_SIZE + col;

            if (addIfValid(targetIndex)) {
                break;  // Stop if blocked by any piece (ally or enemy)
            }

            row += rowDelta;
            col += colDelta;
        }
    }

    private boolean addIfValid(int targetIndex) {
        // Check if the square is empty
        if (squares[targetIndex].getPiece() == null) {
            super.possible_Moves.add(targetIndex);
            return false;  // Continue moving in this direction
        }

        // If there's an enemy piece, add the move, but stop further moves in this direction
        if (squares[targetIndex].getPiece().isWhite() != this.isWhite()) {
            super.possible_Moves.add(targetIndex);
        }

        return true;  // Stop further movement (either friendly or enemy piece)
    }

    @Override
    public Vector<Integer> getPossibleMoves() {
        return super.getPossibleMoves();
    }
}
