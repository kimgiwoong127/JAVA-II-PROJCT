package Game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class SelectChar extends JFrame {
    private JLabel backgroundLabel;
    private JPanel characterPanel;
    private JLabel selectedCharacterLabel;
    private JButton selectButton;

    public SelectChar() {
        setTitle("캐릭터 선택");
        setSize(1366, 768);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon backgroundImageIcon = new ImageIcon("image/forest-2d-tileset/Background/Background.png");
        Image backgroundImage = backgroundImageIcon.getImage().getScaledInstance(1366, 768, Image.SCALE_SMOOTH);
        backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
        backgroundLabel.setLayout(new BorderLayout());
        add(backgroundLabel, BorderLayout.CENTER);

        characterPanel = new JPanel();
        characterPanel.setOpaque(false);
        characterPanel.setLayout(new GridLayout(1, 3));

        String[] characterImages = {"image/chara_profile/pinkprofile.png", "image/chara_profile/owletprofile.png", "image/chara_profile/dudeprofile.png"};
        String[] characterNames = {"pink", "owlet", "dude"};

        for (int i = 0; i < characterImages.length; i++) {
            JLabel label = new JLabel(new ImageIcon(characterImages[i]));
            label.setToolTipText(characterNames[i]);
            label.addMouseListener(new CharacterClickListener());
            characterPanel.add(label);
        }

        backgroundLabel.add(characterPanel, BorderLayout.CENTER);

        ImageIcon selectButtonIcon = new ImageIcon("image/chara_profile/캐릭터선택.png");
        int scaledWidth = 150;
        int scaledHeight = 80;
        Image scaledImage = selectButtonIcon.getImage().getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
        selectButtonIcon = new ImageIcon(scaledImage);
        selectButton = new JButton(selectButtonIcon);
        selectButton.setBorderPainted(false);
        selectButton.setContentAreaFilled(false);
        selectButton.setFocusPainted(false);
        selectButton.addActionListener(new SelectButtonListener());

        backgroundLabel.add(selectButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    private class CharacterClickListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            JLabel clickedLabel = (JLabel) e.getSource();
            if (selectedCharacterLabel != null) {
                selectedCharacterLabel.setBorder(null);
            }
            selectedCharacterLabel = clickedLabel;
            selectedCharacterLabel.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
            System.out.println("Selected Character: " + selectedCharacterLabel.getToolTipText());
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            JLabel enteredLabel = (JLabel) e.getSource();
            ImageIcon icon = (ImageIcon) enteredLabel.getIcon();
            Image image = icon.getImage().getScaledInstance(icon.getIconWidth() + 20, icon.getIconHeight() + 20, Image.SCALE_SMOOTH);
            enteredLabel.setIcon(new ImageIcon(image));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            JLabel exitedLabel = (JLabel) e.getSource();
            if (exitedLabel != selectedCharacterLabel) {
                ImageIcon icon = (ImageIcon) exitedLabel.getIcon();
                Image image = icon.getImage().getScaledInstance(icon.getIconWidth() - 20, icon.getIconHeight() - 20, Image.SCALE_SMOOTH);
                exitedLabel.setIcon(new ImageIcon(image));
            }
        }
    }

    private class SelectButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (selectedCharacterLabel != null) {
                String characterName = selectedCharacterLabel.getToolTipText();
                int result = JOptionPane.showConfirmDialog(SelectChar.this,
                        "선택된 캐릭터: " + characterName + "\n선택하시겠습니까?",
                        "캐릭터 선택 확인", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    openAnotherPage(characterName);
                }
            } else {
                JOptionPane.showMessageDialog(SelectChar.this, "캐릭터를 선택하세요.", "경고", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    private void openAnotherPage(String characterName) {
        JFrame anotherPage = new JFrame("Another Page");
        JLabel label = new JLabel("선택된 캐릭터: " + characterName);
        label.setHorizontalAlignment(SwingConstants.CENTER);

        JOptionPane.showMessageDialog(this, "게임을 시작합니다!");
        Play play = new Play();
        play.setVisible(true);
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SelectChar());
    }
}
