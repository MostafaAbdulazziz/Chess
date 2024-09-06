package Board;

import Pieces.piece.Piece;

import javax.swing.*;

public class Square extends JLabel {
    private int index;
    private boolean isHighlighted;
    private boolean isOccupied;
    private Piece piece;

    public Square(int index) {
        this.index = index;
        this.isHighlighted = false;
        this.isOccupied = false;
        this.piece = null;
    }

    public int getIndex() {
        return index;
    }

    public boolean isHighlighted() {
        return isHighlighted;
    }

    public void setHighlighted(boolean highlighted) {
        isHighlighted = highlighted;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
        if(piece != null)
             this.setIcon(new ImageIcon(piece.getPath()));
        else
            this.setIcon(null);
    }

    public boolean hasPiece() {
        return piece != null;
    }
}
