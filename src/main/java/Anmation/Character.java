package Anmation;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Character extends JPanel {
    private static final int CHARACTER_WIDTH = 80;
    private static final int CHARACTER_HEIGHT = 80;
    private int Delta_X = 5;
    private int x = 220;
    private int y = 220;
    private boolean isWalking = false;
    private Walk walk;
    private Idle idle;
    private Move move;
    
    public Character() {
        walk = new Walk();
        idle = new Idle();
        idle.setBounds(x, y, CHARACTER_WIDTH, CHARACTER_HEIGHT);
        walk.setBounds(x, y, CHARACTER_WIDTH, CHARACTER_HEIGHT);

        move = new Move(this);
        
        add(idle);
        
        Timer timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isWalking) {
                    walk.repaint();
                } else {
                    idle.repaint();
                }
            }
        });
        timer.start();
        
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_RIGHT) {
                    isWalking = true;
                    remove(idle);
                    add(walk);
                    revalidate();
                    repaint();
                }
                
                if (isWalking) {
                    if (keyCode == KeyEvent.VK_LEFT) {
                        move.moveLeft();
                    } else if (keyCode == KeyEvent.VK_RIGHT) {
                        move.moveRight();
                    }
                }
            }
            
            @Override
            public void keyReleased(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_RIGHT) {
                    isWalking = false;
                    remove(walk);
                    add(idle);
                    revalidate();
                    repaint();
                }
            }
        });
        
        setFocusable(true);
        requestFocus();
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(CHARACTER_WIDTH, CHARACTER_HEIGHT);
    }
    
    public int getDeltaX() {
        return Delta_X;
    }
    
    public void setX(int x) {
        this.x = x;
    }
    
    public int getCharacterWidth() {
        return CHARACTER_WIDTH;
    }
}
