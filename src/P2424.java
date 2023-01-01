import java.io.*;
import java.lang.*;
import java.util.*;

// 2424. Longest Uploaded Prefix

public class P2424 {
    int n;
    TreeSet<Integer> ts = new TreeSet<>();

    public P2424(int n) {
        this.n = n;
        for (int i = 1; i <= n; i++) ts.add(i);
    }

    public void upload(int video) {
        ts.remove(video);
    }

    public int longest() {
        return ts.isEmpty() ? n : ts.first() - 1;
    }
}
