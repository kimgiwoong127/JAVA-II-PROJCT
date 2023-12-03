package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SelectChar extends JFrame {
    private JLabel backgroundLabel;
    private JPanel characterPanel;

    public SelectChar() {
        setTitle("캐릭터 선택");
        setSize(1366, 768);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 백그라운드 이미지 추가 및 크기 조절
        ImageIcon backgroundImageIcon = new ImageIcon("image/forest-2d-tileset/Background/Background.png");
        Image backgroundImage = backgroundImageIcon.getImage().getScaledInstance(1366, 768, Image.SCALE_SMOOTH);
        backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
        backgroundLabel.setLayout(new BorderLayout());
        add(backgroundLabel, BorderLayout.CENTER);

        characterPanel = new JPanel();
        characterPanel.setOpaque(false);  // 투명한 패널로 설정
        characterPanel.setLayout(new GridLayout(1, 3));

        // 캐릭터 이미지 파일 경로에 따라 수정
        String[] characterImages = {"image/chara_profile/pinkprofile.png", "image/chara_profile/owletprofile.png", "image/chara_profile/dudeprofile.png"};

        for (String imagePath : characterImages) {
            JLabel label = new JLabel(new ImageIcon(imagePath));
            label.addMouseListener(new CharacterClickListener());
            characterPanel.add(label);
        }

        // characterPanel을 backgroundLabel 중앙에 추가
        backgroundLabel.add(characterPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private class CharacterClickListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            JLabel selectedLabel = (JLabel) e.getSource();
            System.out.println("Selected Character: " + selectedLabel.getIcon());
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            JLabel enteredLabel = (JLabel) e.getSource();
            // 마우스가 올라가면 이미지를 확대
            ImageIcon icon = (ImageIcon) enteredLabel.getIcon();
            Image image = icon.getImage().getScaledInstance(icon.getIconWidth() + 20, icon.getIconHeight() + 20, Image.SCALE_SMOOTH);
            enteredLabel.setIcon(new ImageIcon(image));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            JLabel exitedLabel = (JLabel) e.getSource();
            // 마우스가 나가면 다시 원래 크기로
            ImageIcon icon = (ImageIcon) exitedLabel.getIcon();
            Image image = icon.getImage().getScaledInstance(icon.getIconWidth() - 20, icon.getIconHeight() - 20, Image.SCALE_SMOOTH);
            exitedLabel.setIcon(new ImageIcon(image));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SelectChar());
    }
}
