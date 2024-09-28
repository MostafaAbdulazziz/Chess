package Board;

import Pieces.Piece;

import javax.swing.*;
import java.awt.*;

public class Square extends JLabel  {
    private int index;
    private boolean isHighlighted;
    private boolean isOccupied;
    private Piece piece;
    private boolean isWhite;
    private Color originalColor;


    public Square(int index) {
        this.index = index;
        this.isHighlighted = false;
        this.isOccupied = false;
        this.piece = null;

    }

    public void setOriginalColor(Color color) {
        this.originalColor = color;
        this.setBackground(color); // Set the initial background color
    }

    public Color getOriginalColor() {
        return originalColor;
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

    public void removePiece() {
        this.piece = null;
        this.setIcon(null);
    }
    public ImageIcon getIcon() {
        return (ImageIcon) super.getIcon();
    }



}
