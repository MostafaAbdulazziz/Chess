package Pieces.piece;

public class Queen  extends Piece {

    public Queen(boolean isWhite) {
        super(isWhite);
        if(isWhite)
        {
            super.setPath("src\\sources\\wq.png");
        }
        else
        {
            super.setPath("src\\sources\\bq.png");
        }
    }
}
