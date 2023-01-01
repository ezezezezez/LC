import java.io.*;
import java.lang.*;
import java.util.*;

// 2424. Longest Uploaded Prefix

public class P2424_2 {
    boolean[] vis;
    int idx = 1;

    public P2424_2(int n) {
        vis = new boolean[n + 1];
    }

    public void upload(int video) {
        vis[video] = true;
    }

    public int longest() {
        while (idx < vis.length && vis[idx]) idx++;
        return idx - 1;
    }
}
