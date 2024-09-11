package Board;

import Game.GameWindow;
import Highlighting.HighlightManager;
import Movable.Move;
import Pieces.piece.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.Vector;

public class BoardSetup extends JLabel {
    private Square[] squares = new Square[64];
    protected int[] GameBoard = new int[64];
    GameWindow gameWindow;
    int from = -1;
    int to = -1;
    boolean selected = false;  // To track if a piece is selected
    boolean isWhiteTurn = true;  // True means it's white's turn, false means it's black's turn
    PiecesLoader pieceLoader;
    HighlightManager highlightManager;
    Vector<Integer> prevPossibleMoves;

    // Add a callback reference to the GameWindow to switch the timer
    private Runnable onTurnSwitch;

    public JLabel getBoardSetup() {
        return this;
    }

    public Square[] getSquares() {
        return squares;
    }

    public void setSquares(Square[] squares) {
        this.squares = squares;
    }

    public BoardSetup(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
        this.setOpaque(true);
        this.setLayout(new GridLayout(8, 8));
        this.setBorder(BorderFactory.createLineBorder(new Color(63, 39, 7), 5));
        this.setIcon(new ImageIcon("src\\sources\\Mainlbl.jpg"));
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
        squares = pieceLoader.loadPieces(squares);
        squares = highlightManager.addHighlightFeature(squares);
        GameBoard = pieceLoader.loadPositions(GameBoard);
        prevPossibleMoves = new Vector<>();
    }

    // Setter method to allow the GameWindow to pass the turn-switching callback
    public void setOnTurnSwitch(Runnable onTurnSwitch) {
        this.onTurnSwitch = onTurnSwitch;
    }

    private void handleSquareClick(Square square) {
        int index = square.getIndex();

        if (!selected) {
            from = index;
            if (squares[from].hasPiece()) {
                // Check if the piece matches the player's turn
                if ((squares[from].getPiece().isWhite() && isWhiteTurn) ||
                        (!squares[from].getPiece().isWhite() && !isWhiteTurn)) {
                    selected = true;
                    System.out.println("Piece selected from square: " + from);
                    squares[from].getPiece().findMoves(from, squares, GameBoard);
                    new HashSet<>(prevPossibleMoves).containsAll(squares[from].getPiece().getPossibleMoves());
                    for (int move : squares[from].getPiece().getPossibleMoves()) {
                        squares[move].setBorder(BorderFactory.createLineBorder(Color.green, 6));
                    }
                    updateBoard();
                } else {
                    System.out.println("Not your turn! It's " + (isWhiteTurn ? "white" : "black") + "'s turn.");
                }
            }
        } else {
            to = index;
            selected = false;

            // Clear the borders after move selection
            for (int i = 0; i < 64; i++) {
                squares[i].setBorder(null);
            }

            if (from != to && squares[from].getPiece().getPossibleMoves().contains(to)) {
                Move move = new Move(from, to, squares, GameBoard);
                squares = move.getBoard();
                this.GameBoard = move.getGameBoard();
                updateBoard();

                System.out.println("Moved piece from square " + from + " to " + to);

                // Switch turns after a successful move
                if (move.isBoardChanged()) {
                    isWhiteTurn = !isWhiteTurn;
                }
                System.out.println("Now it's " + (isWhiteTurn ? "white" : "black") + "'s turn.");

                // Notify the GameWindow to switch the timers
                if (onTurnSwitch != null && move.isBoardChanged()) {
                    onTurnSwitch.run();
                }
                if (move.isBoardChanged()) {
                    isCheckmate(squares[to].getPiece().isWhite());

                }if (move.isBoardChanged()) {
                    isCheck(squares[to].getPiece().isWhite());

                }


            }
            checkPawnPromotionAfterMove(to);
        }
    }

    private void checkPawnPromotionAfterMove(int index) {
        Piece piece = squares[index].getPiece();
        if (piece instanceof Pawn) {
            if ((piece.isWhite() && index / 8 == 0) || (!piece.isWhite() && index / 8 == 7)) {
                // Use showOptionDialog to present options as buttons
                String[] options = {"Queen", "Rook", "Bishop", "Knight"};
                int choice = JOptionPane.showOptionDialog(this, "Promote your pawn to:", "Pawn Promotion", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

                // Default to Queen if the dialog is closed or no selection is made
                String promotionChoice = (choice >= 0 && choice < options.length) ? options[choice].toLowerCase() : "queen";

                Piece newPiece;
                switch (promotionChoice) {
                    case "rook":
                        newPiece = new Rook(piece.isWhite());
                        break;
                    case "bishop":
                        newPiece = new Bishop(piece.isWhite());
                        break;
                    case "knight":
                        newPiece = new Knight(piece.isWhite());
                        break;
                    case "queen":
                    default:
                        newPiece = new Queen(piece.isWhite());  // Default promotion is to Queen
                        break;
                }

                // Replace the pawn with the chosen piece
                squares[index].setPiece(newPiece);

                // Refresh the board UI
                updateBoard();
            }
        }
    }


    // Update board UI after move
    public void updateBoard() {
        this.removeAll();
        for (Square square : squares) {
            this.add(square);
        }
        this.revalidate();
        this.repaint();
    }

    public int[] getGameBoard() {
        return GameBoard;
    }

    void isCheckmate(boolean isWhite) {
        int KingIdx = 0;
        for (int i = 0; i < 64; i++) {
            if ((isWhite && GameBoard[i] == 66) || (!isWhite && GameBoard[i] == 6)) {
                KingIdx = i;
                break;
            }
        }
        if (squares[KingIdx].getPiece() != null)
            squares[KingIdx].getPiece().findMoves(KingIdx, squares, GameBoard);
        if (squares[KingIdx].getPiece() != null && squares[KingIdx].getPiece().getPossibleMoves().isEmpty() && squares[KingIdx].getPiece().isChecked(KingIdx, squares, GameBoard, squares[KingIdx].getPiece().isWhite())) {
            squares[KingIdx].setBackground(Color.RED);
            System.out.println("Checkmate");
            updateBoard();
            if(squares[KingIdx].getPiece().isWhite()){
                gameWindow.endGame("Black Wins");
                gameWindow.restartTimers();
            }
            else{
                gameWindow.endGame("White Wins");
                gameWindow.restartTimers();

            }


        }

    }
    void isCheck(boolean isWhite) {
        int KingIdx = 0;
        for (int i = 0; i < 64; i++) {
            if ((isWhite && GameBoard[i] == 66) || (!isWhite && GameBoard[i] == 6)) {
                KingIdx = i;
                break;
            }
        }
        if (squares[KingIdx].getPiece() != null)
            squares[KingIdx].getPiece().findMoves(KingIdx, squares, GameBoard);
        if (squares[KingIdx].getPiece() != null &&  squares[KingIdx].getPiece().isChecked(KingIdx, squares, GameBoard, squares[KingIdx].getPiece().isWhite())) {
            squares[KingIdx].setBorder(BorderFactory.createLineBorder(Color.RED, 6));
            System.out.println("Check");
            updateBoard();

            }


        }

    }









