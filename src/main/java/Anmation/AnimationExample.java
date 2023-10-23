package Anmation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;

public class AnimationExample {
    private static final int FRAME_WIDTH = 300;
    private static final int FRAME_HEIGHT = 300;
    private static final int CHARACTER_WIDTH = 80;
    private static final int CHARACTER_HEIGHT = 80;
    private static final int NUM_FRAMES = 4;
    private static final int DELAY = 100; // 밀리초 단위

    private static int currentFrame = 0;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Animation Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);

        JLabel label = new JLabel();
        label.setPreferredSize(new Dimension(CHARACTER_WIDTH, CHARACTER_HEIGHT));
        frame.add(label, BorderLayout.CENTER);

        Timer timer = new Timer(DELAY, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ImageIcon icon = new ImageIcon("image/Player/1 Pink_Monster_idle_" + currentFrame + ".png");
                label.setIcon(icon);
                currentFrame = (currentFrame + 1) % NUM_FRAMES;
            }
        });
        timer.start();

        frame.setVisible(true);
    }
}
