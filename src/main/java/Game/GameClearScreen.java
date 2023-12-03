package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameClearScreen extends JFrame {
    public GameClearScreen() {
        // 프레임 설정
        setTitle("Game Clear");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(1366, 760); // 크기 변경
        setLocationRelativeTo(null); // 화면 중앙에 표시

        // 배경 이미지 설정
        ImageIcon backgroundIcon = new ImageIcon("image/forest-2d-tileset/Background/Background.png");
        Image backgroundImage = backgroundIcon.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledBackgroundIcon = new ImageIcon(backgroundImage);
        JLabel backgroundLabel = new JLabel(scaledBackgroundIcon);
        backgroundLabel.setBounds(0, 0, getWidth(), getHeight());

        // 게임 클리어 문구 설정
        JLabel clearLabel = new JLabel("1번째 여정 완료!!");
        clearLabel.setForeground(Color.ORANGE);
        clearLabel.setFont(new Font("Arial", Font.BOLD, 30));
        clearLabel.setHorizontalAlignment(JLabel.CENTER);
        clearLabel.setBounds(400, 300, getWidth() - 400, 50); // 조정된 위치

        // 닫기 버튼 추가
        JButton closeButton = new JButton("닫기");
        closeButton.setBounds(633, 650, 100, 30); // 조정된 위치
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // 화면 닫기
            }
        });

        // 프레임에 컴포넌트 추가
        add(backgroundLabel);
        add(clearLabel);
        add(closeButton);

        // 레이아웃 설정
        setLayout(null);

        // 프레임 표시
        setVisible(true);
    }

    public static void main(String[] args) {
        // 게임 클리어 화면 테스트
        SwingUtilities.invokeLater(() -> new GameClearScreen());
    }
}
