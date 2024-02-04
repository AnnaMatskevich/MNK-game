package game;

import java.util.Arrays;
import java.util.Map;
import static java.lang.Math.max;
import static java.lang.Math.min;

abstract class BoardXO implements Board, Position{
    protected static final Map<Cell, Character> SYMBOLS = Map.of(
            Cell.X, 'X',
            Cell.O, 'O',
            Cell.E, '.',
            Cell.N, ' '
    );

    protected final Cell[][] cells;
    protected Cell turn;
    protected final int n;
    protected final int m;
    protected final int k;
    protected int emptyCount;
    @Override
    public boolean draw() {
        return m < k && n < k;
    }

    @Override
    public int getK() {
        return k;
    }
    @Override
    public int getN() {
        return n;
    }

    @Override
    public int getM() {
        return m;
    }

    @Override
    public Position getPosition() {
        return this;
    }

    @Override
    public Cell getCell() {
        return turn;
    }


    public BoardXO(final int n, final int m, int k, Cell cell) {
        this.n = n;
        this.m = m;
        this.k = k;
        this.cells = new Cell[n][m];

        for (Cell[] row : cells) {
            Arrays.fill(row, cell);
        }
        emptyCount = n * m;
        turn = Cell.X;
    }

    public BoardXO(Position position, int m, int n, int k) {
        this.n = n;
        this.m = m;
        this.k = k;
        cells = new Cell[n][m];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                cells[i][j] = position.getCell(i, j);
            }
        }
        emptyCount = n * m;
        turn = Cell.X;
    }

    public void deleteMove(final Move move) {
        cells[move.getRow()][move.getColumn()] = Cell.E;
        emptyCount++;
        turn = turn == Cell.X ? Cell.O : Cell.X;
    }

    private int checkDiag(int posRow, int posCol, int maxPosl, int DposCol) {
        int cur = 0;
        for (int i = 0; i < 2 * k - 1; ++i) {
            posRow++;
            posCol += DposCol;
            if (posRow < 0 || posCol < 0 || posCol >= m || posRow >= n) {
                cur = 0;
                continue;
            }
            cur = countMaxPosl(cells[posRow][posCol], cur);
            maxPosl = max(maxPosl, cur);
        }
        return maxPosl;
    }

    int countMaxPosl(Cell cell, int cur) {
        if (cell == turn) {
            cur++;
        } else {
            cur = 0;
        }
        return cur;
    }

    @Override
    public Result makeMove(final Move move) {
        if (!isValid(move)) {
            return Result.LOSE;
        }
        int row = move.getRow();
        int col = move.getColumn();
        cells[row][col] = move.getValue();

        emptyCount--;
        int cur = 0;
        int maxPosl = -1;
        for (int i = max(0, col - k + 1); i < min(m, col + k); ++i) {
            cur = countMaxPosl(cells[row][i], cur);
            maxPosl = max(maxPosl, cur);
        }
        cur = 0;
        for (int i = max(0, row - k + 1); i < min(n, row + k); ++i) {
            cur = countMaxPosl(cells[i][col], cur);
            maxPosl = max(maxPosl, cur);
        }
        int posCol = col - k;
        int posRow = row - k;
        maxPosl = checkDiag(posRow, posCol, maxPosl, 1);
        posCol = col + k;
        posRow = row - k;
        maxPosl = checkDiag(posRow, posCol, maxPosl, -1);
        if (maxPosl >= k) {
            return Result.WIN;
        }
        int maxNoEnemy = -1;
        for (int i = 0; i < n; ++i) {
            int noEnemy = 0;
            for (int j = 0; j < m; ++j) {
                if (cells[i][j] == turn || cells[i][j] == Cell.E) {
                    noEnemy++;
                } else {
                    noEnemy = 0;
                }
                maxNoEnemy = max(maxNoEnemy, noEnemy);
            }
        }
        for (int j = 0; j < m; ++j) {
            int noEnemy = 0;
            for (int i = 0; i < n; ++i) {
                if (cells[i][j] == turn || cells[i][j] == Cell.E) {
                    noEnemy++;
                } else {
                    noEnemy = 0;
                }
                maxNoEnemy = max(maxNoEnemy, noEnemy);
            }
        }
        for (int i = 0; i < n - k + 1; ++i) {
            int posarow = i;
            int poscol = 0;
            int noEnemy = 0;
            while (posarow < n && poscol < m) {
                if (cells[posarow][poscol] == turn || cells[posarow][poscol] == Cell.E) {
                    noEnemy++;
                } else {
                    noEnemy = 0;
                }
                maxNoEnemy = max(maxNoEnemy, noEnemy);
                posarow++;
                poscol++;
            }
        }
        for (int j = 1; j < m - k + 1; ++j) {
            int posarow = 0;
            int poscol = j;
            int noEnemy = 0;
            while (posarow < n && poscol < m) {
                if (cells[posarow][poscol] == turn || cells[posarow][poscol] == Cell.E) {
                    noEnemy++;
                } else {
                    noEnemy = 0;
                }
                maxNoEnemy = max(maxNoEnemy, noEnemy);
                posarow++;
                poscol++;
            }
        }
        for (int i = k - 1; i < m; ++i) {
            int posarow = 0;
            int poscol = i;
            int noEnemy = 0;
            while (posarow < n && poscol >= 0) {
                if (cells[posarow][poscol] == turn || cells[posarow][poscol] == Cell.E) {
                    noEnemy++;
                } else {
                    noEnemy = 0;
                }
                maxNoEnemy = max(maxNoEnemy, noEnemy);
                posarow++;
                poscol--;
            }
        }
        for (int i = 1; i < m; ++i) {
            int posarow = i;
            int poscol = m - 1;
            int noEnemy = 0;
            while (posarow < n && poscol >= 0) {
                if (cells[posarow][poscol] == turn || cells[posarow][poscol] == Cell.E) {
                    noEnemy++;
                } else {
                    noEnemy = 0;
                }
                maxNoEnemy = max(maxNoEnemy, noEnemy);
                posarow++;
                poscol--;
            }
        }

        if (emptyCount == 0 || maxNoEnemy < k) {
            return Result.DRAW;
        }

        turn = turn == Cell.X ? Cell.O : Cell.X;
        return Result.UNKNOWN;
    }

    @Override
    public boolean isValid(final Move move) {
        return 0 <= move.getRow() && move.getRow() < n
                && 0 <= move.getColumn() && move.getColumn() < m
                && cells[move.getRow()][move.getColumn()] == Cell.E
                && turn == getCell();
    }
    //public boolean isValid(final Move move);


    @Override
    public Cell getCell(final int r, final int c) {
        return cells[r][c];
    }

    private void addStr(StringBuilder sb, String st, int cellWidth) {
        sb.append(" ".repeat(cellWidth - st.length()));
        sb.append(st);
        sb.append("|");
    }

    @Override
    public String toString() {
        //final StringBuilder sb = new StringBuilder("| |");
        int cellWidth = max(String.valueOf(m).length(), String.valueOf(n).length());
        final StringBuilder sb = new StringBuilder("|" + " ".repeat(cellWidth) + "|");
        for (int i = 0; i < m; i++) {
            addStr(sb, String.valueOf(i), cellWidth);
        }
        //StringBuilder rowSep = new StringBuilder(System.lineSeparator() + "_".repeat(sb.length()) +System.lineSeparator());
        for (int i = 0; i < n; ++i) {
            sb.append(System.lineSeparator());

            sb.append("|");
            addStr(sb, String.valueOf(i), cellWidth);
            for (int j = 0; j < m; ++j) {
                addStr(sb, String.valueOf(SYMBOLS.get(cells[i][j])), cellWidth);
            }
        }
        return sb.toString();
    }
}
