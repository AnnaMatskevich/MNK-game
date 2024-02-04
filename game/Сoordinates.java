package game;

public class Сoordinates {
    private final int x;
    private final int y;
    Сoordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    int getX() {
        return this.x;
    }
    int getY() {
        return this.y;
    }
    boolean equals(Сoordinates coordinates) {
        if (this.x == coordinates.getX() && this.y == coordinates.getY()) {
            return true;
        }
        return false;
    }

}
