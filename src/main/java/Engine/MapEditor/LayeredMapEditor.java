package Engine.MapEditor;

import javax.swing.*;
import java.awt.*;

public class LayeredMapEditor extends JFrame {

    public LayeredMapEditor() {
        setTitle("Layered Map Editor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(688, 400));

        MapPanel mapPanel = new MapPanel();
        ObjectPanel objectPanel = new ObjectPanel();

        // Set the bounds for each panel
        mapPanel.setBounds(0, 0, 688, 400);
        objectPanel.setBounds(0, 0, 688, 400);

        // Add panels to the layered pane with different layers
        layeredPane.add(mapPanel, JLayeredPane.DEFAULT_LAYER); // Default layer (bottom)
        layeredPane.add(objectPanel, JLayeredPane.PALETTE_LAYER); // Palette layer (top)

        // Set a layout manager for the layered pane
        layeredPane.setLayout(new BorderLayout());

        // Add the layered pane to the frame
        getContentPane().add(layeredPane);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LayeredMapEditor());
    }
}
