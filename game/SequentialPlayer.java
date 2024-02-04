package game;
public class SequentialPlayer implements Player {
    private final int number;

    public SequentialPlayer(int number) {
        this.number = number;
    }
    @Override
    public int getPlayerNumber(){
        return this.number;
    }

    @Override
    public Move move(final Position position, final Cell cell, final int k, int n, int m) {
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                final Move move = new Move(r, c, cell);
                if (position.isValid(move)) {
                    return move;
                }
            }
        }
        throw new IllegalStateException("No valid moves");
    }
}
