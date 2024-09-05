package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class BoardSetup {
    private JFrame frame;
    private Square[] squares = new Square[64];
    protected int[] GameBoard = new int[64];
    private JLabel mainLabel, backgroundLabel;
    private Map<Integer, String> PiecesPlaces = new HashMap<Integer, String>();

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public Map<Integer, String> getMap() {
        return PiecesPlaces;
    }

    public void setMap(Map<Integer, String> map) {
        this.PiecesPlaces = map;
    }

    public JLabel getMainLabel() {
        return mainLabel;
    }

    public void setMainLabel(JLabel mainLabel) {
        this.mainLabel = mainLabel;
    }

    public Square[] getSquares() {
        return squares;
    }

    public void setSquares(Square[] squares) {
        this.squares = squares;
    }

    BoardSetup() {
        frame = new JFrame();
        mainLabel = new JLabel();
        backgroundLabel = new JLabel();
        mainLabel.setOpaque(true);

        frame.setLayout(new BorderLayout());
        frame.setTitle("Board");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1500, 820);


        mainLabel.setLayout(new GridLayout(8, 8));
        mainLabel.setBounds(390, 50, 720, 720);
        mainLabel.setBorder(BorderFactory.createLineBorder(new Color(63, 39, 7), 5));
        mainLabel.setIcon(new ImageIcon("D:\\Programing\\JAVA GUI\\Board_GUI\\src\\sources\\Mainlbl.jpg"));


        backgroundLabel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
        backgroundLabel.setIcon(new ImageIcon("D:\\Programing\\JAVA GUI\\Board_GUI\\src\\sources\\galaxy (2).jpg"));
        mainLabel.setOpaque(false);
        backgroundLabel.add(mainLabel);

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
                mainLabel.add(square);
                cnt++;

            }
            id++;
        }

        frame.setContentPane(backgroundLabel);
        frame.setResizable(false);


        frame.setLocationRelativeTo(null);  //--> to make window open in middle
    }


    public void displayBoard() {
        frame.setVisible(true);

    }
}
