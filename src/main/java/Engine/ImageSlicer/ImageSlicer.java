package Engine.ImageSlicer;

 // image/Player/1 Pink_Monster/Pink_Monster_Jump_8.png

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ImageSlicer {

    public static void main(String[] args) {
        // 프레임 생성
        JFrame frame = new JFrame("이미지 슬라이서");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 텍스트 상자와 버튼이 있는 패널 생성
        JPanel panel = new JPanel();
        JTextField inputField = new JTextField(20);
        JButton sliceButton = new JButton("이미지 슬라이스");

        JLabel imageLabel = new JLabel();
        panel.add(new JLabel("이미지 경로 입력: "));
        panel.add(inputField);
        panel.add(sliceButton);
        panel.add(imageLabel);

        sliceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputFilePath = inputField.getText();
                BufferedImage slicedImage = sliceImage(inputFilePath);

                // 슬라이스한 이미지를 레이블에 표시
                if (slicedImage != null) {
                    imageLabel.setIcon(new ImageIcon(slicedImage));
                    frame.pack();  // 이미지에 맞게 프레임 크기 조절
                }
            }
        });

        // 패널을 프레임에 추가
        frame.add(panel);

        // 프레임 표시
        frame.setVisible(true);
    }

    private static BufferedImage sliceImage(String inputFilePath) {
        int pixelSize = 32; // 각 부분 이미지의 크기

        try {
            BufferedImage originalImage = ImageIO.read(new File(inputFilePath));

            int numFrames = 8; // 총 프레임 수
            int frameWidth = originalImage.getWidth() / numFrames;

            // 슬라이스한 이미지를 저장할 BufferedImage
            BufferedImage slicedImage = new BufferedImage(originalImage.getWidth(), originalImage.getHeight(), BufferedImage.TYPE_INT_ARGB);

            for (int k = 0; k < numFrames; k++) {
                for (int i = 0; i < originalImage.getHeight() / pixelSize; i++) {
                    for (int j = 0; j < frameWidth / pixelSize; j++) {
                        int x = j * pixelSize + (k * frameWidth);
                        int y = i * pixelSize;

                        BufferedImage subImage = originalImage.getSubimage(x, y, pixelSize, pixelSize);

                        // 슬라이스한 이미지를 적절한 위치에 그림
                        slicedImage.getGraphics().drawImage(subImage, x, y, null);
                    }
                }
            }

            JOptionPane.showMessageDialog(null, "이미지 분할이 완료되었습니다.");
            return slicedImage;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "오류 발생: " + e.getMessage(), "오류", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return null;
        }
    }
}
