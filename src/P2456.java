import java.io.*;
import java.lang.*;
import java.util.*;

// 2456. Most Popular Video Creator

public class P2456 {
    public List<List<String>> mostPopularCreator(String[] creators, String[] ids, int[] views) {
        int n = creators.length;
        List<List<String>> ret = new ArrayList<>();
        Map<String, Long> popu = new HashMap<>();
        Map<String, List<IdAndView>> c2iv = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String creator = creators[i], id = ids[i];
            int view = views[i];
            popu.merge(creator, (long)view, Long::sum);
            c2iv.computeIfAbsent(creator, k -> new ArrayList<>()).add(new IdAndView(id, view));
        }
        for (List<IdAndView> list : c2iv.values()) {
            Collections.sort(list, (a, b) -> {
                if (a.view != b.view) return Integer.compare(b.view, a.view);
                return a.id.compareTo(b.id);
            });
        }
        long mx = 0;
        for (String key : popu.keySet()) {
            mx = Math.max(mx, popu.get(key));
        }
        // System.out.println(popu);
        for (String key : popu.keySet()) {
            long cnt = popu.get(key);
            if (cnt == mx) {
                // System.out.println(key);
                List<String> list = new ArrayList<>();
                list.add(key);
                ret.add(list);
            }
        }
        for (int i = 0; i < ret.size(); i++) {
            List<String> list = ret.get(i);
            String creator = list.get(0);
            list.add(c2iv.get(creator).get(0).id);
        }
        return ret;
    }

    static class IdAndView {
        String id;
        int view;
        IdAndView(String id, int view) {
            this.id = id;
            this.view = view;
        }
    }
}
