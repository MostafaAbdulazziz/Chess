package org.example;

public class PiecesLoader {
    Square[] loadPieces(Square[] squares) {

        squares[0].setPiece(new Rook(false));
        squares[1].setPiece(new Knight(false));
        squares[2].setPiece(new Bishop(false));
        squares[3].setPiece(new Queen(false));
        squares[4].setPiece(new King(false));
        squares[5].setPiece(new Bishop(false));
        squares[6].setPiece(new Knight(false));
        squares[7].setPiece(new Rook(false));
        for (int i = 8; i < 16; i++) {
            squares[i].setPiece(new Pawn(false));
        }
        for (int i = 16; i < 48; i++) {
            squares[i].setPiece(null);  // Empty squares
        }
        for (int i = 48; i < 56; i++) {
            squares[i].setPiece(new Pawn(true));
        }
        squares[56].setPiece(new Rook(true));
        squares[57].setPiece(new Knight(true));
        squares[58].setPiece(new Bishop(true));
        squares[59].setPiece(new Queen(true));
        squares[60].setPiece(new King(true));
        squares[61].setPiece(new Bishop(true));
        squares[62].setPiece(new Knight(true));
        squares[63].setPiece(new Rook(true));


        return squares;
    }
}
