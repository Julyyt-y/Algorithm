/**
 * 欧几里得算法求两个数的最大公约数
 * 数组的使用
 */

public class Test1 {
    public static void main(String[] args){
        System.out.println(gcd(3,33));
        System.out.println(gcd(75,35));
        System.out.println(gcd(100,100));
        System.out.println(gcd(100,0));
        System.out.println(gcd(0,5));

        double[][] a = new double[10][10];
        System.out.println(a.length);
        double[] b = new double[]{1,2,3,4,5,6,7,8,9};
        System.out.println(a); //输出的是数组的地址
        for (int i = 0;i < b.length;i ++){
            System.out.print(b[i] + "。"); //输出数组元素
        }
        System.out.println( );


        //颠倒数组元素
        for (int i = 0; i < b.length/2;i ++){
            double temp = b[i];
            b[i] = b[b.length - 1 - i];
            b[b.length -1 - i] = temp;
        }
        for (int i = 0;i < b.length;i ++){
            System.out.print(b[i] + "；"); //输出颠倒后的数组元素
        }
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
        if (q == 0)
            return p;
        int r = p % q;
        return gcd(q,r);
    }
}
