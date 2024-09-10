package Movable;

import Board.Square;

public class Move {
    private int from;
    private int to;
    private Square[] board;
    private int[] GameBoard;
    private static final int BOARD_SIZE = 8;
    private boolean boardChanged = false;

    public boolean isBoardChanged() {
        return boardChanged;
    }

    public Move() {
    }

    public Move(int from, int to, Square[] board, int[] GameBoard) {
        this.from = from;
        this.to = to;
        this.board = board;
        this.GameBoard = GameBoard;
        findKingIdx(board[from].getPiece().isWhite());
        move(from, to);
    }

    public boolean isMoveValid() {
        board[from].getPiece().findMoves(from, board, GameBoard);
        System.out.println(board[from].getPiece().isWhite());

        return board[from].getPiece().getPossibleMoves().contains(to) &&
                (board[from].getPiece().isWhite() && GameBoard[board[to].getIndex()] > 6 ||
                        !board[from].getPiece().isWhite() && (GameBoard[board[to].getIndex()] <= 6 || GameBoard[board[to].getIndex()] == 100))
                && !leadToCheck(from, to, board, board[from].getPiece().isWhite());
    }

    public void move(int from, int to) {
        if (isMoveValid()) {
            board[to].setPiece(board[from].getPiece());  // Move piece
            board[from].removePiece();
            int temp = GameBoard[board[to].getIndex()];
            GameBoard[board[to].getIndex()] = GameBoard[board[from].getIndex()];
            GameBoard[board[from].getIndex()] = temp;
            this.boardChanged = true;
        }
    }

    int KingIdx;

    void findKingIdx(boolean isWhite) {
        for (int i = 0; i < 64; i++) {
            if ((isWhite && GameBoard[i] == 6) || (!isWhite && GameBoard[i] == 66)) {
                this.KingIdx = i;
                break;
            }
        }
    }

    private boolean isChecked(int index, Square[] squares, int[] pos, boolean isWhiteKing) {
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
            if (squares[i].getPiece() != null && squares[i].getPiece().isWhite() != isWhiteKing && pos[i] != 66 && pos[i] != 6) {
                squares[i].getPiece().findMoves(i, squares, pos);
                if (squares[i].getPiece().getPossibleMoves().contains(index) && squares[i].getPiece().isWhite() == isWhiteKing) {
                    return true;
                }
            }


        }
        return false;
    }

    public boolean leadToCheck(int from, int to, Square[] squares, boolean isWhiteKing) {
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
        return isChecked(KingIdx, temp, pos, isWhiteKing);
    }


    public Square[] getBoard() {

        return board;
    }

    public int[] getGameBoard() {
        return this.GameBoard;
    }
}
