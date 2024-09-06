package Movable;

import Pieces.piece.Piece;
import Board.Square;

public class Move {
    private int from;
    private int to;
    Square[] board;

    public Move(int from, int to,Square[] board) {
        this.from = from;
        this.to = to;
        this.board = board;
        Piece piece = board[from].getPiece();
        board[to].setPiece(piece);
        board[from].setPiece(null);
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }

    public Square[] getBoard() {
        return board;
    }
}
