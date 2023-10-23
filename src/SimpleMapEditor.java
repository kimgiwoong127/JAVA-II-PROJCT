import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;

public class SimpleMapEditor {
    private static final int TILE_SIZE = 32;
    private static final int PALETTE_SIZE = 42; // 각 팔레트 페이지에 42개의 타일이 있음
    private static final int TOTAL_PAGES = 3; // 총 3개의 팔레트 페이지

    private static BufferedImage[][] palette;
    private static int[][] map;
    private static JButton[][] buttons;
    private static int selectedTile = 0;
    private static int currentPage = 0;
    private static JPanel palettePanel;

    public static void main(String[] args) {
        // Load images into the palette
        palette = new BufferedImage[TOTAL_PAGES][PALETTE_SIZE];
        map = new int[10][10];
        buttons = new JButton[10][10];

        // Load tiles for each palette page
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
        mapPanel.setLayout(new GridLayout(10, 10));
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                buttons[x][y] = new JButton();
                buttons[x][y].setPreferredSize(new Dimension(TILE_SIZE, TILE_SIZE));
                final int buttonX = x;
                final int buttonY = y;
                buttons[x][y].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        placeTile(buttonX, buttonY);
                    }
                });
                mapPanel.add(buttons[x][y]);
            }
        }

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveMap();
            }
        });

        JButton loadButton = new JButton("Load");
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadMap();
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

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);
        buttonPanel.add(prevPageButton);
        buttonPanel.add(nextPageButton);

        frame.add(palettePanel, BorderLayout.WEST);
        frame.add(mapPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.pack();
        frame.setVisible(true);
    }

    private static void selectTile(int tileIndex) {
        selectedTile = tileIndex + currentPage * PALETTE_SIZE;
    }

    private static void placeTile(int x, int y) {
        map[x][y] = selectedTile;
        buttons[x][y].setIcon(new ImageIcon(palette[currentPage][selectedTile % PALETTE_SIZE]));
    }

    private static void saveMap() {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter("map.txt"));
            for (int x = 0; x < 10; x++) {
                for (int y = 0; y < 10; y++) {
                    writer.print(map[x][y] + " ");
                }
                writer.println();
            }
            writer.close();
            System.out.println("Map saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void loadMap() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("map.txt"));
            for (int x = 0; x < 10; x++) {
                String[] values = reader.readLine().trim().split(" ");
                for (int y = 0; y < 10; y++) {
                    map[x][y] = Integer.parseInt(values[y]);
                    buttons[x][y].setIcon(new ImageIcon(palette[currentPage][map[x][y] % PALETTE_SIZE]));
                }
            }
            reader.close();
            System.out.println("Map loaded successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
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
}
