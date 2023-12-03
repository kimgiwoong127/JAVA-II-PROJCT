package Game.Register;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import Game.PlayTheGame.GameMenu;

public class Login extends JFrame {
    private JTextField idField;
    private JPasswordField passwordField;

    public Login() {
        setTitle("로그인");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        BackgroundPanel panel = new BackgroundPanel("image/game-ui/Wondow2/window_03.png");
        panel.setLayout(null);

        JPanel textFieldsPanel = new JPanel();
        textFieldsPanel.setOpaque(false);

        idField = createTextField("아이디", 31);
        passwordField = new JPasswordField(31);
        passwordField.setPreferredSize(new Dimension(passwordField.getPreferredSize().width, 84));
        passwordField.setBackground(new Color(26, 28, 48));
        passwordField.setForeground(Color.WHITE);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 16));
        passwordField.setBorder(new EmptyBorder(0, 0, 0, 0));

        textFieldsPanel.add(idField);
        textFieldsPanel.add(passwordField);

        textFieldsPanel.setBounds(327, 160, 410, 250);

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
        loginButton.setBounds(170, 470, 150, 90);
        Font font = new Font("Arial", Font.BOLD, 24);
        loginButton.setFont(font);
        loginButton.setForeground(Color.WHITE);
        loginButton.setBorderPainted(false);
        loginButton.setFocusPainted(false);
        loginButton.setContentAreaFilled(false);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                String password = new String(passwordField.getPassword());

                LoginDTO loginDTO = new LoginDTO(id, password);

                LoginDAO loginDAO = new LoginDAO();
                if (loginDAO.login(loginDTO)) {
                    JOptionPane.showMessageDialog(Login.this, "로그인 성공");
                    dispose();
                    SwingUtilities.invokeLater(() -> new GameMenu());
                } else {
                    JOptionPane.showMessageDialog(Login.this, "로그인 실패");
                }
            }
        });

        panel.add(textFieldsPanel);
        panel.add(loginButton);

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

        textField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                textField.setBackground(new Color(50, 50, 80));
            }

            @Override
            public void mouseExited(MouseEvent e) {
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
