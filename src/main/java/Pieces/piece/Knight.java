package Pieces.piece;

import Board.Square;

import java.util.Vector;

public class Knight extends Piece {

    private static final int BOARD_SIZE = 8;
    int[] gameBoard;
    int startingIdx;
    Square[] squares;

    public Knight(boolean isWhite) {
        super(isWhite);
        if (isWhite) {
            super.setPath("src\\sources\\wn.png");
        } else {
            super.setPath("src\\sources\\bn.png");
        }
    }

    @Override
    public void findMoves(int index , Square[] squares,int[] gameBoard) {
        this.startingIdx = index;
        this.gameBoard = gameBoard;
        this.squares = squares;
        super.possible_Moves = new Vector<Integer>();

        int row = index / BOARD_SIZE;
        int col = index % BOARD_SIZE;

        addIfValid(row, col, row + 2, col + 1, index + 17);  // Move up 2, right 1
        addIfValid(row, col, row + 2, col - 1, index + 15);  // Move up 2, left 1
        addIfValid(row, col, row - 2, col + 1, index - 15);  // Move down 2, right 1
        addIfValid(row, col, row - 2, col - 1, index - 17);  // Move down 2, left 1
        addIfValid(row, col, row + 1, col + 2, index + 10);  // Move up 1, right 2
        addIfValid(row, col, row + 1, col - 2, index + 6);   // Move up 1, left 2
        addIfValid(row, col, row - 1, col + 2, index - 6);   // Move down 1, right 2
        addIfValid(row, col, row - 1, col - 2, index - 10);  // Move down 1, left 2
    }

    private void addIfValid(int startRow, int startCol, int endRow, int endCol, int targetIndex) {
        // Check if the move stays within bounds of the board
        if (targetIndex >= 0 && targetIndex < 64 && isWithinBounds(startRow, endRow) && isWithinBounds(startCol, endCol)  && (squares[startingIdx].getPiece().isWhite() && gameBoard[targetIndex] > 6)) {
            super.possible_Moves.add(targetIndex);
        }
    }

    private boolean isWithinBounds(int start, int end) {
        return end >= 0 && end < BOARD_SIZE && Math.abs(start - end) <= 2;  // Maximum shift of 2 columns or rows for valid moves
    }

    @Override
    public Vector<Integer> getPossibleMoves() {
        return super.getPossibleMoves();
    }
}
