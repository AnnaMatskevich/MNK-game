package game;

import java.util.Random;
public class RandomPlayer implements Player {
    private final Random random;
    private final int number;

    public RandomPlayer(final Random random, int number) {

        this.random = random;
        this.number = number;
    }

    public RandomPlayer(int number) {
        this(new Random(), number);
    }

    @Override
    public int getPlayerNumber() {
        return this.number;
    }

    @Override
    public Move move(final Position position, final Cell cell, int k, int n, int m) {
        while (true) {
            int r = random.nextInt(n);
            int c = random.nextInt(m);
            final Move move = new Move(r, c, cell);
            if (position.isValid(move)) {
                return move;
            }
        }
    }
}
