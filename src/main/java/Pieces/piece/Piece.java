package Pieces.piece;

public class Piece  {
    boolean isWhite;
    String path;


    public Piece(boolean isWhite) {
        this.isWhite = isWhite;
    }
    void setPath(String path)
    {
        this.path = path;
    }
    String getPath()
    {
        return path;
    }


}
