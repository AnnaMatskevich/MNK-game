package game;

public class mnkBoard extends BoardXO {

    public mnkBoard(final int n, final int m, int k) {
        super(m, n, k, Cell.E);
    }

    public mnkBoard(Position position, int n, int m, int k) {
        super(position, m, n, k);
    }

}
