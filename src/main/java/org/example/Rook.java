package org.example;

public class Rook extends Piece {
    public Rook(boolean isWhite) {
        super(isWhite);
        if(isWhite)
        {
            super.setPath("C:\\Users\\mosta\\Chess\\src\\sources\\wr.png");
        }
        else
        {
            super.setPath("C:\\Users\\mosta\\Chess\\src\\sources\\br.png");
        }
    }
}
