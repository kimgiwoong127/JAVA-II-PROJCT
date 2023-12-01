

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import Game.GameMenu;

public class Register extends JFrame {
    private JTextField idField;
    private JTextField passField;
    private JTextField nicknameField;

    public Register() {
        setTitle("회원가입");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 배경 이미지를 표시하는 패널 생성
        BackgroundPanel panel = new BackgroundPanel("image/game-ui/Wondow2/signupimage.png");
        panel.setLayout(null); // 절대 위치 지정을 위해 null 레이아웃 사용

        // 텍스트 필드를 담는 패널 생성
        JPanel textFieldsPanel = new JPanel();
        textFieldsPanel.setOpaque(false); // 투명한 패널로 설정

        // 아이디 텍스트 필드 추가
        idField = createTextField("아이디", 31);
        // 비밀번호 텍스트 필드 추가
        passField = createTextField("비밀번호", 31);
        // 닉네임 텍스트 필드 추가
        nicknameField = createTextField("닉네임", 31);

        // textFieldsPanel에 텍스트 필드 추가
        textFieldsPanel.add(idField);
        textFieldsPanel.add(passField);
        textFieldsPanel.add(nicknameField);

        // textFieldsPanel 위치 및 크기 설정
        textFieldsPanel.setBounds(327, 160, 410, 400); // x, y, width, height

        // Join 버튼 추가
        JButton joinButton = new JButton("JOIN") {
            @Override
            protected void paintComponent(Graphics g) {
                if (getModel().isPressed()) {
                    g.setColor(new Color(102, 114, 145));
                    g.fillRect(0, 0, getWidth(), getHeight());
                    setForeground(Color.DARK_GRAY);
                } else {
                    g.setColor(new Color(102, 114, 145));
                    g.fillRect(0, 0, getWidth(), getHeight());
                    setForeground(Color.WHITE);
                }
                super.paintComponent(g);
            }
        };
        joinButton.setBounds(170, 470, 150, 90); // x, y, width, height
        Font font = new Font("Arial", Font.BOLD, 24);
        joinButton.setFont(font);
        joinButton.setForeground(Color.WHITE);
        joinButton.setBorderPainted(false);
        joinButton.setFocusPainted(false);
        joinButton.setContentAreaFilled(false);

        // 마우스 이벤트 처리
        joinButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // 회원가입 버튼 클릭 시 아이디, 비밀번호, 닉네임을 콘솔에 출력
                String id = idField.getText();
                String password = passField.getText();
                String nickname = nicknameField.getText();
                System.out.println("ID: " + id + ", Password: " + password + ", Nickname: " + nickname);

                // 현재 창 닫기
                dispose();

                // GameMenu 창 열기
                SwingUtilities.invokeLater(() -> new GameMenu());
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // 마우스가 버튼에 들어왔을 때
                joinButton.setFont(new Font("Arial", Font.BOLD, 28));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // 마우스가 버튼에서 나갔을 때
                joinButton.setFont(new Font("Arial", Font.BOLD, 24));
            }
        });

        // 메인 패널에 textFieldsPanel 및 Join 버튼 추가
        panel.add(textFieldsPanel);
        panel.add(joinButton);

        // 메인 프레임에 배경 패널 추가
        add(panel);
        setVisible(true);
    }

    private JTextField createTextField(String text, int columns) {
        JTextField textField = new JTextField(text, columns);
        textField.setPreferredSize(new Dimension(textField.getPreferredSize().width, 84));
        textField.setBackground(new Color(26, 28, 48));
        textField.setForeground(Color.WHITE);
        Font font = new Font("Arial", Font.PLAIN, 16);
        textField.setFont(font);
        textField.setBorder(new EmptyBorder(0, 0, 0, 0));

        // 마우스 이벤트 처리
        textField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // 마우스가 텍스트 필드에 들어왔을 때
                textField.setBackground(new Color(50, 50, 80));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // 마우스가 텍스트 필드에서 나갔을 때
                textField.setBackground(new Color(26, 28, 48));
            }
        });
        return textField;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Register();
        });
    }
}

class GameMenuFrame implements Runnable {
    @Override
    public void run() {
        JFrame gameMenu = new GameMenu();
        gameMenu.setVisible(true);
    }
}


class BackgroundPanel extends JPanel {
    private Image background;

    public BackgroundPanel(String imagePath) {
        background = new ImageIcon(imagePath).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();
        g.drawImage(background, 0, 0, width, height, this);
    }
}
