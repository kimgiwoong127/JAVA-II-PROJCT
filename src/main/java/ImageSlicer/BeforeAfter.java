package ImageSlicer;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class BeforeAfter extends JFrame {

    private static final String INPUT_IMAGE_PATH = "image/Player/1 Pink_Monster/Pink_Monster_Walk_6.png";
    private static final String OUTPUT_FOLDER_PATH = "image/Player/1 Pink_Monster/Pink_Monster_Walk_6/";

    private BufferedImage originalImage;
    private ImagePanel beforePanel;
    private ImagePanel afterPanel;

    public BeforeAfter() {
        setTitle("Image Slicer Demo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            originalImage = ImageIO.read(new File(INPUT_IMAGE_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }

        beforePanel = new ImagePanel(originalImage);
        afterPanel = new ImagePanel();

        JPanel mainPanel = new JPanel(new GridLayout(1, 2));
        mainPanel.add(beforePanel);
        mainPanel.add(afterPanel);

        add(mainPanel);
        pack();
        setLocationRelativeTo(null); // 화면 중앙에 창 표시
        setVisible(true);

        // 이미지 슬라이싱
        sliceImage();
    }

    private void sliceImage() {
        int numFrames = 6;
        int frameWidth = originalImage.getWidth() / numFrames;
        int pixelSize = frameWidth / numFrames;
    
        for (int k = 0; k < numFrames; k++) {
            for (int i = 0; i < originalImage.getHeight() / pixelSize; i++) {
                for (int j = 0; j < numFrames; j++) {
                    int x = j * pixelSize + (k * frameWidth);
                    int y = i * pixelSize;
    
                    BufferedImage subImage = originalImage.getSubimage(x, y, pixelSize, pixelSize);
                    String outputFilePath = OUTPUT_FOLDER_PATH + "Pink_Monster_Walk_" + k + ".png";
    
                    try {
                        ImageIO.write(subImage, "png", new File(outputFilePath));
                        afterPanel.addImage(subImage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BeforeAfter());
    }
}

class ImagePanel extends JPanel {

    private BufferedImage image;

    public ImagePanel() {
        setPreferredSize(new Dimension(400, 400));
    }

    public ImagePanel(BufferedImage image) {
        this.image = image;
        setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
    }

    public void addImage(BufferedImage image) {
        this.image = image;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            g.drawImage(image, 0, 0, this);
        }
    }
}
