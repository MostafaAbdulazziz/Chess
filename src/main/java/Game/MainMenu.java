package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame implements ActionListener {
    private final CardLayout cardLayout;
    private final JPanel mainPanel;
    JButton button1;
    JButton button2;
    JButton button3;
    JButton button4;
    private final Image backgroundImage = new ImageIcon("src\\sources\\ChessWallpaper.jpg").getImage();

    public MainMenu() {
        // Set up the main frame
        Dimension sz = new Dimension(1000, 800);
        this.setSize(sz);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // CardLayout to switch between main menu and chess game
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Create main menu panel
        JPanel menuPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };

        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));

        button1 = createButton("1 Player");
        button2 = createButton("2 Players");
        button3 = createButton("Online");
        button4 = createButton("Exit");
        button1.setFocusable(false);
        button2.setFocusable(false);
        button3.setFocusable(false);
        button4.setFocusable(false);

        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        button4.addActionListener(this);

        // Add vertical spacing (margins)
        int topMargin = 80;
        int verticalSpacing = 50;
        int bottomMargin = 200;

        menuPanel.add(Box.createRigidArea(new Dimension(50, topMargin)));
        menuPanel.add(button1);
        menuPanel.add(Box.createRigidArea(new Dimension(0, verticalSpacing)));
        menuPanel.add(button2);
        menuPanel.add(Box.createRigidArea(new Dimension(0, verticalSpacing)));
        menuPanel.add(button3);
        menuPanel.add(Box.createRigidArea(new Dimension(0, verticalSpacing)));
        menuPanel.add(button4);
        menuPanel.add(Box.createRigidArea(new Dimension(0, bottomMargin)));

        // Add panels to the main panel
        GameWindow chessPanel = new GameWindow(cardLayout, mainPanel); // Pass cardLayout and mainPanel
        mainPanel.add(menuPanel, "MainMenu");
        mainPanel.add(chessPanel, "ChessGame");

        this.add(mainPanel);
        this.setVisible(true);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(150, 40));
        button.setMaximumSize(new Dimension(150, 40)); // set maximum size
        button.setMinimumSize(new Dimension(150, 40)); // set minimum size
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button2) {
            // Switch to ChessGame panel and show the timer setup dialog
            cardLayout.show(mainPanel, "ChessGame");

            // Show the timer setup dialog when switching to the game
            GameWindow chessGame = (GameWindow) mainPanel.getComponent(1); // Get the GameWindow component
            chessGame.showTimerSetupDialog(); // Call the method to show the timer setup dialog
        }
        if (e.getSource() == button4) {
            System.exit(0); // Close the application
        }
    }
}
