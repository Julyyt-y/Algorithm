/**
 * 欧几里得算法求两个数的最大公约数
 * 数组的基本操作
 */

public class Test1 {
    public static void main(String[] args){
        System.out.println(gcd(3,33));
        System.out.println(gcd(75,35));
        System.out.println(gcd(100,100));
        System.out.println(gcd(100,0));
        System.out.println(gcd(0,5));

        ArrayUseing();

        System.out.println(" \n15的平方根为"+ sqrt(15));

    }

    public static int gcd(int p,int q){
        //交换两个数的数值
        int a;
        if (q > p){
            a = q;
            q = p;
            p = a;
        }

        //欧几里得算法求两个数的最大公约数
        if (q == 0){
            return p;
        }
        //这段代码的意思是p除以q得到的余数为r
        int r = p % q;
        return gcd(q,r);
    }

    public static void ArrayUseing(){

        double[][] a = new double[3][4];
        double[][] a1 ;
        System.out.println(a.length);
        double[] b = new double[]{1,2,3,4,5,6,7,8,9};
        System.out.println(a); //输出的是数组的地址
        for (int i = 0;i < b.length;i ++){
            System.out.print(b[i] + "；"); //输出数组元素
        }
        System.out.println( );


        //颠倒数组元素
        for (int i = 0; i < b.length/2;i ++){
            double temp = b[i];
            b[i] = b[b.length - 1 - i];
            b[b.length -1 - i] = temp;
        }
        System.out.println("\n颠倒后的数组为：");
        for (int i = 0;i < b.length;i ++){
            System.out.print(b[i] + "；"); //输出颠倒后的数组元素
        }
        System.out.println();


        //找出数组中最大元素
        double max = b[0];
        for (int i=0;i<b.length;i++){
            if (b[i] > max){
                max = b[i];
            }
        }
        System.out.println("\n数组b中最大元素为：" + max);


        //计算数组元素的平均值
        int N = b.length;
        double sum = 0.0;
        for (int i=0;i<N;i++){
            sum += b[i];
        }
        double average = sum / N;
        System.out.println("\nb数组元素的平均值为：" + average);


        //复制数组
        double[] bb = new double[N];
        for (int i=0;i<N;i++){
            bb[i] = b[i];
        }
        System.out.println("\nbb数组为：");
        for (int i=0;i<b.length;i++){
            System.out.print(bb[i] + "；");
        }


        //矩阵相乘
        a = new double[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        a1 = new double[][]{{12, 11, 10, 9}, {8, 7, 6, 5}, {4, 3, 2, 1}};
        int NN = a.length;
        double c[][] = new double[NN][NN];
        for (int i=0;i<NN;i++){
            for (int j=0;j<NN;j++){
                for (int k=0;k<NN;k++){
                    c[i][j] += a[i][k] * a1[k][j];
                }
            }
        }
        System.out.print("\n\nc数组为：");
        for (int i=0;i<NN;i++){
            System.out.println();
            for (int j=0;j<NN;j++){
                System.out.print(c[i][j] + "  ");
            }
        }

    }

    //判断一个数是否为素数
    public static boolean isPrime(int N){
        if (N < 2){
            return  false;
        }
        for (int i=2;i*i<=N;i++){
            if (N % i == 0){
                return false;
            }
        }
        return true;
    }


    //计算平方根
    public static double sqrt(double c){
        if (c < 0) {
            return Double.NaN;
        }
        double err = 1e-15; //1乘以10的-15次方
        double t = c;
        //abs()方法是取绝对值
        while (Math.abs(t - c/t) > err * t){
            t = (c/t + t) / 2.0;
        }
        return t;
    }


    //计算调和级数
    public static double H(int N){
        double sum = 0;
        for (int i=0;i<=N;i++){
            sum += 1.0 / i;
        }
        return sum;
    }

}
