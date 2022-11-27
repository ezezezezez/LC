import java.util.*;
import java.io.*;
import java.lang.*;

// 1233. Remove Sub-Folders from the Filesystem

public class P1233 {
    List<String> ret = new ArrayList<>();
    List<String> temp = new ArrayList<>();
    public List<String> removeSubfolders(String[] folders) {
        int n = folders.length;
        for (String folder : folders) {
            String[] tokens = folder.split("/");
            Node cur = root;
            for (String token : tokens) {
                if (!cur.children.containsKey(token)) {
                    cur.children.put(token, new Node());
                }
                cur = cur.children.get(token);
            }
            cur.end = true;
        }
        dfs(root);
        return ret;
    }

    void dfs(Node node) {
        if (node.end) {
            ret.add(String.join("/", temp));
            return;
        }
        for (String k : node.children.keySet()) {
            Node nxt = node.children.get(k);
            temp.add(k);
            dfs(nxt);
            temp.remove(temp.size() - 1);
        }
    }

    Node root = new Node();

    static class Node {
        Map<String, Node> children = new HashMap<>();
        boolean end = false;
    }
}
