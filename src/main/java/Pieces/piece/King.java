package Pieces.piece;

import Board.Square;

import java.util.Vector;

public class King extends Piece {
    private static final int BOARD_SIZE = 8;
    Square[] squares;
    int[] GameBoard;
    int startPosition;


    public King(boolean isWhite) {
        super(isWhite);
        if (isWhite) {
            super.setPath("src\\sources\\wk.png");
        } else {
            super.setPath("src\\sources\\bk.png");
        }
    }

    int getIndex(int row, int col) {
        return row * BOARD_SIZE + col;
    }

    int[][] kingMoves = {
            {-1, -1}, {-1, 0}, {-1, 1}, // Up left, up, up right
            {0, -1}, {0, 1},           // Left, right
            {1, -1}, {1, 0}, {1, 1}    // Down left, down, down right
    };
    int[][] kingCkeckingMoves = {
            {-2, -2}, {-2, 0}, {-2, 2}, // Up left, up, up right
            {1, -2}, {1, 2},           // Left, right
            {2, -2}, {2, 1}, {2, 2}    // Down left, down, down right
    };

    @Override
    public void findMoves(int index, Square[] squares, int[] gameBoard) {
        startPosition = index;
        super.possible_Moves = new Vector<>();
        this.squares = squares;
        this.GameBoard = gameBoard;
        int row = index / 8;
        int col = index % 8;

        // Precompute the opponent's possible moves


        for (int[] move : kingMoves) {
            int newRow = row + move[0];
            int newCol = col + move[1];
            int targetIndex = newRow * BOARD_SIZE + newCol;

            if (isValidMove(newRow, newCol) && !leadToCheck(index, targetIndex, squares)) {
                addIfValid(targetIndex, squares);
            }
        }
    }

    private void addIfValid(int targetIndex, Square[] squares) {
        if (squares[targetIndex].getPiece() == null) {
            super.possible_Moves.add(targetIndex);
            return;
        }

        if (squares[targetIndex].getPiece() != null && squares[targetIndex].getPiece().isWhite() != this.isWhite()) {
            super.possible_Moves.add(targetIndex);
            return;
        }
    }


    private boolean isValidMove(int row, int col) {
        return row >= 0 && row < BOARD_SIZE && col >= 0 && col < BOARD_SIZE;
    }

    @Override
    public   boolean isChecked(int index, Square[] squares, int[] pos , boolean isWhite) {
        int wKingRow = 0;
        int bKingRow = 0;
        int wKingCol = 0;
        int bKingCol = 0;
        for (int i = 0; i < 64; i++) {
            if (pos[i] == 6) {
                wKingCol = i % BOARD_SIZE;
                wKingRow = i / BOARD_SIZE;
            }
            if (pos[i] == 66) {
                bKingCol = i % BOARD_SIZE;
                bKingRow = i / BOARD_SIZE;
            }
        }
        if (Math.abs(wKingRow - bKingRow) <= 1 && Math.abs(wKingCol - bKingCol) <= 1) {
            return true;
        }


        for (int i = 0; i < 64; i++) {
            if (squares[i].getPiece() != null && squares[i].getPiece().isWhite() != isWhite && pos[i] != 66 && pos[i] != 6) {
                squares[i].getPiece().findMoves(i, squares, pos);
                if (squares[i].getPiece().getPossibleMoves().contains(index)) {
                    return true;
                }
            }


        }
        return false;
    }

    private boolean leadToCheck(int from, int to, Square[] squares) {
        // Create a deep copy of the squares array
        Square[] temp = new Square[64];
        int[] pos = new int[64];
        for (int i = 0; i < 64; i++) {
            temp[i] = new Square(squares[i].getIndex());
            temp[i].setPiece(squares[i].getPiece());// Assuming a constructor that copies Square
            pos[i] = GameBoard[i];
        }

        // Simulate the move on the copied board
        pos[to] = pos[from];
        pos[from] = 100;
        temp[to].setPiece(temp[from].getPiece());
        temp[from].setPiece(null);


        // Check if the king is in check after this move
        return isChecked(to, temp, pos , this.isWhite());
    }


    @Override
    public Vector<Integer> getPossibleMoves() {
        return super.getPossibleMoves();
    }
}
