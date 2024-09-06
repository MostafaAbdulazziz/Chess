package Board;

import Highlighting.HighlightManager;
import Movable.Move;
import Movable.addMovements;
import Movable.testMoves;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class BoardSetup extends JLabel implements MouseListener {
    private Square[] squares = new Square[64];
    protected int[] GameBoard = new int[64];
    int from = -1;
    int to = -1;
    boolean selected = false;  // To track if a piece is selected
    PiecesLoader pieceLoader;
    HighlightManager highlightManager;
    testMoves highlightTestMoves;

    public JLabel getBoardSetup() {
        return this;
    }


    public Square[] getSquares() {
        return squares;
    }

    public void setSquares(Square[] squares) {
        this.squares = squares;
    }

    BoardSetup() {

        this.setOpaque(true);


        this.setLayout(new GridLayout(8, 8));
        this.setBorder(BorderFactory.createLineBorder(new Color(63, 39, 7), 5));
        this.setIcon(new ImageIcon("D:\\Programing\\JAVA GUI\\Board_GUI\\src\\sources\\Mainlbl.jpg"));


        this.setOpaque(false);

        Color lightWooden = new Color(222, 184, 135);
        Color darkWooden = new Color(139, 69, 19);
        Color[] colors = {lightWooden, darkWooden};
        int id = 0, cnt = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Color c = colors[id % 2];
                id++;

                Square square = new Square(cnt);
                square.setBackground(c);
                square.setOpaque(true);
                square.setHorizontalAlignment(square.CENTER);
                square.setVerticalAlignment(square.CENTER);
                squares[cnt] = square;
                this.add(square);
                cnt++;

            }
            id++;
        }


        highlightTestMoves = new testMoves();
        pieceLoader = new PiecesLoader();
        highlightManager = new HighlightManager();
        addMovements movements = new addMovements();
        squares = (addMovements.add_Movements(squares));
        squares = (pieceLoader.loadPieces(squares));
        squares = (highlightTestMoves.highlightMoves(squares));
        squares = (highlightManager.addHighlightFeature(squares));


    }


    @Override
    public void mouseClicked(MouseEvent e) {
        Square sq = (Square) this.getComponentAt(new Point(e.getX(), e.getY()));
        // First click: select the piece
        if (!selected) {
            from = sq.getIndex();  // Store the index of the square
            if (squares[from].hasPiece()) {  // Ensure there's a piece to move
                selected = true;
                System.out.println("Piece selected from square: " + from);
            }
        }
        // Second click: move the piece
        else {
            to = sq.getIndex();  // Get the destination square index
            if (from != to) {
                selected = false;
                // Perform the move and update the board
                squares = new Move(from, to, squares).getBoard();
                System.out.println("Moved piece from square " + from + " to " + to);
            }
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
