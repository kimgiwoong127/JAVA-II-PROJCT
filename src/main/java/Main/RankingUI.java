package Main;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RankingUI extends JFrame {
    public RankingUI() {
        setTitle("랭킹");
        setSize(380, 500);

        ImageIcon backgroundImage = new ImageIcon("image/game-ui/Wondow2/window_05.png");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, backgroundImage.getIconWidth(), backgroundImage.getIconHeight());

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.add(backgroundLabel);

        add(panel);
    }
}