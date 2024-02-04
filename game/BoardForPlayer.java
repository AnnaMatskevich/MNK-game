package game;

public class BoardForPlayer implements Position{
    private final Board board;

    public BoardForPlayer (Board board) {
        this.board = board;

    }

    @Override
    public boolean isValid(Move move) {
        return ((Position)board).isValid(move);
    }

    public Cell getCell() {
        return board.getCell();
    }

    @Override
    public Cell getCell(final int r, final int c) {
        return ((Position)board).getCell(r, c);
    }
}
