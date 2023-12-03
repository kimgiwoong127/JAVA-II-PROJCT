package Game;

import javax.swing.JPanel;

import Engine.AnmationWorkFlow.Idle;

public class Player {
    private JPanel playerPanel;

    public Player() {
        // Create an instance of Idle with the path to your idle animation frames
        Idle idleCharacter = new Idle("image\\Player\\1 Pink_Monster\\Pink_Monster_Idle\\Pink_Monster_idle_");

        // Set the bounds of the idleCharacter within playerPanel
        idleCharacter.setBounds(0, 120, 80, 80); // 좌표를 (0, 0)으로 변경

        // Create a JPanel to contain the player components
        playerPanel = new JPanel();
        playerPanel.setSize(80, 80);
        playerPanel.setOpaque(false); // 배경을 투명하게 설정
        playerPanel.setLayout(null);
        playerPanel.add(idleCharacter);
    }

    public JPanel getPlayerPanel() {
        return playerPanel;
    }
}
