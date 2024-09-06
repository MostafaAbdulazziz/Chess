package Pieces.piece;

public class Pawn extends Piece { ;
    public Pawn(boolean isWhite) {
        super(isWhite);
        if(isWhite)
        {
            super.setPath("C:\\Users\\mosta\\Chess\\src\\sources\\wp.png");
        }
        else
        {
            super.setPath("C:\\Users\\mosta\\Chess\\src\\sources\\bp.png");
        }
    }
}
