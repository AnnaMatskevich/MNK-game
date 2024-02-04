package game;

import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class HumanPlayer implements Player {
    private final PrintStream out;
    private final Scanner in;
    private final int number;


    public HumanPlayer(final PrintStream out, final Scanner in, int number) {
        this.out = out;
        this.in = in;
        this.number = number;
    }

    public HumanPlayer(int number) {
        this(System.out, new Scanner(System.in), number);
    }
    @Override
    public int getPlayerNumber() {
        return this.number;
    }

    @Override
    public Move move(final Position position, final Cell cell, final int k, int n, int m) {

        Move move;
        while (true) {
            String st = in.nextLine();
            Scanner scLine = new Scanner(st);
            try {
                move = new Move(scLine.nextInt(), scLine.nextInt(), cell);
                break;
            } catch (NoSuchElementException e) {
                System.out.println("uncorrect input");
            }
        }
        if (!position.isValid(move)) {
            out.println("Move " + move + " is invalid");
        }
        return move;
    }
}
