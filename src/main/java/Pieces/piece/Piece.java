package Pieces.piece;

import Board.Square;
import Movable.possibleMoves;
import java.util.Vector;

public class Piece extends possibleMoves {

    public boolean isWhite;
    private String path;
    protected Vector<Integer> possible_Moves;
    private int startingIdx;         // The index of the square where the piece is located

    public Piece(boolean isWhite) {
        this.isWhite = isWhite;
        this.possible_Moves = new Vector<>();
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public boolean isWhite() {
        return this.isWhite;
    }

    // Set the current position of the piece on the board
    public void setStartingIdx(int index) {
        this.startingIdx = index;
    }

    // Get the current position of the piece on the board
    public int getStartingIdx() {
        return this.startingIdx;
    }


    // Abstract method for subclasses to implement specific move logic
    public void findMoves(int index, Square[] squares, int[] gameBoard) {
        // This will be overridden by specific piece classes (Rook, Queen, King, etc.)
    }

    public Vector<Integer> getPossibleMoves() {
        return this.possible_Moves;
    }

    // Set possible moves for the piece (new method to fix the error)
    public void setPossibleMoves(Vector<Integer> possibleMoves) {
        this.possible_Moves = possibleMoves;
    }

    // Check if the piece can move to a specific index
    public boolean canMoveTo(int targetIndex) {
        return possible_Moves.contains(targetIndex);
    }

    public boolean isChecked(int kingIdx, Square[] squares, int[] gameBoard, boolean isWhiteKing) {
        return false;
    }
    public void movePiece() {
        // This will be overridden by specific piece classes (Rook, Queen, King, etc.)
    }
}
