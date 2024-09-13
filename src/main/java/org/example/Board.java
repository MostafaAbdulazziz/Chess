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
    PieceInitializer  pieceInitializer ;
    Board(){
        Board = new BoardSetup();
        highlightTestMoves = new testMoves();
        PieceInitializer theme1PieceInitializer = new Theme1PieceInitializer(); //Polymorphism
        Map<Integer,String>pieces = theme1PieceInitializer.initializePieces();
        pieceLoader = new PiecesLoader();
        highlightManager = new HighlightManager();
        Board.setMap(pieces);
        Board.setSquares(pieceLoader.loadPieces(Board.getSquares(),pieces));
        Board.setSquares( highlightManager.addHighlightFeature(Board.getSquares()));
        Board.setSquares(highlightTestMoves.highlightMoves(Board.getSquares()));



    }
    public void displayBoard(){
        Board.displayBoard();
    }

}
