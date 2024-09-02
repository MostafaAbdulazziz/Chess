package org.example;

import java.util.HashMap;
import java.util.Map;

public interface PieceInitializer {
    Map<Integer,String> piecesPlaces = new HashMap<Integer,String>();
    public Map<Integer,String> initializePieces ();

}
