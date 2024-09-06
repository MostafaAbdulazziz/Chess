package Game;

import Board.BoardSetup;

import javax.swing.*;

public class GameWindow extends JFrame {
    private BoardSetup board;
    JLabel backgroundLabel;

    public GameWindow() {
        this.board = new BoardSetup();
        backgroundLabel = new JLabel();
        this.setSize(1500, 820);
        this.setLayout(null);
        board.setBounds(390, 50, 720, 720);
        backgroundLabel.add(board);
        this.add(backgroundLabel);
        backgroundLabel.setBounds(0, 0, this.getWidth(), this.getHeight());
        backgroundLabel.setIcon(new ImageIcon("C:\\Users\\mosta\\Chess\\src\\sources\\galaxy (2).jpg"));
        this.setTitle("Board");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);


    }
    void displayBoard() {
        this.setVisible(true);
    }
    void setBoard(BoardSetup board) {
        this.board = board;
    }
    BoardSetup getBoard() {
        return this.board;
    }
}
