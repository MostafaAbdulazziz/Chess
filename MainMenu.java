
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame implements ActionListener {
    JPanel menuPanel;
    JButton button1;
    JButton button2;
    JButton button3;
    JButton button4;
    private final Image backgroundImage = new ImageIcon("D:\\mainTests\\src\\ChessWallpaper (1).jpg").getImage();

    public MainMenu() {
        Dimension sz = new Dimension(600, 600);
        this.setSize(sz);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        menuPanel = new JPanel() {
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

        //add vertical spacing (margins)
        int topMargin = 80;
        int verticalSpacing = 50; //space between buttons
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

        this.add(menuPanel);
        this.setVisible(true);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(150, 40));
        button.setMaximumSize(new Dimension(150, 40));   //set maximum size
        button.setMinimumSize(new Dimension(150, 40));   //set minimum size
        return button;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==button1){
            this.dispose();// to destroy the first window
            //add the instance of the game window
        }
    }
}