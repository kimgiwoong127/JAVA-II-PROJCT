package Engine.AnmationWorkFlow;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Character extends JPanel {
    private static final int CHARACTER_WIDTH = 80;
    private static final int CHARACTER_HEIGHT = 80;
    private JLabel actionLabel;
    private int x = 0;
    private int y = 0;
    private boolean isWalking = false;
    private boolean isAttacking = false;
    private boolean isClimbing = false;
    private boolean isJumping = false;
    private Walk walk;
    private Idle idle;
    private Attack attack;
    private Climb climb;
    private Jump jump;
    private Timer attackTimer;
    private String walkPath;
    private String idlePath;
    private String climbPath;
    private String attackPath;
    private String jumpPath;

    public Character(String walkPath, String idlePath, String climbPath, String attackPath, String jumpPath) {
        this.walk = new Walk(walkPath);
        this.idle = new Idle(idlePath);
        this.climb = new Climb(climbPath);
        this.attack = new Attack(attackPath);
        this.jump = new Jump(jumpPath);

        actionLabel = new JLabel("Idle");
        add(actionLabel);

        idle.setBounds(x, y, CHARACTER_WIDTH, CHARACTER_HEIGHT);
        walk.setBounds(x, y, CHARACTER_WIDTH, CHARACTER_HEIGHT);
        attack.setBounds(x, y, CHARACTER_WIDTH, CHARACTER_HEIGHT);
        climb.setBounds(x, y, CHARACTER_WIDTH, CHARACTER_HEIGHT);
        jump.setBounds(x, y, CHARACTER_WIDTH, CHARACTER_HEIGHT);

        add(idle);
        
        Timer timer = new Timer(600, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isWalking) {
                    walk.repaint();
                }else if (isClimbing) {
                    climb.repaint();
                }else if (isAttacking) {
                    idle.repaint();
                }else if (isJumping) {
                    jump.repaint();
                }
            }
        });
        timer.start();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_RIGHT) {
                    if (!isWalking && !isAttacking && !isClimbing) {
                        isWalking = true;
                        remove(idle);
                        add(walk);
                        setActionLabelText("Walking");
                        revalidate();
                        repaint();
                    }
                
                }else if (keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_DOWN) {
                    if (!isWalking && !isAttacking && !isClimbing) {
                        isClimbing = true;
                        remove(idle);
                        add(climb);
                        setActionLabelText("Climbing");
                        revalidate();
                        repaint();
                    }
                }else if (keyCode == KeyEvent.VK_Z) {
                    if (!isAttacking) {
                        isWalking = false;
                        isAttacking = true;

                        remove(walk);
                        remove(idle);
                        add(attack);
                        setActionLabelText("Attacking");
                        revalidate();
                        repaint();
                        startAttackTimer();
                    }
                }else if (keyCode == KeyEvent.VK_SPACE) {
                    if (!isJumping) {
                        isJumping = true;
                        remove(idle);
                        add(jump);
                        setActionLabelText("Jumping");
                        revalidate();
                        repaint();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_RIGHT) {
                    isWalking = false;
                    isAttacking = false;
                    if (!isWalking && !isAttacking) {
                        remove(walk);
                        remove(attack);
                        add(idle);
                        setActionLabelText("Idle");
                        revalidate();
                        repaint();
                        
                    }
                }else if (keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_DOWN) {
                    isClimbing = false;
                    remove(climb);
                    add(idle);
                    setActionLabelText("Idle");
                    revalidate();
                    repaint();
                }else if (keyCode == KeyEvent.VK_SPACE) {
                    isJumping = false;
                    remove(jump);
                    add(idle);
                    setActionLabelText("Idle");
                    revalidate();
                    repaint();
                }
                else if (keyCode == KeyEvent.VK_Z) {
                    isJumping = false;
                    remove(attack);
                    add(idle);
                    setActionLabelText("Idle");
                    revalidate();
                    repaint();
                }
            }
        });

        setFocusable(true);
        requestFocus();
    }

    private void setActionLabelText(String actionText) {
        actionLabel.setText(actionText);
    }

    private void startAttackTimer() {
        if (attackTimer != null && attackTimer.isRunning()) {
            attackTimer.stop();
        }

        attackTimer = new Timer(600, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isAttacking = false;
                remove(attack);
                add(idle);
                revalidate();
                repaint();
            }
        });
        attackTimer.setRepeats(false);
        attackTimer.start();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(CHARACTER_WIDTH, CHARACTER_HEIGHT);
    }

    public int getCharacterWidth() {
        return CHARACTER_WIDTH;
    }

    public int getCharacterHeight() {
        return CHARACTER_HEIGHT;
    }
}
