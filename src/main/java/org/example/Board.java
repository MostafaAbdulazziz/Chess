package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

public class Board {
    BoardSetup Board ;
    PiecesLoader pieceLoader ;
    HighlightManager highlightManager ;
    testMoves highlightTestMoves ;
    Board(){
        Board = new BoardSetup();
        highlightTestMoves = new testMoves();
        pieceLoader = new PiecesLoader();
        highlightManager = new HighlightManager();
        addMovements movements = new addMovements();
        Board.setSquares(movements.add_Movements(Board.getSquares()));
        Board.setSquares(pieceLoader.loadPieces(Board.getSquares()));
        Board.setSquares(highlightTestMoves.highlightMoves(Board.getSquares()));
        Board.setSquares( highlightManager.addHighlightFeature(Board.getSquares()));



    }
    public void displayBoard(){
        Board.displayBoard();
    }

}
