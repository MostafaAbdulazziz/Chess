package Game;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;

// Custom FuturisticButton class with hover effect
public class FuturisticButton extends JButton {
    private boolean hover = false;

    public FuturisticButton(String text) {
        super(text);
        setUI(new BasicButtonUI());
        setPreferredSize(new Dimension(150, 40));  // Adjusted size
        setFont(new Font("Arial", Font.BOLD, 16));
        setFocusPainted(false);
        setOpaque(false);
        setContentAreaFilled(false);
        setBorder(BorderFactory.createEmptyBorder());

        // Hover effect
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                hover = true;
                setBorder(BorderFactory.createLineBorder(Color.CYAN, 2)); // Glowing cyan effect
                repaint();
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                hover = false;
                setBorder(BorderFactory.createEmptyBorder());
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Button gradient (similar to the theme)
        Color color1 = hover ? new Color(0, 255, 255) : new Color(0, 128, 255);  // Lighter on hover
        Color color2 = hover ? new Color(0, 255, 255, 100) : new Color(0, 128, 255, 100);  // Light cyan glow

        GradientPaint gp = new GradientPaint(0, 0, color1, 0, getHeight(), color2);
        g2d.setPaint(gp);

        // Draw an oval (rounded button)
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 50, 50);

        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw rounded border
        g2d.setColor(new Color(0, 128, 255));
        g2d.setStroke(new BasicStroke(2));
        g2d.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 50, 50);
    }
}
