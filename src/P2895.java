import java.util.Collections;
import java.util.List;

// 2895. Minimum Processing Time
public class P2895 {
    public int minProcessingTime(List<Integer> processorTime, List<Integer> tasks) {
        int ret = 0;
        Collections.sort(processorTime);
        tasks.sort((a, b) -> Integer.compare(b, a));
        for (int i = 0; i < processorTime.size(); i++) {
            for (int j = 0; j < 4; j++) {
                ret = Math.max(ret, processorTime.get(i) + tasks.get(4 * i + j));
            }
        }
        return ret;
    }
}
