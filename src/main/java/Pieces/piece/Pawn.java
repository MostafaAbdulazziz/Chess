package Pieces.piece;

import Board.Square;

import java.util.Vector;

public class Pawn extends Piece {
    boolean isFirstMove = true;

    public Pawn(boolean isWhite) {
        super(isWhite);
        if (isWhite) {
            super.setPath("src\\sources\\wp.png");
        } else {
            super.setPath("src\\sources\\bp.png");
        }
    }

    @Override
    public void findMoves(int index , Square[] squares, int[] gameBoard) {

        super.possible_Moves = new Vector<Integer>();
        if (isWhite) {
            if (index > 16 && index < 48) {
                super.possible_Moves.add(index - 8);

            } else {
                if (index - 8 >= 0)
                    super.possible_Moves.add(index - 8);
                if (index - 16 >= 0)
                    super.possible_Moves.add(index - 16);
            }
        } else {
            if (index > 16 && index < 48) {
                if (index + 8 >= 0)
                    super.possible_Moves.add(index + 8);

            } else {
                if (index + 8 >= 0)
                    super.possible_Moves.add(index + 8);
                if (index + 16 >= 0)
                    super.possible_Moves.add(index + 16);
            }
        }
    }

    @Override
    public Vector<Integer> getPossibleMoves() {
        return super.possible_Moves;
    }
}
