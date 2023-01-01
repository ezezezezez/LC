import java.io.*;
import java.lang.*;
import java.util.*;

// 2469. Convert the Temperature

public class P2469 {
    public double[] convertTemperature(double celsius) {
        double[] ret = new double[2];
        ret[0] = celsius + 273.15;
        ret[1] = celsius * 1.8 + 32;
        return ret;
    }
}
