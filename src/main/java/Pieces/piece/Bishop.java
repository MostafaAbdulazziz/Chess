package Pieces.piece;

import Board.Square;

import java.util.Vector;

public class Bishop extends Piece {
    private static final int BOARD_SIZE = 8;
    Square[] squares;

    public Bishop(boolean isWhite) {
        super(isWhite);
        if (isWhite) {
            super.setPath("src/sources/wb.png");
            super.setIconPath("src\\sources\\wb1.png");
        } else {
            super.setPath("src/sources/bb.png");
            super.setIconPath("src\\sources\\bb1.png");
        }
    }

    @Override
    public void findMoves(int index, Square[] squares, int[] gameBoard) {
        this.squares = squares;
        super.possible_Moves = new Vector<>();

        int row = index / BOARD_SIZE;
        int col = index % BOARD_SIZE;

        // Diagonal moves (like the Bishop)
        findMovesInDirection(row, col, -1, -1); // Move up-left
        findMovesInDirection(row, col, -1, 1);  // Move up-right
        findMovesInDirection(row, col, 1, -1);  // Move down-left
        findMovesInDirection(row, col, 1, 1);   // Move down-right
    }

    // Helper function to find moves in any direction (straight or diagonal)
    private void findMovesInDirection(int startRow, int startCol, int validRow, int validCol) {
        int row = startRow + validRow;
        int col = startCol + validCol;

        // Continue moving in the direction until out of bounds or blocked
        while (row >= 0 && row < BOARD_SIZE && col >= 0 && col < BOARD_SIZE) {
            int targetIndex = row * BOARD_SIZE + col;

            if (!addIfValid(targetIndex)) {
                break;  // Stop if blocked by any piece (myPieces or enemy)
            }

            row += validRow;
            col += validCol;
        }
    }

    private boolean addIfValid(int targetIndex) {
        // Check if the square is empty
        if (squares[targetIndex].getPiece() == null) {
            super.possible_Moves.add(targetIndex);
            return true;  // Continue moving in this direction
        }

        // If there's an enemy piece, add the move, but stop further moves in this direction
        if (squares[targetIndex].getPiece().isWhite() != this.isWhite()) {
            super.possible_Moves.add(targetIndex);
        }

        return false;  // Stop further movement (either friendly or enemy piece)
    }

    @Override
    public Vector<Integer> getPossibleMoves() {
        return super.getPossibleMoves();
    }
}
