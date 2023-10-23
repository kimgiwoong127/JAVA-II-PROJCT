package Anmation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Character extends JPanel {
    private static final int CHARACTER_WIDTH = 80;
    private static final int CHARACTER_HEIGHT = 80;
    private static final int DELTA_X = 5;
    private static final int JUMP_HEIGHT = 50;

    private int x = 100;
    private int y = 100;
    private int currentFrame = 0;
    private boolean isJumping = false;

    public Character() {
        setPreferredSize(new Dimension(CHARACTER_WIDTH, CHARACTER_HEIGHT));
        Timer timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentFrame = (currentFrame + 1) % 4;
                repaint();
            }
        });
        timer.start();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_LEFT) {
                    x = Math.max(0, x - DELTA_X);
                } else if (keyCode == KeyEvent.VK_RIGHT) {
                    x = Math.min(getWidth() - CHARACTER_WIDTH, x + DELTA_X);
                } else if (keyCode == KeyEvent.VK_SPACE && !isJumping) {
                    jump();
                }
                repaint();
            }
        });
        setFocusable(true);
    }

    private void jump() {
        isJumping = true;
        int originalY = y;
        for (int i = 0; i < JUMP_HEIGHT; i++) {
            y = Math.max(0, y - 1);
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            repaint();
        }
        for (int i = 0; i < JUMP_HEIGHT; i++) {
            y = Math.min(getHeight() - CHARACTER_HEIGHT, y + 1);
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            repaint();
        }
        isJumping = false;
        y = originalY;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon icon = new ImageIcon("image/Player/1 Pink_Monster_idle_" + currentFrame + ".png");
        g.drawImage(icon.getImage(), x, y, null);
    }
}
