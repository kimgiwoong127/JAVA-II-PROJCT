package Game;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Play extends JFrame {
    public Play() {
        JPanel emptyPanel = new JPanel();
        emptyPanel.setSize(1366, 768);

        JButton sampleButton = new JButton("클릭하세요");
        emptyPanel.add(sampleButton);

        setTitle("이어하기");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setContentPane(emptyPanel);
        
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Play());
    }
}
