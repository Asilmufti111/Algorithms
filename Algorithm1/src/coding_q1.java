/*
Chocolate bar puzzle Given an n-by-m chocolate bar,
you need to break it into nm 1-by-1 pieces.
You can break a bar only in a straight line,
and only one bar can be broken at a time.

Design an algorithm that solves the problem with the minimum number of bar breaks. 
*/
import java.util.Scanner;

public class coding_q1 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter n and m in (n x m) chocolate bar: ");// take the input from the user
        int n = input.nextInt();// take n value
        int m = input.nextInt();// tale m value
        System.out.println("The minimum number of bar breaks is: " + bar_breaks(n, m));// print the min num of bar breaking 
    }

    // method to compute the min num of breaking bars.
    public static int bar_breaks(int n, int m) {
        if (n == 1 && m == 1) {//it is 1 x 1 
            return 0;//stop breaking
        } else if (n == 1 && m > 1) {//one row and many coulmn
            return 1 + bar_breaks(n, (int) Math.floor(m / 2)) + bar_breaks(n, (int) Math.ceil(m / 2)); // devide and conquer 

        } else if (n > 1 && m > 1) {//many row and many coulmn
            return 1 + bar_breaks((int) Math.floor(n / 2), m) + bar_breaks((int) Math.ceil(n / 2), m); // devide and conquer 

        } else {
            return 0;
        }
    }
}
