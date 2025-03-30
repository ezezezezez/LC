// 3494. Find the Minimum Amount of Time to Brew Potions
public class P3494 {
    public long minTime(int[] skill, int[] mana) {
        int n = skill.length, m = mana.length;
        long preTime = 0;
        for (int i = 1; i < m; i++) {
            long curTime = 0, mx = 0;
            for (int j = 0; j < n; j++) {
                preTime += mana[i - 1] * skill[j];
                mx = Math.max(mx, preTime - curTime);
                curTime += mana[i] * skill[j];
            }
            preTime = mx;
        }
        for (int i = 0; i < n; i++) preTime += skill[i] * mana[m - 1];
        return preTime;
    }
}
