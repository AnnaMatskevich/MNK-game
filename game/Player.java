package game;

public interface Player {
    Move move(Position position, Cell cell, int k, int n, int m);
    int getPlayerNumber();
}
