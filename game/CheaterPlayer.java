package game;

public class CheaterPlayer implements Player{
    private final int number;
    CheaterPlayer(int number) {
        this.number = number;
    }
    @Override
    public int getPlayerNumber() {
        return this.number;
    }
    @Override
    public Move move(final Position position, final Cell cell, final int k, int n, int m) {
        Board board = (Board) position;
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                final Move move = new Move(r, c, cell);
                if (position.isValid(move)) {
                    board.makeMove(move);
                }
            }
        }
        throw new IllegalStateException("No valid moves");
    }
}
