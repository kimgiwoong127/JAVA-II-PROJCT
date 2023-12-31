package Engine.MapEditor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.commons.io.FileUtils;

public class SimpleMapEditor {
    private static final int TILE_SIZE = 32;
    private static final int PALETTE_SIZE = 42;
    private static final int TOTAL_PAGES = 3;
    private static BufferedImage[][] palette;
    private static int[][] map;
    private static JButton[][] buttons;
    private static int selectedTile = 0;
    private static int currentPage = 0;
    private static JPanel palettePanel;
    private static final int ERASER_TILE = -1;

    public static void main(String[] args) {
        palette = new BufferedImage[TOTAL_PAGES][PALETTE_SIZE];
        map = new int[12][21];
        buttons = new JButton[12][21];
        for (int i = 0; i < map.length; i++) {
            Arrays.fill(map[i], -1);
        }

        for (int page = 0; page < TOTAL_PAGES; page++) {
            try {
                for (int i = 0; i < PALETTE_SIZE; i++) {
                    palette[page][i] = ImageIO.read(new File("image/maptile/tile_" + getPageName(page) + (i + 1) + ".png"));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        JFrame frame = new JFrame("Simple Map Editor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        palettePanel = new JPanel(new GridLayout(0, 5));
        updatePalette();

        JPanel mapPanel = new JPanel();
        mapPanel.setLayout(new GridLayout(12, 21));
        for (int y = 0; y < 12; y++) {
            for (int x = 0; x < 21; x++) {
                buttons[y][x] = new JButton();
                buttons[y][x].setPreferredSize(new Dimension(TILE_SIZE, TILE_SIZE));
                final int buttonX = x;
                final int buttonY = y;
                buttons[y][x].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        placeTile(buttonX, buttonY);
                    }
                });
                mapPanel.add(buttons[y][x]);
            }
        }

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showSaveDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    saveMap(selectedFile);
                }
            }
        });

        JButton loadButton = new JButton("Load");
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    loadMap(selectedFile);
                }
            }
        });

        JButton prevPageButton = new JButton("Previous Page");
        prevPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentPage > 0) {
                    currentPage--;
                    updatePalette();
                }
            }
        });

        JButton nextPageButton = new JButton("Next Page");
        nextPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentPage < TOTAL_PAGES - 1) {
                    currentPage++;
                    updatePalette();
                }
            }
        });

        JButton eraseButton = new JButton("Eraser");
        eraseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectEraser();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);
        buttonPanel.add(prevPageButton);
        buttonPanel.add(nextPageButton);
        buttonPanel.add(eraseButton);

        frame.add(palettePanel, BorderLayout.WEST);
        frame.add(mapPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.pack();
        frame.setVisible(true);
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

    private static void updatePalette() {
        palettePanel.removeAll();
        for (int i = 0; i < PALETTE_SIZE; i++) {
            JLabel label = new JLabel(new ImageIcon(palette[currentPage][i]));
            final int tileIndex = i;
            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    selectTile(tileIndex);
                }
            });
            palettePanel.add(label);
        }
        palettePanel.revalidate();
        palettePanel.repaint();
    }

    private static void selectTile(int tileIndex) {
        selectedTile = tileIndex + currentPage * PALETTE_SIZE;
    }

    private static void selectEraser() {
        selectedTile = ERASER_TILE;
    }

    private static void placeTile(int x, int y) {
        if (selectedTile == ERASER_TILE) {
            map[y][x] = -1;
            buttons[y][x].setIcon(null);
        } else {
            map[y][x] = selectedTile;
            buttons[y][x].setIcon(new ImageIcon(palette[currentPage][selectedTile % PALETTE_SIZE]));
        }
    }

    private static void saveMap(File selectedFile) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(selectedFile));
            for (int y = 0; y < 12; y++) {
                for (int x = 0; x < 21; x++) {
                    writer.print(map[y][x] + " ");
                }
                writer.println();
            }
            writer.close();
            System.out.println("Map saved successfully.");

            // 프로젝트 리소스 디렉토리에 선택한 파일을 복사
            File destinationFile = new File("src/main/resources/map/" + selectedFile.getName());
            FileUtils.copyFile(selectedFile, destinationFile);
            System.out.println("Map file copied to resources directory.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void loadMap(File selectedFile) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(selectedFile));
            for (int y = 0; y < 12; y++) {
                String[] values = reader.readLine().trim().split(" ");
                for (int x = 0; x < 21; x++) {
                    map[y][x] = Integer.parseInt(values[x]);
                    if (map[y][x] != -1) {
                        buttons[y][x].setIcon(new ImageIcon(palette[currentPage][map[y][x] % PALETTE_SIZE]));
                    } else {
                        buttons[y][x].setIcon(null);
                    }
                }
            }
            reader.close();
            System.out.println("Map loaded successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
