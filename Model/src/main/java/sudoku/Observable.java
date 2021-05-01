package sudoku;

public interface Observable {
    void attach(sudoku.Observer observer);

    void dettach(sudoku.Observer observer);

    void notifyObserver();
}
