package Game;

import Board.BoardSetup;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameWindow extends JPanel {
    private BoardSetup board;
    private JLabel backgroundLabel;
    private JButton exitGameButton;
    private JPanel sidePanel;

    // Timer variables
    private JLabel whiteTimerLabel;
    private JLabel blackTimerLabel;
    private Timer whiteTimer;
    private Timer blackTimer;
    private int whiteTimeRemaining; // Time in seconds for white
    private int blackTimeRemaining; // Time in seconds for black
    private boolean isWhiteTurn = true; // To track the current player's turn

    public GameWindow(CardLayout cardLayout, JPanel mainPanel) {
        this.board = new BoardSetup();

        backgroundLabel = new JLabel();
        this.setLayout(null);

        // Set up the chess board, shifted to the left
        board.setBounds(200, 50, 720, 720); // Shifted to the left to make room for the side panel
        backgroundLabel.add(board);

        // Create side panel for the Exit Game button and timers on the left
        sidePanel = new JPanel();
        sidePanel.setLayout(null);
        sidePanel.setBounds(0, 0, 200, 820); // Left side panel, within the background bounds
        sidePanel.setBackground(new Color(224, 171, 124)); // Light background for the side panel

        // Add Exit Game button
        exitGameButton = new JButton("Exit Game");
        exitGameButton.setBounds(20, 50, 160, 30); // Button in the side panel
        exitGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Show a confirmation dialog when the Exit Game button is clicked
                int result = JOptionPane.showConfirmDialog(
                        GameWindow.this,
                        "Are you sure you want to exit the game?",
                        "Exit Game",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE

                );

                if (result == JOptionPane.YES_OPTION) {
                    resetBoard(); // Reset the board to its initial state
                    cardLayout.show(mainPanel, "MainMenu");
                }
            }
        });
        sidePanel.add(exitGameButton);

        JButton restartGameButton = new JButton("Restart Game");
        restartGameButton.setBounds(20, 200, 160, 30);
        restartGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Show a confirmation dialog when the Restart Game button is clicked
                int result = JOptionPane.showConfirmDialog(
                        GameWindow.this,
                        "Are you sure you want to restart the game?",
                        "Restart Game",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE

                );

                if (result == JOptionPane.YES_OPTION) {
                    resetBoard(); // Reset the board to its initial state
                    restartTimers();
                }
            }
        });
        sidePanel.add(restartGameButton);



        // White player timer
        whiteTimerLabel = new JLabel("White: 00:00");
        whiteTimerLabel.setBounds(20, 100, 160, 30);
        whiteTimerLabel.setFont(new Font("MV Boli", Font.BOLD, 16));
        whiteTimerLabel.setForeground(Color.BLACK);
        whiteTimerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        sidePanel.add(whiteTimerLabel);

        // Black player timer
        blackTimerLabel = new JLabel("Black: 00:00");
        blackTimerLabel.setBounds(20, 150, 160, 30);
        blackTimerLabel.setFont(new Font("MV Boli", Font.BOLD, 16));
        blackTimerLabel.setForeground(Color.BLACK);
        blackTimerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        sidePanel.add(blackTimerLabel);

        // Set background properties
        backgroundLabel.setBounds(0, 0, 1000, 820);
        backgroundLabel.setIcon(new ImageIcon("src\\sources\\galaxy (2).jpg"));

        // Add components to the main panel
        this.add(sidePanel);
        this.add(backgroundLabel);

        // Set up board logic (turn switch handling)
        board.setOnTurnSwitch(this::switchTurn);
    }

    // Show the pop-up window to set the timer duration before the game starts
    public void showTimerSetupDialog() {
        // Ask the user for the time in minutes
        String input = JOptionPane.showInputDialog(
                this,
                "Set timer (in minutes) for each player:",
                "Timer Setup",
                JOptionPane.QUESTION_MESSAGE
        );

        if (input != null && !input.isEmpty()) {
            try {
                int minutes = Integer.parseInt(input);
                // Convert minutes to seconds and set it
                whiteTimeRemaining = blackTimeRemaining = minutes * 60;

                // Initialize the timers with the user-defined time
                initializeTimers();

            } catch (NumberFormatException e) {
                // Show error and re-open the dialog if input is invalid
                JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid number of minutes.", "Error", JOptionPane.ERROR_MESSAGE);
                showTimerSetupDialog(); // Retry timer setup
            }
        } else {
            // Default time if no input is provided
            whiteTimeRemaining = blackTimeRemaining = 600; // 10 minutes default
            initializeTimers();
        }
    }

    // Initialize timers for both players
    private void initializeTimers() {
        whiteTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                whiteTimeRemaining--;
                updateTimerLabel(whiteTimerLabel, whiteTimeRemaining);

                if (whiteTimeRemaining <= 0) {
                    whiteTimer.stop();
                    blackTimer.stop();
                    endGame("Black wins! White ran out of time.");
                }
            }
        });

        blackTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                blackTimeRemaining--;
                updateTimerLabel(blackTimerLabel, blackTimeRemaining);

                if (blackTimeRemaining <= 0) {
                    whiteTimer.stop();
                    blackTimer.stop();
                    endGame("White wins! Black ran out of time.");
                }
            }
        });

        // Start the white timer by default
        updateTimerLabel(whiteTimerLabel, whiteTimeRemaining);
        updateTimerLabel(blackTimerLabel, blackTimeRemaining);
        whiteTimer.start();
    }

    // Helper method to format and update the timer label
    private void updateTimerLabel(JLabel timerLabel, int timeRemaining) {
        int minutes = timeRemaining / 60;
        int seconds = timeRemaining % 60;
        timerLabel.setText(String.format("%s: %02d:%02d",
                timerLabel.getText().contains("White") ? "White" : "Black", minutes, seconds));
    }

    // Switch timer between players
    public void switchTurn() {
        if (isWhiteTurn) {
            whiteTimer.stop();  // Stop white's timer
            blackTimer.start();  // Start black's timer
        } else {
            blackTimer.stop();  // Stop black's timer
            whiteTimer.start();  // Start white's timer
        }
        isWhiteTurn = !isWhiteTurn;  // Toggle the turn
    }

    // Method to reset the chess board
    private void resetBoard() {
        this.board = new BoardSetup();
        board.setBounds(200, 50, 720, 720);
        backgroundLabel.removeAll();
        backgroundLabel.add(board);
        backgroundLabel.revalidate();
        backgroundLabel.repaint();

        // Reset timers to default or user-defined value

    }
    public void restartTimers()
    {
        whiteTimer.stop();
        blackTimer.stop();
        this.showTimerSetupDialog();
    }

    // End the game when time runs out
    private void endGame(String message) {
        JOptionPane.showMessageDialog(this, message, "Game Over", JOptionPane.INFORMATION_MESSAGE);
        resetBoard();
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
