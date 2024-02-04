package game;

public interface Board {
    Position getPosition();
    Cell getCell();
    Cell getCell(int x, int y);
    void deleteMove(Move move);
    boolean draw();

    Result makeMove(Move move);
    int getN();
    int getM();
    int getK();


}
