package game;

public class RoundBoard extends BoardXO {
    private final int r;

    public int getR() {
        return r;
    }

    public RoundBoard( int r, int k) {
        super(r * 2 + 1, r * 2 + 1, k, Cell.N);
        this.r = r;
        emptyCount = 0;

        for (int i = 0; i < 2 * r + 1; ++i) {
            for (int j = 0; j < 2 * r + 1; ++j) {
                if ((i - r) * (i - r) + (j - r) * (j - r) <= (r + 1) * (r + 1)) {
                    emptyCount++;
                    cells[i][j] = Cell.E;
                }
            }
        }
        turn = Cell.X;
    }


}
