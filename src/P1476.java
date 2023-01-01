import java.io.*;
import java.lang.*;
import java.util.*;

// 1476. Subrectangle Queries

public class P1476 {
    int m, n;
    int[][] rectangle;

    public P1476(int[][] rectangle) {
        m = rectangle.length; n = rectangle[0].length;
        this.rectangle = rectangle;
    }

    public void updateSubrectangle(int row1, int col1, int row2, int col2, int newValue) {
        for (int i = row1; i <= row2; i++) {
            for (int j = col1; j <= col2; j++) {
                rectangle[i][j] = newValue;
            }
        }
    }

    public int getValue(int row, int col) {
        return rectangle[row][col];
    }
}
