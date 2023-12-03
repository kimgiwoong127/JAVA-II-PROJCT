package Engine.AnmationWorkFlow;

import java.awt.AlphaComposite;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Walk extends JPanel {
    private static final int CHARACTER_WIDTH = 80;
    private static final int CHARACTER_HEIGHT = 80;
    private ImageIcon[] walkFrames;
    private int currentFrame = 0;
    private Timer timer;

    public Walk(String walkPath) {
        walkFrames = new ImageIcon[6];
        for (int i = 0; i < 6; i++) {
            walkFrames[i] = new ImageIcon(walkPath + i + ".png");
        }
        setOpaque(false);
        setPreferredSize(new Dimension(CHARACTER_WIDTH, CHARACTER_HEIGHT));

        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentFrame = (currentFrame + 1) % 6;
                repaint();
            }
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // 그래픽스 객체를 Graphics2D로 캐스팅합니다.
        Graphics2D g2d = (Graphics2D) g.create();

        // 알파 채널을 사용하여 투명성을 설정합니다.
        float alpha = 1.0f; // 투명성을 조절하려면 0.0f부터 1.0f까지의 값을 사용합니다.
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));

        // 현재 프레임을 그립니다.
        walkFrames[currentFrame].paintIcon(this, g2d, 0, 0);

        // 그래픽스 객체를 해제합니다.
        g2d.dispose();
    }
}
