package org.example;

public class King extends Piece {
    public King(boolean isWhite) {
        super(isWhite);
        if(isWhite)
        {
            super.setPath("C:\\Users\\mosta\\Chess\\src\\sources\\wk.png");
        }
        else
        {
            super.setPath("C:\\Users\\mosta\\Chess\\src\\sources\\bk.png");
        }
    }
}
