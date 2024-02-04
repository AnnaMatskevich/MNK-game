package game;


/*
public class Winner implements Player{
    private Board board;

    private Move bestMove;
    @Override
    public Move move(Position position, Cell cell, int k, int n, int m) {
        board = new mnkBoard(position, n, m, k);
        bestMove = null;
        play(1, cell);
        if (bestMove == null) {
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < m; ++j) {
                    Move move = new Move(i, j, cell);
                    if (position.isValid(move)) {
                        return move;
                    }
                }
            }
        }
        return bestMove;
    }
    private Result play(int depth, Cell cell) {
        int countEmpty = 0;
        int countWin = 0;
        for (int i = 0; i < board.getN(); ++i) {
            for (int j = 0; j < board.getM(); ++j) {
                if (board.getCell(i, j) == Cell.E) {
                    countEmpty++;
                    Move newMove = new Move(i, j, cell); // turn = turn == Cell.X ? Cell.O : Cell.X;
                    Result result = board.makeMove(newMove);
                    if (result == Result.WIN) {
                        if (depth == 1){
                            bestMove = newMove;
                        }
                        board.deleteMove(newMove);
                        return Result.WIN;
                    }
                    if (result == Result.LOSE) {
                        countWin++;
                        board.deleteMove(newMove);
                        continue;
                    }
                    if (result == Result.DRAW) {
                        board.deleteMove(newMove);
                        continue;
                    }

                    result = play(depth + 1, cell == Cell.X ? Cell.O : Cell.X);
                    board.deleteMove(newMove);
                    if (depth == 1) {
                        System.out.println(i);
                        System.out.println(j);
                        System.out.println(result);
                    }
                    if (result == Result.LOSE) {
                        if (depth == 1){
                            bestMove = new Move(i, j, cell);
                        }
                        return Result.WIN;
                    }
                    if (result == Result.WIN) {
                        countWin++;
                    }
                    else {
                        if (depth == 1 && bestMove == null){
                            bestMove = newMove;
                            board.deleteMove(newMove);
                        }
                    }
                }
            }
        }
        if (countWin == countEmpty) {
            return Result.LOSE;
        }
        return Result.UNKNOWN;
    }

}
*/