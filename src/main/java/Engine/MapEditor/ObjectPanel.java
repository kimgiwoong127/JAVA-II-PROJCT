package Engine.MapEditor;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.imageio.ImageIO;

public class ObjectPanel extends JPanel {

    private static final int TILE_SIZE = 32; // 타일 크기
    private BufferedImage[][][] objectImages; // 오브젝트 이미지 배열
    private int[][] objectData; // 오브젝트 데이터 배열

    public ObjectPanel() {
        loadObjectImages(); // 오브젝트 이미지 로드
        loadObjectDataFromFile("src/main/resources/object/forest1_object"); // 오브젝트 데이터 로드
    }

    private static final int PALETTE_SIZE = 50;
    private static final int TOTAL_PAGES = 3;
    private static final String[] FILE_PATHS = {
            "image/forest-2d-tileset/objects",
            "image/cave-2d-tileset/objects",
            "image/hell-2d-tileset/objects"
    };

    private void loadObjectImages() {
        List<BufferedImage> objectImagesList = new ArrayList<>();

        objectImages = new BufferedImage[FILE_PATHS.length][TOTAL_PAGES][PALETTE_SIZE];

        for (int fileIndex = 0; fileIndex < FILE_PATHS.length; fileIndex++) {
            for (int page = 0; page < TOTAL_PAGES; page++) {
                File directory = new File(FILE_PATHS[fileIndex]);
                File[] imageFiles = directory.listFiles((dir, name) -> name.toLowerCase().endsWith(".png"));

                int numTiles = Math.min(PALETTE_SIZE, imageFiles.length);
                for (int i = 0; i < PALETTE_SIZE; i++) {
                    if (i < numTiles) {
                        try {
                            objectImages[fileIndex][page][i] = ImageIO.read(imageFiles[i]);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        // 이미지 파일이 부족한 경우, 빈 BufferedImage 또는 기본 이미지를 설정
                        objectImages[fileIndex][page][i] = new BufferedImage(TILE_SIZE, TILE_SIZE, BufferedImage.TYPE_INT_ARGB);
                    }
                }
            }
        }
    }

    private void loadObjectDataFromFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            List<int[]> objectDataList = new ArrayList<>();
            String line;

            while ((line = reader.readLine()) != null) {
                String[] values = line.trim().split(" ");
                int[] row = Arrays.stream(values).mapToInt(Integer::parseInt).toArray();
                objectDataList.add(row);
            }

            objectData = objectDataList.toArray(new int[0][0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (objectImages != null && objectData != null) {
            // 오브젝트 이미지를 그립니다.
            for (int row = 0; row < objectData.length; row++) {
                for (int col = 0; col < objectData[row].length; col++) {
                    int tileValue = objectData[row][col];

                    // 유효한 타일 값일 때만 이미지를 그립니다.
                    if (tileValue >= 0 && tileValue < PALETTE_SIZE) {
                        int page = tileValue / PALETTE_SIZE;
                        int imageIndex = tileValue % PALETTE_SIZE;
                        g.drawImage(objectImages[0][page][imageIndex], col * TILE_SIZE, row * TILE_SIZE, this);
                    }
                }
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        if (objectData != null) {
            int width = (objectData[0].length) * TILE_SIZE;
            int height = (objectData.length) * TILE_SIZE;
            return new Dimension(width, height);
        }
        return super.getPreferredSize();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Object Viewer");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            ObjectPanel objectPanel = new ObjectPanel();
            frame.add(objectPanel);
            int width = 688;
            int height = 400;
            frame.setSize(width, height);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
