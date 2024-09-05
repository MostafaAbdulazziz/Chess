package org.example;

public class Queen  extends Piece{

    public Queen(boolean isWhite) {
        super(isWhite);
        if(isWhite)
        {
            super.setPath("C:\\Users\\mosta\\Chess\\src\\sources\\wq.png");
        }
        else
        {
            super.setPath("C:\\Users\\mosta\\Chess\\src\\sources\\bq.png");
        }
    }
}
