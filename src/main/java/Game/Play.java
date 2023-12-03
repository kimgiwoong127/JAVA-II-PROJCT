package Game;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import Engine.MapEditor.CombinedPanel;


public class Play extends JFrame {
    public Play() {
        CombinedPanel combinedPanel = new CombinedPanel();
        combinedPanel.setSize(1366, 760);

        setTitle("이어하기");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setContentPane(combinedPanel);

        setSize(688, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Play());
    }
}
