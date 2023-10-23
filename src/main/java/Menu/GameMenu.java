package Menu;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class BackgroundPanel extends JPanel {
    private Image background;
    private Image logo;
    private Image scaledLogo;
    private Image rankingIcon;
    private Image settingIcon;

    public BackgroundPanel(String fileName) {
        background = new ImageIcon(fileName).getImage();
        logo = new ImageIcon("image/game-ui/5 Logo/Logo.png").getImage();

        // 로고 이미지 크기 조절
        int LogoWidth = 800;
        int LogoHeight = 400;
        scaledLogo = logo.getScaledInstance(LogoWidth, LogoHeight, Image.SCALE_SMOOTH);

        settingIcon = new ImageIcon("image/game-ui/3 icons/icons_39.png").getImage();

        int newIconWidth = 64;
        int newIconHeight = 64;
        settingIcon = settingIcon.getScaledInstance(newIconWidth, newIconHeight, Image.SCALE_SMOOTH);
        
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();
        g.drawImage(background, 0, 0, width, height, this); 

        int x = (width - scaledLogo.getWidth(this)) / 2;
        int y = 50; // 로고를 조금 더 위로 올림
        g.drawImage(scaledLogo, x, y, this);

        // 세팅 아이콘 위치 지정
        int settingIconWidth = settingIcon.getWidth(this);
        int settingIconHeight = settingIcon.getHeight(this);
        int settingIconX = 10; // 왼쪽 여백 10픽셀
        int settingIconY = height - settingIconHeight - 10; // 아래 여백 10픽셀
        g.drawImage(settingIcon, settingIconX, settingIconY, this);

}
}

public class GameMenu extends JFrame implements ActionListener {
    private JButton startButton;
    private JButton rankingButton;
    // private SoundPlayer backgroundMusic;

    public GameMenu() {
        setTitle("게임 초기 메뉴");
        setSize(1366, 768);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        BackgroundPanel panel = new BackgroundPanel("image/forest-2d-tileset/Background/Background.png");
        panel.setLayout(new GridBagLayout());

        add(panel, BorderLayout.CENTER);

        setVisible(true);

        rankingButton = new JButton();

        // 랭킹 아이콘 이미지 크기 조절
        Image rankingIcon = new ImageIcon("image/game-ui/3 icons/icons_10.png").getImage();
        int newIconWidth = 64;
        int newIconHeight = 64;
        rankingIcon = rankingIcon.getScaledInstance(newIconWidth, newIconHeight, Image.SCALE_SMOOTH);

        rankingButton.setIcon(new ImageIcon(rankingIcon));
        rankingButton.setBorderPainted(false);
        rankingButton.setContentAreaFilled(false);
        rankingButton.setFocusPainted(false);
        rankingButton.addActionListener(this);

        rankingButton.setPreferredSize(new Dimension(newIconWidth, newIconHeight));

        int rankingiconX = panel.getWidth() - newIconWidth - 10;
        int rankingiconY = panel.getHeight() - newIconHeight - 10;
        rankingButton.setBounds(rankingiconX, rankingiconY, newIconWidth, newIconHeight);

        panel.add(rankingButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            playGame();
        }
        else if (e.getSource() == rankingButton) {
            showRankingUI(); // 랭킹 버튼 눌렀을 때 호출할 메소드
        }
    }

    private void playGame() {
        JOptionPane.showMessageDialog(this, "게임을 시작합니다!");
        // 게임 시작 코드를 여기에 추가
    }

    private void showRankingUI() {
        // 랭킹 UI 창 띄우기
        JFrame rankingFrame = new RankingUI();
        rankingFrame.setVisible(true);
    }

    public static void main(String[] args) {
        new GameMenu();
    }
}