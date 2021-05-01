package sudoku;

import java.util.HashSet;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Set;

public class Difficulty {

    public SudokuBoard choose(SudokuBoard board, String level) {
        Random rand = new Random();
        int x;
        int y;
        switch (level) {
            case "1": {
                int i = 0;
                while (i < 10) {
                    boolean flaga = true;
                    while (flaga) {
                        x = rand.nextInt(9);
                        y = rand.nextInt(9);
                        if (board.get(x,y) != 0) {
                            board.set(x,y,0);
                            flaga = false;
                        }
                    }
                    i++;
                }
                break;
            }
            case "2": {
                int i = 0;
                while (i < 19) {
                    boolean flaga = true;
                    while (flaga) {
                        x = rand.nextInt(9);
                        y = rand.nextInt(9);
                        if (board.get(x,y) != 0) {
                            board.set(x,y,0);
                            flaga = false;
                        }
                    }
                    i++;
                }
                break;
            }
            case "3": {
                int i = 0;
                while (i < 29) {
                    boolean flaga = true;
                    while (flaga) {
                        x = rand.nextInt(9);
                        y = rand.nextInt(9);
                        if (board.get(x,y) != 0) {
                            board.set(x,y,0);
                            flaga = false;
                        }
                    }
                    i++;
                }
                break;
            }
            default: {
                System.out.println("XD");
            }
        }
        return board;
    }
    }

