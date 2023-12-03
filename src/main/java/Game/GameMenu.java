package Game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


class BackgroundPanel extends JPanel {
    private Image background;
    private Image logo;
    private Image scaledLogo;
    private Image settingIcon;
    private JLabel goldLabel;

    public BackgroundPanel(String fileName) {
        background = new ImageIcon(fileName).getImage();
        logo = new ImageIcon("image/game-ui/5 Logo/Logo.png").getImage();

        goldLabel = new JLabel("COIN: Loading...");
        goldLabel.setForeground(Color.WHITE);
        add(goldLabel);

        int LogoWidth = 800;
        int LogoHeight = 400;
        scaledLogo = logo.getScaledInstance(LogoWidth, LogoHeight, Image.SCALE_SMOOTH);

        settingIcon = new ImageIcon("image/game-ui/3 icons/icons_39.png").getImage();

        int IconWidth = 64;
        int IconHeight = 64;
        settingIcon = settingIcon.getScaledInstance(IconWidth, IconHeight, Image.SCALE_SMOOTH);
    }

    public void updateGoldInfo(int CoinAmount) {
        goldLabel.setText("COIN: " + CoinAmount);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();
        g.drawImage(background, 0, 0, width, height, this);

        int x = (width - scaledLogo.getWidth(this)) / 2;
        int y = 50;
        g.drawImage(scaledLogo, x, y, this);

        int settingIconWidth = settingIcon.getWidth(this);
        int settingIconHeight = settingIcon.getHeight(this);
        int settingIconX = 10;
        int settingIconY = height - settingIconHeight - 650;
        g.drawImage(settingIcon, settingIconX, settingIconY, this);

        goldLabel.setBounds(1250, 10, 200, 20);
    }
}

public class GameMenu extends JFrame implements ActionListener {
    private JButton startButton;
    private JButton rankingButton;
    private JButton AchievementButton;
    private JButton exitButton;

    public GameMenu() {
        setTitle("게임 초기 메뉴");
        setSize(1366, 768);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //아이콘들 크기 변수
        int IconWidth = 64;
        int IconHeight = 64;

        BackgroundPanel panel = new BackgroundPanel("image/forest-2d-tileset/Background/Background.png");
        panel.setLayout(new GridBagLayout());
        add(panel, BorderLayout.CENTER);
        setVisible(true);

        //랭킹 버튼 생성
        rankingButton = new JButton();
        Image rankingIconImage = new ImageIcon("image/game-ui/3 icons/icons_52.png").getImage();
        
        rankingIconImage = rankingIconImage.getScaledInstance(IconWidth, IconHeight, Image.SCALE_SMOOTH);
        rankingButton.setIcon(new ImageIcon(rankingIconImage));
        rankingButton.setBorderPainted(false);
        rankingButton.setContentAreaFilled(false);
        rankingButton.setFocusPainted(false);
        rankingButton.addActionListener(this);

        GridBagConstraints gbb = new GridBagConstraints();
        gbb.gridx = 1;
        gbb.gridy = 1;
        gbb.insets = new Insets(0, 1000, -800, 10);
        panel.add(rankingButton, gbb);

        //업적 버튼 생성
        AchievementButton = new JButton();
        Image AchievementIconImage = new ImageIcon("image/game-ui/3 icons/icons_10.png").getImage();
        
        AchievementIconImage = AchievementIconImage.getScaledInstance(IconWidth, IconHeight, Image.SCALE_SMOOTH);
        AchievementButton.setIcon(new ImageIcon(AchievementIconImage));
        AchievementButton.setBorderPainted(false);
        AchievementButton.setContentAreaFilled(false);
        AchievementButton.setFocusPainted(false);
        AchievementButton.addActionListener(this);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 1200, -800, 10);
        panel.add(AchievementButton, gbc);

        exitButton = new JButton();
        Image exitIconImage = new ImageIcon("image/game-ui/3 icons/icons_50.png").getImage();
        exitIconImage = exitIconImage.getScaledInstance(IconWidth, IconHeight, Image.SCALE_SMOOTH);

        exitButton.setIcon(new ImageIcon(exitIconImage));
        exitButton.setBorderPainted(false);
        exitButton.setContentAreaFilled(false);
        exitButton.setFocusPainted(false);
        exitButton.addActionListener(this);

        GridBagConstraints gbd = new GridBagConstraints();
        gbd.gridx = 1;
        gbd.gridy = 1;
        gbd.insets = new Insets(0, 0, -800, 1350);
        panel.add(exitButton, gbd);

        startButton = new JButton();
        startButton.addActionListener(this);
        startButton.setText("게임 시작");
        Dimension buttonSize = new Dimension(150, 50);

        startButton.setPreferredSize(buttonSize);

        GridBagConstraints startButtonGbc = new GridBagConstraints();
        startButtonGbc.gridx = 1;
        startButtonGbc.gridy = 2;
        startButtonGbc.insets = new Insets(150, 0, 0, 70);
        panel.add(startButton, startButtonGbc);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            playGame();
        }
        else if (e.getSource() == rankingButton) {
            showRankingUI();
        }
        else if (e.getSource() == exitButton) {
            exitGame();
        }
    }

    private void playGame() {
        JOptionPane.showMessageDialog(this, "게임을 시작합니다!");
        SelectChar SC = new SelectChar();
        SC.setVisible(true);
        dispose();
    }

    private void showRankingUI() {
        JFrame RankingFrame = new RankingUI();
        RankingFrame.setVisible(true);
    }

    private void exitGame() {
        int result = JOptionPane.showConfirmDialog(this, "정말로 게임을 종료하시겠습니까?", "게임 종료", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new GameMenu();
        });
    }
}
