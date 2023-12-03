package Game;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import Engine.AnmationWorkFlow.Idle;
import Engine.MapEditor.CombinedPanel;

public class Play extends JFrame {
    public Play() {
        CombinedPanel combinedPanel = new CombinedPanel();
        combinedPanel.setSize(1366, 760);

        // Set the layout manager of combinedPanel to null to manually position components
        combinedPanel.setLayout(null);

        // Create an instance of Idle with the path to your idle animation frames
        Idle idleCharacter = new Idle("image\\Player\\1 Pink_Monster\\Pink_Monster_Idle\\Pink_Monster_idle_");

        // Set the bounds of the idleCharacter within combinedPanel
        idleCharacter.setBounds(100, 100, 80, 80);

        // Add the idleCharacter to combinedPanel
        combinedPanel.add(idleCharacter);

        setTitle("forest1");
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
