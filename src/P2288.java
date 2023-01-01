import java.io.*;
import java.lang.*;
import java.util.*;

// 2288. Apply Discount to Prices

public class P2288 {
    public String discountPrices(String sentence, int discount) {
        int n = sentence.length();
        String[] tokens = sentence.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String token : tokens) {
            if (token.charAt(0) != '$' || token.length() <= 1) {
                sb.append(token).append(' ');
                continue;
            }
            boolean allDigit = true;
            for (int i = 1; i < token.length(); i++) {
                if (!Character.isDigit(token.charAt(i))) {
                    allDigit = false;
                    break;
                }
            }
            if (!allDigit) {
                sb.append(token).append(' ');
                continue;
            }
            double val = Double.parseDouble(token.substring(1));
            // System.out.println(token + " " + val);
            double dV = val * (100 - discount) / 100;
            sb.append('$').append(String.format("%.2f", dV)).append(' ');
        }
        return sb.substring(0, sb.length() - 1).toString();
    }
}
