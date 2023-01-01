import java.io.*;
import java.lang.*;
import java.util.*;

// 1797. Design Authentication Manager

public class P1797 {
    int ttl;
    Map<String, Integer> map = new HashMap<>();

    public P1797(int timeToLive) {
        ttl = timeToLive;
    }

    public void generate(String tokenId, int currentTime) {
        map.put(tokenId, currentTime);
    }

    public void renew(String tokenId, int currentTime) {
        if (!map.containsKey(tokenId) || map.get(tokenId) + ttl <= currentTime) return;
        map.put(tokenId, currentTime);
    }

    public int countUnexpiredTokens(int currentTime) {
        // System.out.println(currentTime);
        // System.out.println(map);
        List<String> tokensToRemove = new ArrayList<>();
        for (String tokenId : map.keySet()) {
            if (map.get(tokenId) + ttl <= currentTime) tokensToRemove.add(tokenId);
        }
        for (String token : tokensToRemove) {
            map.remove(token);
        }
        // System.out.println(map);
        // System.out.println();
        return map.size();
    }
}
