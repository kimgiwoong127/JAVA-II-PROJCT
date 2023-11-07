package Anmation;

public class Move {
    private Character character;

    public Move(Character character) {
        this.character = character;

    }

    public void moveLeft() {
        int newX = character.getX() - character.getDeltaX();
        if (newX >= 0) {
            character.setX(newX);
            character.repaint();
        }
    }

    public void moveRight() {
        int newX = character.getX() + character.getDeltaX();
        if (newX <= character.getWidth() - character.getCharacterWidth()) {
            character.setX(newX);
            character.repaint();
        }
    }
}
