import java.io.*;
import java.lang.*;
import java.util.*;

// 2437. Number of Valid Clock Times

public class P2437 {
    public int countTime(String time) {
        int n = time.length();
        int hours = 1, minutes = 1;
        if (time.charAt(0) == '?') {
            if (time.charAt(1) == '?') {
                hours = 24;
            } else if (time.charAt(1) >= '4'){
                hours = 2;
            } else {
                hours = 3;
            }
        } else {
            if (time.charAt(1) == '?') {
                if (time.charAt(0) == '2') {
                    hours = 4;
                } else {
                    hours = 10;
                }
            }
        }
        if (time.charAt(3) == '?') {
            if (time.charAt(4) == '?') {
                minutes = 60;
            } else {
                minutes = 6;
            }
        } else {
            if (time.charAt(4) == '?') {
                minutes = 10;
            }
        }
        return hours * minutes;
    }
}
