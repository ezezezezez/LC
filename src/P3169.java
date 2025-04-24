import java.util.HashMap;
import java.util.Map;

// 3169. Count Days Without Meetings
public class P3169 {
    public int countDays(int days, int[][] meetings) {
        int n = meetings.length;
        int ret = days;
        int pres = -1, pree = -2;
        Arrays.sort(meetings, (a, b) -> Integer.compare(a[0], b[0]));
        for (int[] meeting : meetings) {
            int curs = meeting[0], cure = meeting[1];
            if (curs > pree) {
                ret -= pree - pres + 1;
                pres = curs;
                pree = cure;
            } else {
                pree = Math.max(pree, cure);
            }
        }
        ret -= pree - pres + 1;
        return ret;
    }
}
