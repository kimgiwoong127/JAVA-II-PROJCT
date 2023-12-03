package Engine.MapEditor;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class CombinedPanel extends JPanel {
    private MapPanel mapPanel;
    private ObjectPanel objectPanel;

    public CombinedPanel() {
        mapPanel = new MapPanel();
        objectPanel = new ObjectPanel();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the contents of MapPanel
        mapPanel.paintComponent(g);

        // Draw the contents of ObjectPanel
        objectPanel.paintComponent(g);
    }

    @Override
    public Dimension getPreferredSize() {
        // Set the preferred size based on the combined size of MapPanel and ObjectPanel
        int maxWidth = Math.max(mapPanel.getPreferredSize().width, objectPanel.getPreferredSize().width);
        int maxHeight = Math.max(mapPanel.getPreferredSize().height, objectPanel.getPreferredSize().height);
        return new Dimension(maxWidth, maxHeight);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Combined Viewer");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            CombinedPanel combinedPanel = new CombinedPanel();
            frame.add(combinedPanel);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
