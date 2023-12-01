package Game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class PNG extends JFrame {
    private static final int TILE_SIZE = 32;

    public PNG() {
        String inputFilePath = "src/main/resources/map/forest1.txt";
        String outputImagePath = "src/main/resources/map/forest1.png";

        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
            int rows = 11; // 맵 파일의 행 수에 맞게 수정
            int columns = 21; // 맵 파일의 열 수에 맞게 수정

            BufferedImage mapImage = new BufferedImage(columns * TILE_SIZE, rows * TILE_SIZE, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = mapImage.createGraphics();

            for (int row = 0; row < rows; row++) {
                String[] values = reader.readLine().trim().split(" ");
                for (int col = 0; col < columns; col++) {
                    int tileValue = Integer.parseInt(values[col]);
                    drawTile(g2d, tileValue, col * TILE_SIZE, row * TILE_SIZE);
                }
            }

            g2d.dispose();

            ImageIO.write(mapImage, "png", new File(outputImagePath));
            System.out.println("맵 이미지가 성공적으로 저장되었습니다.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void drawTile(Graphics2D g2d, int tileValue, int x, int y) {
        // 각 타일 값에 따라 다른 색상이나 이미지를 사용하여 타일을 그릴 수 있는 로직을 추가하세요.
        // 이 예제에서는 0과 19의 값에 따라 간단하게 초록색과 회색으로 구분합니다.
        Color tileColor = (tileValue == 0) ? Color.GREEN : Color.GRAY;

        // Alpha 값을 설정하여 투명도를 처리합니다.
        int alpha = (tileValue == -1) ? 0 : 255;
        tileColor = new Color(tileColor.getRed(), tileColor.getGreen(), tileColor.getBlue(), alpha);

        g2d.setColor(tileColor);
        g2d.fillRect(x, y, TILE_SIZE, TILE_SIZE);

        // 타일 값에 따라 다양한 로직을 추가할 수 있습니다.
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PNG play = new PNG();
            play.setTitle("게임 플레이");
            play.setSize(700, 400);
            play.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            play.setLocationRelativeTo(null);
            play.setVisible(true);
        });
    }
}
