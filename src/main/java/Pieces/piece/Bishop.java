package Pieces.piece;

public class Bishop extends Piece {
    public Bishop(boolean isWhite) {
        super(isWhite);
        if(isWhite)
        {
            super.setPath("src/sources/wb.png");
        }
        else
        {
            super.setPath("src/sources/bb.png");
        }
    }
}
