package Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import Engine.AnmationWorkFlow.Idle;

public class Player {
    private JPanel playerPanel;
    private Idle idleCharacter;
    private int x = 0;
    private int y = 288;
    private int speed = 2;

    public Player() {
        // Create an instance of Idle with the path to your idle animation frames
        idleCharacter = new Idle("image\\Player\\1 Pink_Monster\\Pink_Monster_Idle\\Pink_Monster_idle_");

        // Set the initial bounds of the idleCharacter within playerPanel
        idleCharacter.setBounds(x, y, 80, 80);

        // Create a JPanel to contain the player components
        playerPanel = new JPanel();
        playerPanel.setSize(688, 400);
        playerPanel.setOpaque(false); // Set the background to be transparent
        playerPanel.setLayout(null);
        playerPanel.add(idleCharacter);

        // Add KeyListener to playerPanel to handle keyboard input
        playerPanel.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                // Not needed for this example
            }

            @Override
            public void keyPressed(KeyEvent e) {
                handleKeyPress(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // Not needed for this example
            }
        });

        // Set the playerPanel to be focusable for KeyListener to work
        playerPanel.setFocusable(true);
        playerPanel.requestFocus();
    }

    private void handleKeyPress(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_LEFT) {
            movePlayer(-speed, 0);
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            movePlayer(speed, 0);
        }
    }

    private void movePlayer(int deltaX, int deltaY) {
        x += deltaX;
        y += deltaY;
        idleCharacter.setBounds(x, y, 80, 80);
    }

    public JPanel getPlayerPanel() {
        return playerPanel;
    }
}
