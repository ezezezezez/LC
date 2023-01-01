import java.io.*;
import java.lang.*;
import java.util.*;

// 1801. Number of Orders in the Backlog

public class P1801 {
    public int getNumberOfBacklogOrders(int[][] orders) {
        int n = orders.length;
        PriorityQueue<Node> buyPQ = new PriorityQueue<>((a, b) -> Integer.compare(b.price, a.price));
        PriorityQueue<Node> sellPQ = new PriorityQueue<>((a, b) -> Integer.compare(a.price, b.price));
        for (int[] order : orders) {
            int price = order[0], amount = order[1], type = order[2];
            if (type == 0) {
                while (amount > 0 && !sellPQ.isEmpty() && sellPQ.peek().price <= price) {
                    if (sellPQ.peek().amount <= amount) {
                        Node cur = sellPQ.poll();
                        amount -= cur.amount;
                    } else {
                        sellPQ.peek().amount -= amount;
                        amount = 0;
                    }
                }
                if (amount > 0) {
                    buyPQ.offer(new Node(price, amount));
                }
            } else {
                while (amount > 0 && !buyPQ.isEmpty() && buyPQ.peek().price >= price) {
                    if (buyPQ.peek().amount <= amount) {
                        Node cur = buyPQ.poll();
                        amount -= cur.amount;
                    } else {
                        buyPQ.peek().amount -= amount;
                        amount = 0;
                    }
                }
                if (amount > 0) {
                    sellPQ.offer(new Node(price, amount));
                }
            }
            // System.out.println(buyPQ);
            // System.out.println(sellPQ);
            // System.out.println();
        }

        int ret = 0, mod = (int)(1e9 + 7);
        while (!buyPQ.isEmpty()) {
            ret = (ret + buyPQ.poll().amount) % mod;
        }
        while (!sellPQ.isEmpty()) {
            ret = (ret + sellPQ.poll().amount) % mod;
        }
        return ret;
    }

    static class Node {
        int price;
        int amount;
        Node(int price, int amount) {
            this.price = price;
            this.amount = amount;
        }
        public String toString() {
            return "price: " + price + ", amount: " + amount;
        }
    }
}
