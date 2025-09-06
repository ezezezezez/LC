import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 3387. Maximize Amount After Two Days of Conversions
public class P3387 {
    public double maxAmount(String initialCurrency, List<List<String>> pairs1, double[] rates1, List<List<String>> pairs2, double[] rates2) {
        Map<String, Map<String, Double>> g1 = buildGraph(pairs1, rates1);
        Map<String, Map<String, Double>> g2 = buildGraph(pairs2, rates2);
        Map<String, Double> day1 = new HashMap<>();
        dfs(g1, initialCurrency, 1.0, null, day1);
        double ret = day1.get(initialCurrency);
        for (String u : day1.keySet()) {
            if (g2.containsKey(u)) {
                Map<String, Double> day2 = new HashMap<>();
                dfs(g2, u, day1.get(u), null, day2);
                ret = Math.max(ret, day2.getOrDefault(initialCurrency, 0.0));
            }
        }
        return ret;
    }

    void dfs(Map<String, Map<String, Double>> g, String u, double value, String pre, Map<String, Double> map) {
        map.merge(u, value, Math::max);
        for (String v : g.get(u).keySet()) {
            if (v.equals(pre)) continue;
            double r = g.get(u).get(v);
            dfs(g, v, value * r, u, map);
        }
    }

    Map<String, Map<String, Double>> buildGraph(List<List<String>> pair, double[] rate) {
        Map<String, Map<String, Double>> map = new HashMap<>();
        for (int i = 0; i < pair.size(); i++) {
            String u = pair.get(i).get(0), v = pair.get(i).get(1);
            double r = rate[i];
            map.computeIfAbsent(u, k -> new HashMap<>()).put(v, r);
            map.computeIfAbsent(v, k -> new HashMap<>()).put(u, 1 / r);
        }
        return map;
    }
}
