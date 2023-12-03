package Engine.AnmationWorkFlow;

import javax.swing.JFrame;

public class Launcher {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Animation WorkFlow");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Character character = new Character();
        frame.add(character);
        frame.setSize(200, 100);
        frame.setVisible(true);
    }
}