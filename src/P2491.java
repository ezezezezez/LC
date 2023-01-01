import java.io.*;
import java.lang.*;
import java.util.*;

// 2491. Divide Players Into Teams of Equal Skill

public class P2491 {
    public long dividePlayers(int[] skill) {
        int n = skill.length;
        Arrays.sort(skill);
        int tot = skill[0] + skill[n - 1];
        long ret = 0;
        for (int i = 0; i < n / 2; i++) {
            int j = n - 1 - i;
            if (skill[i] + skill[j] != tot) return -1;
            ret += 1L * skill[i] * skill[j];
        }
        return ret;
    }
}
