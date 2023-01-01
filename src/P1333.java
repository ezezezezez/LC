import java.util.*;
import java.io.*;
import java.lang.*;
import java.util.stream.Collectors;

// 1333. Filter Restaurants by Vegan-Friendly, Price and Distance

public class P1333 {
    public List<Integer> filterRestaurants(int[][] restaurants, int veganFriendly, int maxPrice, int maxDistance) {
        List<Integer> ret = new ArrayList<>();
        List<Restaurant> list = new ArrayList<>();
        for (int[] restaurant : restaurants) {
            list.add(new Restaurant(restaurant[0], restaurant[1], restaurant[2], restaurant[3], restaurant[4]));
        }
        list = list.stream().filter(res -> {
            if (veganFriendly == 1 && res.veganFriendly == 0) return false;
            if (maxPrice < res.price) return false;
            if (maxDistance < res.distance) return false;
            return true;
        }).collect(Collectors.toList());

        Collections.sort(list, (a, b) -> {
            if (a.rating != b.rating) return Integer.compare(b.rating, a.rating);
            return Integer.compare(b.id, a.id);
        });

        for (Restaurant restaurant : list) {
            ret.add(restaurant.id);
        }
        return ret;
    }

    static class Restaurant {
        int id;
        int rating;
        int veganFriendly;
        int price;
        int distance;

        Restaurant(int id, int rating, int veganFriendly, int price, int distance) {
            this.id = id;
            this.rating = rating;
            this.veganFriendly = veganFriendly;
            this.price = price;
            this.distance = distance;
        }
    }
}
