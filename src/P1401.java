import java.io.*;
import java.lang.*;
import java.util.*;

// 1400. Construct K Palindrome Strings

public class P1401 {
    public boolean checkOverlap(int radius, int xCenter, int yCenter, int x1, int y1, int x2, int y2) {
        if (xCenter >= x1 && xCenter <= x2 && yCenter >= y1 && yCenter <= y2) return true;
        int eLeft = x1 - radius, eRight = x2 + radius, eBottom = y1 - radius, eTop = y2 + radius;
        if (xCenter >= eLeft && xCenter <= eRight && yCenter >= y1 && yCenter <= y2) return true;
        if (xCenter >= x1 && xCenter <= x2 && yCenter >= eBottom && yCenter <= eTop) return true;
        int dx = xCenter - x1, dy = yCenter - y1;
        if (dx * dx + dy * dy <= radius * radius) return true;
        dx = xCenter - x2; dy = yCenter - y2;
        if (dx * dx + dy * dy <= radius * radius) return true;
        dx = xCenter - x1; dy = yCenter - y2;
        if (dx * dx + dy * dy <= radius * radius) return true;
        dx = xCenter - x2; dy = yCenter - y1;
        if (dx * dx + dy * dy <= radius * radius) return true;
        return false;
    }
}
