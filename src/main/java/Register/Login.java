// LoginFrame.java
package Register;

import Game.GameMenu;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login extends JFrame {
    private JTextField idField;
    private JPasswordField passwordField;

    public Login() {
        setTitle("로그인");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 배경 이미지를 표시하는 패널 생성
        BackgroundPanel panel = new BackgroundPanel("image/game-ui/Wondow2/window_03.png");
        panel.setLayout(null); // 절대 위치 지정을 위해 null 레이아웃 사용

        // 텍스트 필드를 담는 패널 생성
        JPanel textFieldsPanel = new JPanel();
        textFieldsPanel.setOpaque(false); // 투명한 패널로 설정

        // 아이디 텍스트 필드 추가
        idField = createTextField("아이디", 31);
        // 비밀번호 필드 추가
        passwordField = new JPasswordField(31);
        passwordField.setPreferredSize(new Dimension(passwordField.getPreferredSize().width, 84));
        passwordField.setBackground(new Color(26, 28, 48));
        passwordField.setForeground(Color.WHITE);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 16));
        passwordField.setBorder(new EmptyBorder(0, 0, 0, 0));

        // textFieldsPanel에 텍스트 필드 추가
        textFieldsPanel.add(idField);
        textFieldsPanel.add(passwordField);

        // textFieldsPanel 위치 및 크기 설정
        textFieldsPanel.setBounds(327, 160, 410, 250); // x, y, width, height

        // Login 버튼 추가
        JButton loginButton = new JButton("LOGIN") {
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
        loginButton.setBounds(170, 470, 150, 90); // x, y, width, height
        Font font = new Font("Arial", Font.BOLD, 24);
        loginButton.setFont(font);
        loginButton.setForeground(Color.WHITE);
        loginButton.setBorderPainted(false);
        loginButton.setFocusPainted(false);
        loginButton.setContentAreaFilled(false);

        // 마우스 이벤트 처리
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 로그인 버튼 클릭 시
                String id = idField.getText();
                String password = new String(passwordField.getPassword());

                // Create LoginDTO
                LoginDTO loginDTO = new LoginDTO(id, password);

                // Create LoginDAO and check login
                LoginDAO loginDAO = new LoginDAO();
                if (loginDAO.login(loginDTO)) {
                    JOptionPane.showMessageDialog(Login.this, "로그인 성공");

                    // 현재 창 닫기
                    dispose();

                    // GameMenu 창 열기
                    SwingUtilities.invokeLater(() -> new GameMenu());
                } else {
                    JOptionPane.showMessageDialog(Login.this, "로그인 실패");
                }
            }
        });

        // 메인 패널에 textFieldsPanel 및 Login 버튼 추가
        panel.add(textFieldsPanel);
        panel.add(loginButton);

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
            new Login();
        });
    }

    class GameMenuFrame implements Runnable {
        @Override
        public void run() {
            JFrame gameMenu = new GameMenu();
            gameMenu.setVisible(true);
        }
    }
}
