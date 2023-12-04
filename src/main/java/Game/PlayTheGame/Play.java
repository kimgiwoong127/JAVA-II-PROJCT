package Game.PlayTheGame;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import Engine.MapEditor.CombinedPanel;

public class Play extends JFrame {
    public Play(String Name) {
        CombinedPanel combinedPanel = new CombinedPanel();
        combinedPanel.setSize(688, 400);

        // 레이아웃 관리자를 사용하지 않고 수동으로 컴포넌트를 배치하려는 것은 좋지 않습니다.
        combinedPanel.setLayout(null);

        // Create an instance of Player class
        Player player = new Player(Name);

        // Add the player panel to combinedPanel
        combinedPanel.add(player.getPlayerPanel());

        setTitle("forest1");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setContentPane(combinedPanel);

        // 크기를 화면에 맞게 설정
        pack();
        setSize(688, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Play("NULL"));
    }
}