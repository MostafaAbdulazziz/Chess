package org.example;

import java.util.Map;

public class Theme1PieceInitializer implements PieceInitializer {
    public Theme1PieceInitializer() {
    }

    @Override
    public Map<Integer, String> initializePieces() {
        piecesPlaces.put(0, "D:\\Programing\\JAVA GUI\\sources\\br.png");  // Black Rook
        piecesPlaces.put(1, "D:\\Programing\\JAVA GUI\\sources\\bn.png");  // Black Knight
        piecesPlaces.put(2, "D:\\Programing\\JAVA GUI\\sources\\bb.png");  // Black Bishop
        piecesPlaces.put(3, "D:\\Programing\\JAVA GUI\\sources\\bq.png");  // Black Queen
        piecesPlaces.put(4, "D:\\Programing\\JAVA GUI\\sources\\bk.png");  // Black King
        piecesPlaces.put(5, "D:\\Programing\\JAVA GUI\\sources\\bb.png");  // Black Bishop
        piecesPlaces.put(6, "D:\\Programing\\JAVA GUI\\sources\\bn.png");  // Black Knight
        piecesPlaces.put(7, "D:\\Programing\\JAVA GUI\\sources\\br.png");  // Black Rook

        for (int i = 8; i < 16; i++) {
            piecesPlaces.put(i, "D:\\Programing\\JAVA GUI\\sources\\bp.png");  // Black Pawns
        }

        // Empty squares in the middle of the board
        for (int i = 16; i < 48; i++) {
            piecesPlaces.put(i, null);  // Empty squares
        }

        // White pieces
        for (int i = 48; i < 56; i++) {
            piecesPlaces.put(i, "D:\\Programing\\JAVA GUI\\sources\\wp.png");  // White Pawns
        }

        piecesPlaces.put(56, "D:\\Programing\\JAVA GUI\\sources\\wr.png");  // White Rook
        piecesPlaces.put(57, "D:\\Programing\\JAVA GUI\\sources\\wn.png");  // White Knight
        piecesPlaces.put(58, "D:\\Programing\\JAVA GUI\\sources\\wb.png");  // White Bishop
        piecesPlaces.put(59, "D:\\Programing\\JAVA GUI\\sources\\wq.png");  // White Queen
        piecesPlaces.put(60, "D:\\Programing\\JAVA GUI\\sources\\wk.png");  // White King
        piecesPlaces.put(61, "D:\\Programing\\JAVA GUI\\sources\\wb.png");  // White Bishop
        piecesPlaces.put(62, "D:\\Programing\\JAVA GUI\\sources\\wn.png");  // White Knight
        piecesPlaces.put(63, "D:\\Programing\\JAVA GUI\\sources\\wr.png");  // White rock

        return piecesPlaces;

    }
}
