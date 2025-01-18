import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class P2545 {
    public int[][] sortTheStudents(int[][] score, int k) {
        Arrays.sort(score, Comparator.comparingInt(a -> -a[k]));
        return score;
    }
}
