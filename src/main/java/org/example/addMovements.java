package org.example;

import javax.swing.*;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class addMovements {
    public static JLabel[] add_Movements(JLabel[] squares)
    {
        for (int i =0 ;i<64;++i)
        {

           squares[i].addMouseListener(new MouseAdapter() {
               private Point initialClick;
               private Point currentClick;

               @Override
               public void mouseDragged(MouseEvent e) {
                   super.mouseDragged(e);
               }

               @Override
               public void mouseMoved(MouseEvent e) {
                   super.mouseMoved(e);
               }

               @Override
               public void mouseClicked(MouseEvent e) {

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
           });

        }
        return squares;

    }
}
