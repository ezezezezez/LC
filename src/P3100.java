// 3100. Water Bottles II
public class P3100 {
    public int maxBottlesDrunk(int numBottles, int numExchange) {
        int ret = 0;
        int empty = 0;
        while (numBottles > 0 || empty >= numExchange) {
            if (numBottles > 0) {
                ret += numBottles;
                empty += numBottles;
                numBottles = 0;
                continue;
            }
            numBottles++;
            empty -= numExchange;
            numExchange++;
        }
        return ret;
    }
}
