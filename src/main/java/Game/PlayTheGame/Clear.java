package Game.PlayTheGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class Clear extends JFrame {
    public Clear() {
        try {
            // 프레임 설정
            setTitle("게임 클리어");
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setResizable(false);
            setSize(1366, 760); // 크기 변경
            setLocationRelativeTo(null); // 화면 중앙에 표시

            // 배경 이미지 설정
            ImageIcon backgroundIcon = new ImageIcon("image/forest-2d-tileset/Background/Background.png");
            Image backgroundImage = backgroundIcon.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
            if (backgroundImage == null) {
                throw new RuntimeException("배경 이미지 로딩 실패");
            }
            ImageIcon scaledBackgroundIcon = new ImageIcon(backgroundImage);
            JLabel backgroundLabel = new JLabel(scaledBackgroundIcon);
            backgroundLabel.setBounds(0, 0, getWidth(), getHeight());

            // 게임 클리어 문구 설정
            JLabel clearLabel = new JLabel("CLEAR!");
            clearLabel.setForeground(Color.DARK_GRAY);
            clearLabel.setFont(new Font("Arial", Font.BOLD, 60));
            clearLabel.setHorizontalAlignment(JLabel.CENTER);
            clearLabel.setBounds(300, 300, getWidth() - 600, 50); // 조정된 위치

            // 닫기 버튼 추가
            JButton closeButton = new JButton("닫기");
            closeButton.setBounds(633, 650, 100, 40); // 조정된 위치 및 크기
            closeButton.setFont(new Font("Arial", Font.BOLD, 16));
            closeButton.setForeground(Color.WHITE);
            closeButton.setBackground(Color.GRAY); // Set background color to gray
            closeButton.setBorder(BorderFactory.createLineBorder(new Color(0, 51, 102), 2)); // Custom border color

            // Add a hover effect
            closeButton.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    closeButton.setBackground(new Color(128, 128, 128)); // Darker gray on hover
                }

                public void mouseExited(java.awt.event.MouseEvent evt) {
                    closeButton.setBackground(Color.GRAY);
                }
            });

            closeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose(); // 화면 닫기
                }
            });

            // backgroundLabel에 컴포넌트 추가
            backgroundLabel.add(clearLabel);
            backgroundLabel.add(closeButton);

            // 프레임에 backgroundLabel 추가
            add(backgroundLabel);

            // 레이아웃 설정
            setLayout(null);

            // 프레임 표시
            setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // 게임 클리어 화면 테스트
        SwingUtilities.invokeLater(() -> new Clear());
    }
}
