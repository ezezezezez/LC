// 3648. Minimum Sensors to Cover Grid

public class P3648 {
    public int minSensors(int n, int m, int k) {
        int a = (m + 2 * k) / (2 * k + 1), b = (n + 2 * k) / (2 * k + 1);
        return a * b;
    }
}
