package algorithm;


/**
 * Created by zihua on 16-12-13.
 */
public class DeadLock {
    private int[] available;
    private int[][] max;
    private int[][] allcation;
    private int[][] need;

    /**
     *
     * @param available  开始可用资源向量
     * @param max        开始是 最大需求矩阵 n*m
     */

    public DeadLock(int[] available, int[][] max) {
        this.available = available;
        this.max = max;
        this.need = max;
        allcation = new int[max.length][max[0].length];

    }

    /**
     *
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
     * 查看当前的需求矩阵
     */
    public void showNeed() {
        show(need);
    }

    /**
     * 查看当前的 可用资源向量
     */
    public void showAvailable() {
        int len = available.length;
        for (int i = 0; i < len; i++) {
            System.out.print(available[i] + " ");
        }
    }

    /**
     * 查看当前已经分配的矩阵
     */

    public void showAllcation() {
        show(allcation);
    }

    /**
     * 查看进程最开始的最大需求矩阵
     */

    public void showMax() {
        show(max);
    }

    /**
     *
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
     *
     * @param processId 当前发出请求的进程的 id
     * @param requestVector  当前进程 发出的请求向量
     */
    public void request(int processId, int[] requestVector) {
        if (allot(processId, requestVector)) {
            System.out.println("can allot !");
        } else {

            System.out.println("can't allot !");
        }
    }

    /**
     * 分配资源函数
     * @param processId   进程号
     * @param requestVector 请求向量
     * @return 返回是够分配
     */
    private boolean allot(int processId, int[] requestVector) {
        boolean result = false;
        if (bAE(requestVector, need[processId]) && bAE(requestVector, available)) {
            tryAllot(processId, requestVector);
        }
        return result;

    }

    /**
     *
     * @param a 向量 a
     * @param b 向量 b
     * @return  返回向量  a <= b
     */

    private boolean bAE(int[] a, int[] b) {
        int len = a.length;
        for (int i = 0; i < len; i++) {
            if (a[i] - b[i] > 0) return false;
        }
        return true;
    }

    /**
     * 尝试分配
     * @param id  进程号
     * @param rVector  请求向量
     * @return   是否安全
     */

    private boolean tryAllot(int id, int[] rVector) {
        int[] tAvai = available.clone();
        int[][] tAllca = allcation.clone();
        int[][] tneed = need.clone();
        tAvai = vectorSub(tAvai, rVector);
        tAllca[id] = vectorAdd(tAllca[id], rVector);
        tneed[id] = vectorSub(tneed[id], rVector);
        return safe();

    }

    /**
     *
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
     *
     * @param a
     * @param b
     * @return  a = a+b
     */
    private int[] vectorAdd(int[] a, int[] b) {
        if (a.length != b.length) return a;
        int len = a.length;
        for (int i = 0; i < len; i++) {
            a[i] += b[i];
        }
        return a;
    }

    /**
     *
     * @return 是否安全
     */
    private boolean safe() {
        return true;
    }


}







