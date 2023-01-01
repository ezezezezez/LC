import java.io.*;
import java.lang.*;
import java.util.*;

// 1904. The Number of Full Rounds You Have Played

public class P1904 {
    public int numberOfRounds(String loginTime, String logoutTime) {
        String[] sTokens = loginTime.split(":"), eTokens = logoutTime.split(":");
        int sTime = Integer.parseInt(sTokens[1]) + 60 * Integer.parseInt(sTokens[0]);
        int eTime = Integer.parseInt(eTokens[1]) + 60 * Integer.parseInt(eTokens[0]);
        int ost = sTime, oet = eTime;
        sTime = (sTime + 14) / 15 * 15;
        eTime = eTime / 15 * 15;
        if (oet < ost) {
            eTime += 24 * 60;
        }
        int ret = 0;
        while (sTime < eTime) {
            ret++;
            sTime += 15;
        }
        return ret;
    }
}
