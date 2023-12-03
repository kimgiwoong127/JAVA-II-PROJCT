package Engine.ImageSlicer;

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
        JFrame frame = new JFrame("이미지 슬라이서");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        JTextField inputField = new JTextField(20);
        JTextField outputFileNameField = new JTextField(20);
        JTextField numFramesField = new JTextField(5);
        JButton sliceButton = new JButton("이미지 슬라이스");

        JLabel imageLabel = new JLabel();
        panel.add(new JLabel("이미지 경로 입력: "));
        panel.add(inputField);
        panel.add(new JLabel("출력 파일명 입력: "));
        panel.add(outputFileNameField);
        panel.add(new JLabel("프레임 수 입력: "));
        panel.add(numFramesField);
        panel.add(sliceButton);
        panel.add(imageLabel);

        sliceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputFilePath = inputField.getText();
                String outputFileName = outputFileNameField.getText();
                String outputDirectory = JOptionPane.showInputDialog(null, "저장할 디렉토리 경로를 입력하세요:");
                int numFrames = Integer.parseInt(numFramesField.getText());
                BufferedImage slicedImage = sliceImage(inputFilePath, outputDirectory, outputFileName, numFrames);

                if (slicedImage != null) {
                    imageLabel.setIcon(new ImageIcon(slicedImage));
                    frame.pack();
                }
            }
        });

        frame.add(panel);

        frame.setVisible(true);
    }

    private static BufferedImage sliceImage(String inputFilePath, String outputDirectory, String outputFileName, int numFrames) {
        int pixelSize = 32;

        try {
            BufferedImage originalImage = ImageIO.read(new File(inputFilePath));

            int frameWidth = originalImage.getWidth() / numFrames;

            BufferedImage slicedImage = new BufferedImage(originalImage.getWidth(), originalImage.getHeight(), BufferedImage.TYPE_INT_ARGB);

            for (int k = 0; k < numFrames; k++) {
                for (int i = 0; i < originalImage.getHeight() / pixelSize; i++) {
                    for (int j = 0; j < frameWidth / pixelSize; j++) {
                        int x = j * pixelSize + (k * frameWidth);
                        int y = i * pixelSize;

                        BufferedImage subImage = originalImage.getSubimage(x, y, pixelSize, pixelSize);
                        String outputFilePath = outputDirectory + File.separator + outputFileName + "_" + k + ".png";

                        ImageIO.write(subImage, "png", new File(outputFilePath));

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
