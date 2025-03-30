import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// 3492. Maximum Containers on a Ship
public class P3492 {
    public int maxContainers(int n, int w, int maxWeight) {
        return Math.min(n * n, maxWeight / w);
    }
}
