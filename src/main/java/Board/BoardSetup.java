package Board;

import Highlighting.HighlightManager;
import Movable.Move;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.Vector;

public class BoardSetup extends JLabel{
    private Square[] squares = new Square[64];
    protected int[] GameBoard = new int[64];
    int from = -1;
    int to = -1;
    boolean selected = false;  // To track if a piece is selected
    PiecesLoader pieceLoader;
    HighlightManager highlightManager;
    Vector<Integer>prevPossibleMoves;

    public JLabel getBoardSetup() {
        return this;
    }

    public Square[] getSquares() {
        return squares;
    }

    public void setSquares(Square[] squares) {
        this.squares = squares;
    }

    public BoardSetup() {
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
                square.setText(String.valueOf(cnt));
                squares[cnt] = square;

                square.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        handleSquareClick(square);
                    }
                });



                this.add(square);
                cnt++;
            }
            id++;
        }

        // Load pieces and highlight movement functionality

        pieceLoader = new PiecesLoader();
        highlightManager = new HighlightManager();
        squares = (pieceLoader.loadPieces(squares));
        squares = (highlightManager.addHighlightFeature(squares));
        GameBoard = pieceLoader.loadPositions(GameBoard);
        prevPossibleMoves = new Vector<Integer>();

        // A

    }

    private void handleSquareClick(Square square) {
        int index = square.getIndex();

        if (!selected) {

            from = index;
            if (squares[from].hasPiece()) {
                selected = true;
                System.out.println("Piece selected from square: " + from);
                squares[index].getPiece().findMoves(index,squares,GameBoard);
                new HashSet<>(prevPossibleMoves).containsAll(squares[index].getPiece().getPossibleMoves());

                for(int move : squares[index].getPiece().getPossibleMoves())
                {
                    squares[move].setBorder(BorderFactory.createLineBorder(Color.GREEN,3));
                }
                updateBoard();

            }
        } else {
            to = index;
                selected = false;
                for (int i =0 ; i<64;i++)
                    squares[i].setBorder(null);
                updateBoard();
            if (from != to  ) {
                Move move = new Move(from, to, squares,GameBoard);
                squares = move.getBoard();
                this.GameBoard = move.getGameBoard();
                updateBoard();

                System.out.println("Moved piece from square " + from + " to " + to);
            }
        }
    }

    // Update board UI after move
    private void updateBoard() {
        this.removeAll();
        for (Square square : squares) {
            this.add(square);
        }
        this.revalidate();
        this.repaint();
    }
}
