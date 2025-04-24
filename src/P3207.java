import java.util.Arrays;

// 3207. Maximum Points After Enemy Battles
public class P3207 {
    public long maximumPoints(int[] enemyEnergies, int currentEnergy) {
        int n = enemyEnergies.length;
        Arrays.sort(enemyEnergies);
        long cur = currentEnergy;
        if (cur < enemyEnergies[0]) return 0L;
        for (int i = 1; i < n; i++) cur += enemyEnergies[i];
        return cur / enemyEnergies[0];
    }
}
