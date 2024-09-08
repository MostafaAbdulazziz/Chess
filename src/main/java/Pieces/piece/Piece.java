package Pieces.piece;

import Board.Square;
import Movable.possibleMoves;

import javax.swing.*;
import java.util.Vector;

public class Piece extends possibleMoves {

    boolean isWhite;
    String path;
    Vector<Integer> possible_Moves ;


    public Piece(boolean isWhite) {
        this.isWhite = isWhite;
    }
    void setPath(String path)
    {
        this.path = path;
    }
    public String getPath()
    {
        return path;
    }
    public boolean isWhite()
    {
        return this.isWhite;
    }



    public void findMoves(int index , Square[] squares, int[] gameBoard) {

    }


    public Vector<Integer> getPossibleMoves() {
        return this.possible_Moves;
    }
}
