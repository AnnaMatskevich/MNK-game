package game;

public class Game {
    private final boolean log;
    private final Player player1, player2;

    public Game(final boolean log, final Player player1, final Player player2) {
        this.log = log;
        this.player1 = player1;
        this.player2 = player2;
    }

    public int play(Board board) {
        log(board.toString());
        BoardForPlayer boardForPlayer = new BoardForPlayer(board);
        if (board.draw()) {
            log("Draw");
            return 0;
        }
        while (true) {
            log("Player " + 1 + " move X");
            final int result1 = move(boardForPlayer, board, player1, 1);
            if (result1 != -1) {
                return result1;
            }
            log("Player " + 2 + " move 0");
            final int result2 = move(boardForPlayer, board, player2, 2);
            if (result2 != -1) {
                return result2;
            }
        }
    }

    private int move(final BoardForPlayer boardForPlayer, final Board board, final Player player, final int no) {
        Move move;
        try {
            move = player.move(boardForPlayer, board.getCell(), board.getK(), board.getN(), board.getM());
        }
        catch (Exception e){
            System.out.println("player is broken, he lose");
            return 3 - no;
        }
        Result result = board.makeMove(move);
        log("Player " + no + " move: " + move);
        log("Position:" + System.lineSeparator() + board);
        if (result == Result.WIN) {
            log("Player " + no + " won");
            return no;
        } else if (result == Result.LOSE) {
            log("Player " + no + " lose");
            return 3 - no;
        } else if (result == Result.DRAW) {
            log("Draw");
            return 0;
        } else {
            return -1;
        }
    }

    private void log(final String message) {
        if (log) {
            System.out.println(message);
        }
    }
}
