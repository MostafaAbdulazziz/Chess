package Pieces.piece;

import Board.Square;
import java.util.Vector;

public class King extends Piece {


    public King(boolean isWhite) {
        super(isWhite);
        if (isWhite) {
            super.setPath("src\\sources\\wk.png");
        } else {
            super.setPath("src\\sources\\bk.png");
        }
    }
}
