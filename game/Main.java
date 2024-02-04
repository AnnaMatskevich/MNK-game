package game;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();
        final Game game = new Game(true, new HumanPlayer(1), new HumanPlayer(2));
        int result;
        Board board = new mnkBoard(m, n, k);
        result = game.play(board);
        System.out.println("Game result: " + result);

    }
}
