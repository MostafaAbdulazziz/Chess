package Board;

import Game.GameWindow;
import Highlighting.HighlightManager;
import Movable.Move;
import Pieces.piece.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
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
        this.setOpaque(false);
        this.setLayout(new GridLayout(8, 8));


        Color lightWooden = new Color(222, 184, 135);
        Color darkWooden = new Color(139, 69, 19);


        Color[] colors = {lightWooden, darkWooden};
        int id = 0, cnt = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Color c = colors[id % 2];
                id++;

                Square square = new Square(cnt);
                square.setOriginalColor(c); // Store original color here
                square.setOpaque(true);
                square.setBackground(c); // Set the initial background color
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
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
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

            for (int i = 0; i < 64; i++) {
                Border border = (Border) squares[i].getBorder();
                if (border != null) {
                    Color borderColor = ((LineBorder) border).getLineColor();
                    if (borderColor != Color.red)
                        squares[i].setBorder(null);
                }
            }

            // Castling detection
            if (from != to && squares[from].getPiece() instanceof King &&Math.abs(from - to) == 2 &&squares[from].getPiece().getPossibleMoves().contains(to)) {
                // Move the king
                moveKingForCastling(from, to, squares);

                // Move the rook
                if (to - from == 2) { // King-side castling
                    moveRookForCastling(from, from + 3, from + 1, squares);
                } else if (to == from - 2) { // Queen-side castling
                    moveRookForCastling(from, from - 4, from - 1, squares);
                }

                updateBoard(); // Ensure the board is redrawn

                isWhiteTurn = !isWhiteTurn; // Switch turns
                if (onTurnSwitch != null) {
                    onTurnSwitch.run();
                }
                return;
            }

            if (from != to && squares[from].getPiece().getPossibleMoves().contains(to)) {
                Move move = new Move(from, to, squares, GameBoard);
                squares = move.getBoard();
                this.GameBoard = move.getGameBoard();

                // Check if the piece moved is the king or rook and call movePiece()
                if (squares[to].getPiece() instanceof King) {
                    ((King) squares[to].getPiece()).movePiece(); // Mark the king as moved
                } else if (squares[to].getPiece() instanceof Rook) {
                    ((Rook) squares[to].getPiece()).movePiece(); // Mark the rook as moved
                }
                updateBoard();

                System.out.println("Moved piece from square " + from + " to " + to);

                // Switch turns after a successful move
                if (move.isBoardChanged() && onTurnSwitch != null) {
                    isWhiteTurn = !isWhiteTurn;
                    onTurnSwitch.run();
                    for (int i = 0; i < 64; i++) {
                        squares[i].setBorder(null);
                    }
                }
                System.out.println("Now it's " + (isWhiteTurn ? "white" : "black") + "'s turn.");

                // Notify the GameWindow to switch the timers
//                if (onTurnSwitch != null ) {
//                    onTurnSwitch.run();
//                }
                boolean isCheck = false;
                checkPawnPromotionAfterMove(to);
                if (move.isBoardChanged()) {
                    isCheck(!squares[to].getPiece().isWhite());

                }
                if (move.isBoardChanged()) {
                    isCheckmate(!squares[to].getPiece().isWhite());

                }
            }
        }
    }

    private void moveRookForCastling(int kingIndex, int rookFrom, int rookTo, Square[] squares) {
        Piece rook = squares[rookFrom].getPiece();
        squares[rookTo].setPiece(rook);
        squares[rookFrom].removePiece(); // Remove rook from its original position
        GameBoard[rookTo] = GameBoard[rookFrom]; // Update the game board
        GameBoard[rookFrom] = 100; // Mark original rook position as empty
        if (rook instanceof Rook) {
            ((Rook) rook).movePiece(); // Call movePiece to update the hasMoved flag
        }
    }


    private void moveKingForCastling(int kingFrom, int kingTo, Square[] squares) {
        Piece king = squares[kingFrom].getPiece();
        squares[kingTo].setPiece(king);
        squares[kingFrom].removePiece(); // Clear the original position
        GameBoard[kingTo] = GameBoard[kingFrom];
        GameBoard[kingFrom] = 100; // Mark original king position as empty

        // Mark the king as moved
        if (king instanceof King) {
            ((King) king).movePiece();
        }
        updateBoard();
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
            if ((isWhite && GameBoard[i] == 6) || (!isWhite && GameBoard[i] == 66)) {
                KingIdx = i;
                break;
            }
        }
        Square[] tempSquares = new Square[64];
        int[] tempGameBoard = new int[64];
        for (int i = 0; i < 64; i++) {
            tempSquares[i] = new Square(i);
            tempSquares[i].setPiece(squares[i].getPiece());
            tempGameBoard[i] = GameBoard[i];
        }

        King king = (King) squares[KingIdx].getPiece();
        boolean isCheckmate = true;
        if (king != null)
            king.findMoves(KingIdx, squares, GameBoard);
        if (king != null && king.getPossibleMoves().isEmpty() && king.isChecked(KingIdx, tempSquares, tempGameBoard, king.isWhite())) {
            {
                for (int i = 0; i < 64; i++) {
                    if (tempSquares[i].getPiece() != null && squares[i].getPiece().isWhite() == isWhite) {
                        tempSquares[i].getPiece().findMoves(i, tempSquares, tempGameBoard);
                        for (int move : squares[i].getPiece().getPossibleMoves()) {
                            Move move1 = new Move(i, move, tempSquares, tempGameBoard);
                            if (move1.isMoveValid(i, move, tempSquares, tempGameBoard)) {
                                isCheckmate = false;
                                break;
                            }
                        }
                    }
                }
            }
            if (isCheckmate) {
                squares[KingIdx].setBackground(Color.RED);
                System.out.println("Checkmate");
                updateBoard();
                if (squares[KingIdx].getPiece().isWhite()) {
                    gameWindow.endGame("Black Wins");
//                gameWindow.restartTimers();
                    gameWindow.stopTimers();
                } else {
                    gameWindow.endGame("White Wins");
//                gameWindow.restartTimers();
                    gameWindow.stopTimers();

                }
            }


        }

    }

    void isCheck(boolean isWhite) {
        int KingIdx = 0;
        for (int i = 0; i < 64; i++) {
            if ((isWhite && GameBoard[i] == 6) || (!isWhite && GameBoard[i] == 66)) {
                KingIdx = i;
                break;
            }
        }
        Square[] tempSquares = new Square[64];
        int[] tempGameBoard = new int[64];
        for (int i = 0; i < 64; i++) {
            tempSquares[i] = new Square(i);
            tempSquares[i].setPiece(squares[i].getPiece());
            tempGameBoard[i] = GameBoard[i];
        }
        if (squares[KingIdx].getPiece() != null)
            squares[KingIdx].getPiece().findMoves(KingIdx, tempSquares, tempGameBoard);
        King king = (King) squares[KingIdx].getPiece();
        if (king != null && king.isChecked(KingIdx, tempSquares, tempGameBoard, king.isWhite())) {
            squares[KingIdx].setBorder(BorderFactory.createLineBorder(Color.RED, 6));
            System.out.println("Check");
            updateBoard();

        }


    }

    public void restartGame() {
        this.removeAll();
        squares = pieceLoader.loadPieces(squares);
        squares = highlightManager.addHighlightFeature(squares);
        GameBoard = pieceLoader.loadPositions(GameBoard);
        updateBoard();
        isWhiteTurn = true;
    }
}









