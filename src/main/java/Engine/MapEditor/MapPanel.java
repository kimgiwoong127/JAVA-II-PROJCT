package Engine.MapEditor;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class MapPanel extends JPanel {

    private static final int TILE_SIZE = 32; // 타일 크기
    private int[][] map; // 맵 데이터 배열
    private BufferedImage[] tileImages; // 타일 이미지 배열

    public MapPanel() {
        loadMapDataFromFile("src/main/resources/map/forest1"); // 맵 데이터 로드
        loadTileImages(); // 타일 이미지 로드
    }

    private void loadMapDataFromFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int row = 0;

            while ((line = reader.readLine()) != null) {
                String[] values = line.split("\\s+");
                if (map == null) {
                    map = new int[values.length][values.length];
                }

                for (int col = 0; col < values.length; col++) {
                    map[row][col] = Integer.parseInt(values[col]);
                }

                row++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getPageName(int page) {
        if (page == 0) {
            return "forest";
        } else if (page == 1) {
            return "cave";
        } else if (page == 2) {
            return "hell";
        }
        return "";
    }

    private void loadTileImages() {
        // "image/maptile" 디렉터리에서 타일 이미지 로드
        tileImages = new BufferedImage[126];

        for (int i = 0; i < 126; i++) {
            try {
                int page = i / 42;
                int num = i % 42;
                tileImages[i] = ImageIO.read(new File("image/maptile/tile_" + getPageName(page) + (num + 1) + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (map != null && tileImages != null) {
            for (int row = 0; row < map.length; row++) {
                for (int col = 0; col < map[row].length; col++) {
                    int tileValue = map[row][col];

                    if (tileValue >= 0 && tileValue < tileImages.length) {
                        BufferedImage tileImage = tileImages[tileValue];
                        g.drawImage(tileImage, col * TILE_SIZE, row * TILE_SIZE, this);
                    }
                }
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        if (map != null) {
            int width = map.length * TILE_SIZE;
            int height = map[0].length * TILE_SIZE;
            return new Dimension(width, height);
        }
        return super.getPreferredSize();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Map Viewer");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            MapPanel mapPanel = new MapPanel();
            frame.add(mapPanel);
            int width = 688;
            int height = 400;
            frame.setSize(width, height);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
