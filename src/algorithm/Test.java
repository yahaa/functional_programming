package algorithm;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by zihua on 16-12-13.
 */

public class Test{

    public static void main(String[] ags) {
        Random random=new Random();
        int []resouse={10,20,10,5};
        int[][] max=new int[2][4];
        for(int i=0;i<2;i++){
            for(int j=0;j<4;j++){
                max[i][j]=random.nextInt(10);
            }
        }
        DeadLock a=new DeadLock(resouse,max);
        System.out.println("max");
        a.showMax();
        System.out.println("need");
        a.showNeed();
        System.out.println("allcation");
        a.showAllcation();
        System.out.println("available");
        a.showAvailable();

    }

}
