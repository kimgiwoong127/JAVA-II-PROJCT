package Main.Anmation;

import javax.swing.JFrame;

public class Launcher {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Character character = new Character();
        frame.add(character);
        frame.setSize(80, 80);
        frame.setVisible(true);
    }
}
