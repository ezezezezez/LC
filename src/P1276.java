import java.util.*;
import java.io.*;
import java.lang.*;

// 1276. Number of Burgers with No Waste of Ingredients

public class P1276 {
    public List<Integer> numOfBurgers(int tomatoSlices, int cheeseSlices) {
        List<Integer> ret = new ArrayList<>();
        // 4x + 2y = tomatoSlices, x + y = cheeseSlices
        // 4x + 2y = tomatoSlices, 2x + 2y = 2 * cheeseSlices
        // 2x = tomatoSlices - 2 * cheeseSlices
        int t = tomatoSlices - 2 * cheeseSlices;
        if (t % 2 != 0 || t < 0) return ret;
        int x = t / 2, y = cheeseSlices - x;
        if (x < 0 || y < 0) return ret;
        return List.of(x, y);
    }
}
