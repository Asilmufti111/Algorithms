package Ch4_Q10a;
import java.lang.Math;

public class ch4_q10a {

    public static void main(String[] args) {

        int coinpile[] = {1, 1, 0, 1, 1, 1};//this array in a collection of coins,0 is the fake coin 
        System.out.println(findfake3(coinpile, coinpile.length));

    }

    public static int findfake3(int[] coinpile, int n) { //method to find the fake coin 
        if (n == 1) {
            return coinpile[0] ;
        }
        int devide3 = (int) Math.ceil(n / 3);//the size of the first and second subarray
        int devide_last = n - 2 * (int) Math.ceil(n / 3);// the size of the last pile 
        int[] pile1 = new int[devide3];// subarray contain the collection of coins was in the first pile element of coinpile 
        int[] pile2 = new int[devide3];// subarray contain the collection of coins was in the second pile element of coinpile
        int[] pile3 = new int[devide_last];// subarray contain the collection of coins was in the third pile element of coinpile
        
        for (int i = 0; i < devide3; ++i) {//fill the array by the element in the fisrt pile 
            pile1[i] = coinpile[i];
        }
        for (int i = devide3; i < devide3 * 2; ++i) {//fill the array by the element in the second pile 
            pile2[i - devide3] = coinpile[i];
        }
        for (int i = devide3 * 2; i < n; ++i) {//fill the array by the element in the third pile 
            pile3[i - devide3 * 2] = coinpile[i];
        }
        int compareweight = weight(pile1, pile2);//wight the first 2 piles
        if (compareweight == 0) {// the wight is the same
            return findfake3(pile3, devide_last);//continue with the coins of teh third pile
        } else if (compareweight == 1) {// the second pile is lighter 
            return findfake3(pile2, devide3);//continue with the coins of teh second pile
        } else if (compareweight == -1) {// the first pile is lighter 
            return findfake3(pile1, devide3);//continue with the coins of teh first pile
        }
        return n;

    }

    private static int weight(int[] pile1, int[] pile2) {// method to calculate the weight of the coins 
        int sum1 = 0, sum2 = 0;// variables to save the sum of each pile
        for (int i = 0; i < pile1.length; i++) {
            sum1 += pile1[i];

        }
        for (int i = 0; i < pile2.length; i++) {
            sum2 += pile2[i];

        }
        if (sum1 == sum2) {// the weight is the same 
            return 0;

        } else if (sum1 > sum2) {// the fake coin in pile 2
            return 1;
        } else {
            return -1;// the fake coin in poile 1
        }

    }
}
