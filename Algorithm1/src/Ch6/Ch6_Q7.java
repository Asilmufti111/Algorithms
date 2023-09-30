
import java.util.*;

public class Ch6_Q7 {

    public static void main(String args[]) {
        int A[] = {5, 9, 1, 3};// the array to check
        int s = 6;//the sum wants to find
        int n = A.length;// the length of the array
        Arrays.sort(A);// sort the elements in the array by the Quicksort algorithm
        if (ArrayHasSum(A, n, s))// calling the method
        {
            System.out.println("Yes");//the array contains  2 elements whose sum is equal to (s)
        } else {
            System.out.println("No");//the array is not contains  2 elements whose sum is equal to (s)
        }
    }

    // method to check if the array contains  2 elements whose sum is equal to (s)
    public static boolean ArrayHasSum(int A[], int n, int s) {
        // make 2 pointer from the right and left (start and end of the array )
        int l = 0;
        int r = n - 1;
        while (l < r) {//check that the left pointer is less than the right one 
            if (A[l] + A[r] == s)//the sum of the pointing elements is equle s
            {
                return true;// then return true that the sum of 2 elements =s in exist 
            } else if (A[l] + A[r] < s)//the sum of the pointing elements is less than s
            {
                l++;//incrace the left pointer
            } else // A[i] + A[j] > s ==>(the sum of the pointing elements is greater than s)
            {
                r--;//decrace the right pointer
            }
        }
        // by the end of the loop if the method doesn't return true then the sum of 2 elements =s doesn't exist==> so return  
        return false;
    }

}
