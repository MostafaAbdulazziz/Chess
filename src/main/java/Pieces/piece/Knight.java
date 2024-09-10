package Pieces.piece;

import Board.Square;
import java.util.Vector;

public class Knight extends Piece {
    private static final int BOARD_SIZE = 8;
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
    public void findMoves(int index, Square[] squares, int[] gameBoard) {
        this.squares = squares;
        super.possible_Moves = new Vector<>();

        int row = index / BOARD_SIZE;
        int col = index % BOARD_SIZE;

        int[][] knightMoves = {
                {-2, -1}, {-2, 1},   // Two rows up, one column left/right
                {2, -1},  {2, 1},    // Two rows down, one column left/right
                {-1, -2}, {1, -2},   // One row up/down, two columns left
                {-1, 2},  {1, 2}     // One row up/down, two columns right
        };

        // Try each possible knight move
        for (int[] move : knightMoves) {
            int newRow = row + move[0];
            int newCol = col + move[1];
            int targetIndex = newRow * BOARD_SIZE + newCol;

            if (isValidMove(newRow, newCol)) {
                addIfValid(targetIndex);
            }
        }
    }

    private boolean isValidMove(int row, int col) {
        return row >= 0 && row < BOARD_SIZE && col >= 0 && col < BOARD_SIZE;
    }

    private void addIfValid(int targetIndex) {

        if (squares[targetIndex].getPiece() == null) {
            super.possible_Moves.add(targetIndex);
            return ;
        }


        if (squares[targetIndex].getPiece().isWhite() != this.isWhite()) {
            super.possible_Moves.add(targetIndex);
        }

        return ;
    }







    @Override
    public Vector<Integer> getPossibleMoves() {
        return super.getPossibleMoves();
    }
}
