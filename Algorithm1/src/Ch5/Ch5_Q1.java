package Ch5;

import java.util.Scanner;

public class Ch5_Q1 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("please enter the length of the array: ");// ask the user for the length of the array 
        int n = input.nextInt();// take the length from the user 
        int[] A = new int[n];//create the array

        System.out.println("please enter the elements of the array: ");// ask the user for the elements to be in the array 
        for (int i = 0; i < n; ++i) {// loop to fill the array with the elements
            A[i] = input.nextInt();
        }
        //desplay the position of the largest number in the array (I add 1 at the end to show the right position if we start counting with 1)
        System.out.println("the position of the largest element in the array is: " + (maxIndex(A, 0, A.length - 1) + 1));

    }

//method for finding the position of the max elements by using divide-and-conquer algorithm
    public static int maxIndex(int[] A, int l, int r) {
        if (l == r) {// the base case that stop the recarsive call
            return l;
        } else {
            int temp1 = maxIndex(A, l, (int) Math.ceil((l + r) / 2));//change the right pointer 
            int temp2 = maxIndex(A, (int) Math.ceil((l + r) / 2) + 1, r);//change the left pointer 
            //check the larger element and return it's position
            if (A[temp1] >= A[temp2]) {
                return temp1;
            } else {
                return temp2;
            }
        }
    }
}
