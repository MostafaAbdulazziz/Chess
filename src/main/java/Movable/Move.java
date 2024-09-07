package Movable;

import Board.Square;

public class Move {
    private int from;
    private int to;
    private Square[] board;
    private int[] GameBoard;

    public Move() {
    }

    public Move(int from, int to, Square[] board, int[] GameBoard) {
        this.from = from;
        this.to = to;
        this.board = board;
        this.GameBoard = GameBoard;
        move(from, to);
    }

    public boolean isMoveValid() {
        board[from].getPiece().findMoves(from,board,GameBoard);
        System.out.println(board[from].getPiece().isWhite());

        return board[from].getPiece().getPossibleMoves().contains(to) &&
                (board[from].getPiece().isWhite() && GameBoard[board[to].getIndex()] > 6 ||
                        !board[from].getPiece().isWhite() && (GameBoard[board[to].getIndex()] <= 6  || GameBoard[board[to].getIndex()] == 100));
    }

    public void move(int from, int to) {
        if (isMoveValid()) {
            board[to].setPiece(board[from].getPiece());  // Move piece
            board[from].removePiece();
            int temp = GameBoard[board[to].getIndex()];
            GameBoard[board[to].getIndex()] = GameBoard[board[from].getIndex()];
            GameBoard[board[from].getIndex()] = temp;
        }
    }

    public Square[] getBoard() {

        return board;
    }
    public int[] getGameBoard()
    {
        return this.GameBoard;
    }
}
