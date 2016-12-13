package algorithm;


import java.util.Arrays;

/**
 * Created by zihua on 16-12-13.
 */
public class Computer {

    private int totalP;
    private boolean flag=false;
    private int[] available;
    private int[][] max;
    private int[][] allcation;
    private int[][] need;
    private int[] tAvailable;
    private int[][] tAllcation;
    private int[][] tNeed;
    private boolean[] finish;

    /**
     * @param available 开始可用资源向量
     * @param max       开始是 最大需求矩阵 n*m
     */

    public Computer(int[] available, int[][] max) {
        this.available = available;
        this.max = max;
        this.need = cloneMatrix(max);
        allcation = new int[max.length][max[0].length];
        totalP = max.length;
        finish = new boolean[totalP];

    }
    public void showAll(){
        System.out.println("total "+totalP);
        showAvailable();
        showMax();
        showNeed();
        showAllcation();
    }


    /**
     * @param processId     当前发出请求的进程的 id
     * @param requestVector 当前进程 发出的请求向量
     */
    public void request(int processId, int[] requestVector) {
        init();
        boolean ok=false;
        if(bAE(requestVector,available)&&bAE(requestVector,need[processId])){
            System.out.println("ididididididid    "+processId);
            ok=tryAllot(processId,requestVector);
        }
        if(ok){
            allot(processId,requestVector);
        }

    }

    /**
     * 尝试分配
     * @param id      进程号
     * @param rVector 请求向量
     * @return 是否安全
     */
    private boolean tryAllot(int id, int[] rVector) {
        cloneSome();
        tAvailable = vectorSub(tAvailable, rVector);
        tAllcation[id] = vectorAdd(tAllcation[id], rVector);
        tNeed[id] = vectorSub(tNeed[id], rVector);
        flag=false;
        for (int i = 0; i < totalP; i++) {
            if (flag) break;
            safeTest(tAvailable,i, 0);
        }
        return flag;
    }

    /**
     * 真正的分配
     * @param id
     * @param rVector 请求向量
     */
    public void allot(int id,int[] rVector){
        allcation[id]=vectorAdd(allcation[id],rVector);
        need[id]=vectorSub(need[id],rVector);
        available=vectorSub(available,rVector);
    }

    /**
     * @return 是否安全
     */
    private void safeTest(int[] work, int id, int count) {
        if (count >= totalP) {
            flag = true;
            return;
        }
        if(finish[id]==false&&bAE(tNeed[id],work)){
            int[] t = cloneVector(work);
            work = vectorAdd(work, tAllcation[id]);
            finish[id] = true;
            for (int i = 0; i < totalP; i++) {
                safeTest(work, id, count++);
                work = cloneVector(t);
                finish[id] = false;
            }

        }

    }

    /**
     * 初始化
     */
    private void init(){
        tNeed=null;
        tAllcation=null;
        tAvailable=null;
        Arrays.fill(finish,false);
        flag=false;
    }

    /**
     * @param a 向量 a
     * @param b 向量 b
     * @return 返回向量  a <= b
     */
    private boolean bAE(int[] a, int[] b) {
        int len = a.length;
        for (int i = 0; i < len; i++) {
            if (a[i]>b[i]) return false;
        }
        return true;
    }

    /**
     * 查看当前的需求矩阵
     */
    public void showNeed() {
        System.out.println("need");
        show(need);
    }

    /**
     * 查看当前的 可用资源向量
     */
    public void showAvailable() {
        System.out.println("available");
        int len = available.length;
        for (int i = 0; i < len; i++) {
            System.out.print(available[i] + " ");
        }
        System.out.println();
    }

    /**
     * 查看当前已经分配的矩阵
     */
    public void showAllcation() {
        System.out.println("allcation");
        show(allcation);
    }

    /**
     * 查看进程最开始的最大需求矩阵
     */
    public void showMax() {
        System.out.println("max");
        show(max);
    }

    /**
     * @param a 要显示的矩阵
     */
    private void show(int[][] a) {
        int n = a.length;
        int m = a[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * @param a 被减矩阵
     * @param b 减矩阵
     * @return 返回被减矩阵的值
     */
    private int[][] matrixSub(int[][] a, int[][] b) {
        if (a.length != b.length || a[0].length != b[0].length) {
            System.out.println("can't sub");
            return a;
        }
        int n = a.length;
        int m = a[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                a[i][j] -= b[i][j];
            }
        }
        return a;
    }


    /**
     * @param a 要拷贝的矩阵
     * @return 返回拷贝后的矩阵
     */
    private int[][] cloneMatrix(int[][] a) {
        int n = a.length;
        int m = a[0].length;
        int[][] temp = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                temp[i][j] = a[i][j];
            }
        }
        return temp;
    }

    /**
     * @param a 要拷贝的向量
     * @return 返回拷贝后的向量
     */
    private int[] cloneVector(int[] a) {
        int n = a.length;
        int[] temp = new int[n];
        for (int i = 0; i < n; i++) temp[i] = a[i];
        return temp;
    }

    private void cloneSome() {
        tAvailable = cloneVector(available);
        tAllcation = cloneMatrix(allcation);
        tNeed = cloneMatrix(need);
        Arrays.fill(finish, false);
    }

    /**
     * @param a 向量 a
     * @param b 向量 b
     * @return a=a-b
     */
    private int[] vectorSub(int[] a, int[] b) {
        if (a.length != b.length) return a;
        int len = a.length;
        for (int i = 0; i < len; i++) {
            a[i] -= b[i];
        }
        return a;
    }

    /**
     * @param a
     * @param b
     * @return a = a+b
     */
    private int[] vectorAdd(int[] a, int[] b) {
        if (a.length != b.length) return a;
        int len = a.length;
        for (int i = 0; i < len; i++) {
            a[i] += b[i];
        }
        return a;
    }

}







