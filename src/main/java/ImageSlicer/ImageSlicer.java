package ImageSlicer;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageSlicer {

    public static void main(String[] args) {
        String inputFilePath = "image/Player/1 Pink_Monster/Pink_Monster_Jump_8.png";
        int pixelSize = 32; // 각 부분 이미지의 크기

        try {
            BufferedImage originalImage = ImageIO.read(new File(inputFilePath));

            int numFrames = 8; // 총 프레임 수

            int frameWidth = originalImage.getWidth() / numFrames;

            for (int k = 0; k < numFrames; k++) {
                for (int i = 0; i < originalImage.getHeight() / pixelSize; i++) {
                    for (int j = 0; j < frameWidth / pixelSize; j++) {
                        int x = j * pixelSize + (k * frameWidth);
                        int y = i * pixelSize;

                        BufferedImage subImage = originalImage.getSubimage(x, y, pixelSize, pixelSize);
                        String outputFilePath = "image/Player/1 Pink_Monster/Pink_Monster_Jump_8/Pink_Monster_Jump_" + k + ".png";

                        ImageIO.write(subImage, "png", new File(outputFilePath));
                    }
                }
            }

            System.out.println("이미지 분할이 완료되었습니다.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
