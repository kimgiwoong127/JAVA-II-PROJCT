package Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import Engine.AnmationWorkFlow.Attack;
import Engine.AnmationWorkFlow.Climb;
import Engine.AnmationWorkFlow.Idle;
import Engine.AnmationWorkFlow.Jump;
import Engine.AnmationWorkFlow.Walk;

public class Player {
    private JPanel playerPanel;
    private Idle idleCharacter;
    private Walk walkingCharacter;
    private Jump jumpingCharacter;
    private Attack attack2Character;
    private Climb climbingCharacter;
    private int x = 0;
    private int y = 288;
    private int speed = 2;
    private boolean isWalk = false;
    private boolean isJump = false;
    private boolean isAttack = false;
    private boolean isClimb = false;
    private Timer jumpTimer;
    private int jumpHeight = 50;

    public Player() {
        idleCharacter = new Idle("image\\Player\\1 Pink_Monster\\Pink_Monster_Idle\\Pink_Monster_idle_");

        walkingCharacter = new Walk("image\\Player\\1 Pink_Monster\\Pink_Monster_Walk_6\\Pink_Monster_Walk_");

        jumpingCharacter = new Jump("image\\Player\\1 Pink_Monster\\Pink_Monster_Jump\\Pink_Monster_jump_");

        attack2Character = new Attack("image\\Player\\1 Pink_Monster\\Pink_Monster_Attack2_6\\Pink_Monster_Attack2_");

        climbingCharacter = new Climb("image\\Player\\1 Pink_Monster\\Pink_Monster_Climb_4\\Pink_Monster_Climb_");

        idleCharacter.setBounds(x, y, 80, 80);

        playerPanel = new JPanel();
        playerPanel.setSize(688, 400);
        playerPanel.setOpaque(false);
        playerPanel.setLayout(null);
        playerPanel.add(idleCharacter);

        playerPanel.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                handleKeyPress(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                isWalk = false;
                isJump = false;
                isAttack = false;
                isClimb = false;
                movePlayer(0, 0);
            }
        });

        playerPanel.setFocusable(true);
        playerPanel.requestFocus();

        jumpTimer = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateJumpMotion();
            }
        });
    }

    private void handleKeyPress(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_LEFT) {
            isWalk = true;
            movePlayer(-speed, 0);
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            isWalk = true;
            movePlayer(speed, 0);
        } else if (keyCode == KeyEvent.VK_SPACE && !isJump) {
            isJump = true;
            jumpTimer.start();
        } else if (keyCode == KeyEvent.VK_Z && !isAttack) {
            isAttack = true;
            movePlayer(0, 0);
        } else if (keyCode == KeyEvent.VK_UP && !isClimb) {
            isClimb = true;
            movePlayer(0, -speed);
        } else if (keyCode == KeyEvent.VK_DOWN && !isClimb) {
            isClimb = true;
            movePlayer(0, +speed);
        }
    }

    private void movePlayer(int deltaX, int deltaY) {
        x += deltaX;
        y += deltaY;

        if (isWalk) {
            walkingCharacter.setBounds(x, y, 80, 80);
            playerPanel.remove(idleCharacter);
            playerPanel.add(walkingCharacter);
        } else if (isJump) {
            jumpingCharacter.setBounds(x, y, 80, 80);
            playerPanel.remove(idleCharacter);
            playerPanel.add(jumpingCharacter);
        } else if (isAttack) {
            attack2Character.setBounds(x, y, 80, 80);
            playerPanel.remove(idleCharacter);
            playerPanel.add(attack2Character);
        } else if (isClimb) {
            climbingCharacter.setBounds(x, y, 80, 80);
            playerPanel.remove(idleCharacter);
            playerPanel.add(climbingCharacter);
        } else {
            idleCharacter.setBounds(x, y, 80, 80);
            playerPanel.remove(walkingCharacter);
            playerPanel.remove(jumpingCharacter);
            playerPanel.remove(attack2Character);
            playerPanel.remove(climbingCharacter);
            playerPanel.add(idleCharacter);
        }
    }

    private void updateJumpMotion() {
        y -= speed;

        if (y <= (288 - jumpHeight)) {
            jumpTimer.stop();
            isJump = false;
            jumpBackDown();
        }

        movePlayer(0, 0);
    }

    private void jumpBackDown() {
        Timer jumpDownTimer = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                y += speed;
                movePlayer(0, 0);

                if (y >= 288) {
                    ((Timer) e.getSource()).stop();
                    isJump = false;
                }
            }
        });

        jumpDownTimer.start();
    }

    public JPanel getPlayerPanel() {
        return playerPanel;
    }
}
