package algorithm;

/**
 * Created by zihua on 16-12-13.
 */
public class Test {

    public static void change(int[][] a){
        a[0][0]=100;
    }


    public static void show(int[][] a){
        for(int i=0;i<a.length;i++){
            for(int j=0;j<a[0].length;j++){
                System.out.print(a[i][j]+" ");
            }
            System.out.println();
        }
    }

    public static void main(String[]ags){
        int[][]b=new int[2][2];
        for(int i=0;i<2;i++){
            for(int j=0;j<2;j++){
                b[i][j]=i+1;
            }
        }
        show(b);
        int[][]a=(int[][])b.clone();
        show(a);
        change(a);
        show(a);
        show(b);

        int []aa={1,2,3,4};
        int[] bb=aa.clone();
        bb[2]=100;
        for(int i=0;i<4;i++){
            System.out.println(aa[i]);
        }

    }

}
