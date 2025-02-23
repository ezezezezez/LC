import java.util.List;

// 2849. Determine if a Cell Is Reachable at a Given Time
public class P2849 {
    public boolean isReachableAtTime(int sx, int sy, int fx, int fy, int t) {
        if (sx == fx && sy == fy && t == 1) return false;
        int dist = Math.min(Math.abs(sx - fx), Math.abs(sy - fy));
        int rem = Math.max(Math.abs(sx - fx) - dist, Math.abs(sy - fy) - dist);
        if (dist + rem <= t) return true;
        return false;
    }
}
