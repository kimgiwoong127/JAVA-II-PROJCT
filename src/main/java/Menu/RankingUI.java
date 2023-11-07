package Menu;

import javax.swing.*;
import java.awt.*;

public class RankingUI extends JFrame {
    public RankingUI() {
        setTitle("랭킹");
        setSize(400, 600);

        // 배경 이미지를 불러옵니다.
        ImageIcon backgroundImage = new ImageIcon("image/game-ui/Wondow2/window_05.png");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, backgroundImage.getIconWidth(), backgroundImage.getIconHeight());

        // 랭킹 표시할 컴포넌트들을 추가하고 설정하는 코드를 작성해야 합니다.

        // 패널을 만들어 배경 이미지와 컴포넌트들을 추가합니다.
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.add(backgroundLabel);

        // 여기에 랭킹 표시할 컴포넌트들을 추가하는 코드를 작성해주세요.

        add(panel);
    }
}