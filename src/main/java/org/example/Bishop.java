package org.example;

public class Bishop extends Piece {
    public Bishop(boolean isWhite) {
        super(isWhite);
        if(isWhite)
        {
            super.setPath("C:\\Users\\mosta\\Chess\\src\\sources\\wb.png");
        }
        else
        {
            super.setPath("C:\\Users\\mosta\\Chess\\src\\sources\\bb.png");
        }
    }
}
