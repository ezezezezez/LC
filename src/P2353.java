import java.io.*;
import java.lang.*;
import java.util.*;

// 2353. Design a Food Rating System

public class P2353 {
    int n;
    Map<String, Integer> food2rate = new HashMap<>();
    Map<String, String> types = new HashMap<>();
    Map<String, TreeSet<String>> cuisine2rate = new HashMap<>();

    String[] foods, cuisines;
    int[] ratings;

    public P2353(String[] foods, String[] cuisines, int[] ratings) {
        n = foods.length;
        this.foods = foods;
        this.cuisines = cuisines;
        this.ratings = ratings;
        for (int i = 0; i < n; i++) {
            food2rate.put(foods[i], ratings[i]);
            types.put(foods[i], cuisines[i]);
        }
        for (int i = 0; i < n; i++) {
            cuisine2rate.computeIfAbsent(cuisines[i], k -> new TreeSet<>((a, b) -> {
                // System.out.println(a + " " + b);
                if (food2rate.get(a).intValue() != food2rate.get(b).intValue()) {
                    return Integer.compare(food2rate.get(b), food2rate.get(a));
                }
                return a.compareTo(b);
            })).add(foods[i]);
        }
    }

    public void changeRating(String food, int newRating) {
        cuisine2rate.get(types.get(food)).remove(food);
        food2rate.put(food, newRating);
        cuisine2rate.get(types.get(food)).add(food);
    }

    public String highestRated(String cuisine) {
        return cuisine2rate.get(cuisine).first();
    }
}
