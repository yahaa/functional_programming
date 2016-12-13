package algorithm;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by zihua on 16-12-13.
 */

public class Test {


    public static boolean bAE(int[] a, int[] b) {
        int len = a.length;
        for (int i = 0; i < len; i++) {
            if (a[i] > b[i]) return false;
        }
        return true;
    }

    public static void main(String[] ags) {


        int[] resouse = {10, 20, 10, 50};

        int[][] max = new int[2][4];

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                max[i][j] = i + j + 1;
            }
        }

        Computer a = new Computer(resouse, max);
        int[] rp = {1, 2, 1, 2};
        a.request(1, rp);
        a.showAll();
        a.request(0,rp);
        a.showAll();


    }

}
