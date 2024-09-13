package Pieces.piece;

import Board.Square;
import java.util.Vector;

public class Rook extends Piece {
    private static final int BOARD_SIZE = 8;
    Square[] squares;
    private boolean hasMoved = false; // Track if the rook has moved

    public Rook(boolean isWhite) {
        super(isWhite);
        if (isWhite) {
            super.setPath("src\\sources\\wr.png");
        } else {
            super.setPath("src\\sources\\br.png");
        }
    }

    @Override
    public void findMoves(int index, Square[] squares, int[] gameBoard) {
        this.squares = squares;
        super.possible_Moves = new Vector<>();

        int row = index / BOARD_SIZE;
        int col = index % BOARD_SIZE;

        findMovesInDirection(row, col, 0, -1);  // Move left
        findMovesInDirection(row, col, 0, 1);   // Move right
        findMovesInDirection(row, col, -1, 0);  // Move up
        findMovesInDirection(row, col, 1, 0);   // Move down
    }

    public boolean hasMoved() {
        return hasMoved;
    }


    @Override
    public void movePiece() {
        this.hasMoved = true; // Mark that the rook has moved
    }

    private void findMovesInDirection(int startRow, int startCol, int validRow, int validCol) {
        int row = startRow + validRow;
        int col = startCol + validCol;

        while (row >= 0 && row < BOARD_SIZE && col >= 0 && col < BOARD_SIZE) {
            int targetIndex = row * BOARD_SIZE + col;

            if (!addIfValid(targetIndex)) {
                break;  // Stop if blocked by any piece (ally or enemy)
            }

            row += validRow;
            col += validCol;
        }
    }

    private boolean addIfValid(int targetIndex) {
        if (squares[targetIndex].getPiece() == null) {
            super.possible_Moves.add(targetIndex);
            return true;
        }

        if (squares[targetIndex].getPiece().isWhite() != this.isWhite()) {
            super.possible_Moves.add(targetIndex);
        }

        return false;
    }
    @Override
    public Vector<Integer> getPossibleMoves() {
        return super.getPossibleMoves();
    }

}
