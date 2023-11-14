package Anmation;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Idle extends JPanel {
    private static final int CHARACTER_WIDTH = 80;
    private static final int CHARACTER_HEIGHT = 80;
    private ImageIcon[] idleFrames;
    private int currentFrame = 0;
    private Timer timer;

    public Idle() {
        idleFrames = new ImageIcon[4];  // 가만히 있는 에니메이션
        for (int i = 0; i < 4; i++) {
            idleFrames[i] = new ImageIcon("image/Player/1 Pink_Monster/Pink_Monster_Idle/Pink_Monster_Idle_" + i + ".png");
        }
        setPreferredSize(new Dimension(CHARACTER_WIDTH, CHARACTER_HEIGHT));

        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentFrame = (currentFrame + 1) % 4;
                repaint();
            }
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        idleFrames[currentFrame].paintIcon(this, g, 0, 0);
    }
}
