package Engine;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class EngineMain {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Engine Main");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 각 버튼을 생성하고 ActionListener를 추가
        JButton animationButton = new JButton("Animation WorkFlow");
        JButton imageSlicerButton = new JButton("Image Slicer");
        JButton mapEditorButton = new JButton("Map Editor");
        JButton objectEditorButton = new JButton("Object Editor");

        animationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Animation WorkFlow 실행
                Engine.AnmationWorkFlow.Launcher.main(null);
            }
        });

        imageSlicerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Image Slicer 실행
                Engine.ImageSlicer.ImageSlicer.main(null);
            }
        });

        mapEditorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Map Editor 실행
                Engine.MapEditor.SimpleMapEditor.main(null);
            }
        });

        objectEditorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Object Editor 실행
                Engine.MapEditor.SimpleObjectEditor.main(null);
            }
        });

        

        // 프레임에 버튼들을 추가
        frame.setContentPane(new JPanel(new BorderLayout())); // Use a BorderLayout

        // 이미지 파일 경로
        String imagePath = "image\\EngineImage\\KakaoTalk_20231203_204756671.png";

        // ImagePanel을 생성하여 배경 이미지로 설정
        ImagePanel imagePanel = new ImagePanel(imagePath);
        imagePanel.setBorder(new EmptyBorder(20, 0, 0, 0)); // Add space at the top

        frame.getContentPane().add(imagePanel, BorderLayout.CENTER);

        // ImagePanel에 FlowLayout을 설정하고 버튼들을 추가
        imagePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 600));
        // Add an empty label for spacing
        imagePanel.add(new JLabel(" "));

        imagePanel.add(animationButton);
        imagePanel.add(imageSlicerButton);
        imagePanel.add(mapEditorButton);
        imagePanel.add(objectEditorButton);

        imagePanel.setCenterText("JAVA GRAPHIC STUDIO");

        // 프레임 설정
        frame.pack(); // 프레임 크기를 이미지 크기에 맞게 조절
        frame.setLocationRelativeTo(null); // 화면 중앙에 표시
        frame.setVisible(true);
    }
}

class ImagePanel extends JPanel {
    private Image backgroundImage;
    private String centerText;

    public ImagePanel(String imagePath) {
        try {
            backgroundImage = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Initialize centerText to an empty string
        centerText = "";
    }

    public void setCenterText(String text) {
        centerText = text;
        repaint(); // Request a repaint to update the displayed text
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, this);

        Font font = new Font("Arial", Font.BOLD, 50); // 사용하고자 하는 글씨체로 변경하세요
        g.setFont(font);
        g.drawString(centerText, (getWidth() - g.getFontMetrics().stringWidth(centerText)) / 2, getHeight() / 2);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(backgroundImage.getWidth(this), backgroundImage.getHeight(this));
    }
}
