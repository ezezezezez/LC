import java.util.*;

// 2671. Frequency Tracker
public class P2671 {
    Map<Integer, Integer> map;
    Map<Integer, Set<Integer>> freq2Num;

//    public FrequencyTracker() {
    public P2671() {
        map = new HashMap<>();
        freq2Num = new HashMap<>();
    }

    public void add(int number) {
        int preFreq = map.getOrDefault(number, 0);
        map.merge(number, 1, Integer::sum);
        int freq = preFreq + 1;
        if (preFreq > 0) {
            freq2Num.get(preFreq).remove(number);
        }
        freq2Num.computeIfAbsent(freq, k -> new HashSet<>()).add(number);
    }

    public void deleteOne(int number) {
        int preFreq = map.getOrDefault(number, 0);
        if (preFreq == 0) return;
        freq2Num.get(preFreq).remove(number);
        if (preFreq - 1 > 0) {
            freq2Num.get(preFreq - 1).add(number);
        }
        map.merge(number, -1, Integer::sum);
    }

    public boolean hasFrequency(int frequency) {
        return freq2Num.get(frequency) != null && !freq2Num.get(frequency).isEmpty();
    }
}
