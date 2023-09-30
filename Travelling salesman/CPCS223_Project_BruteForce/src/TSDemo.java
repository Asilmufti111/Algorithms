/* 
CPCS223 Project, Traveling salesman algorithm brute force
*/

import static java.lang.System.currentTimeMillis;
import java.util.Random;
import java.util.Scanner;

public class TSDemo {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.print("Enter the number of areas: ");
        int n = input.nextInt();
        
        System.out.print("Enter the lower bound of distance (km): ");
        double upperB = input.nextDouble();
        
        System.out.print("Enter the upper bound of distance (km): ");
        double lowerB = input.nextDouble();

        //generate random distances for comparison study
        Random random = new Random();
        double[][] distances = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(i==j)
                    //main diagonal elements are 0
                    distances[i][j]=0;
                else{
                    //generate random double number between 1 to 10
                    distances[i][j]= Math.round((lowerB + random.nextDouble()*(upperB-lowerB))*1000.0)/1000.0;
                    //same number on both triangles
                    distances[j][i]=distances[i][j];
                }                    
            }
        }
        
        //print the randomised distance matrix 
        System.out.println("\nThe randomized distance matrix (km): \n");
        for (int i = 0; i < n; i++) {
            System.out.print("   "+i+"  "); //index of areas
        }
        
        System.out.println("");
        for (int i = 0; i < n; i++) {
            System.out.print(i+" "); //index of areas
            for (int j = 0; j < n; j++) {
                System.out.print(String.format("%.3f",distances[i][j])+" "); //round to 3 decimal places
            }
            System.out.println("\n");
        }
    
        System.out.println("\n##Using brute force algorithm to find shortest tour of the areas## \n");
        System.out.println("All possible routes available with its total distances: ");
        
        //start time here to estimate the efficiency of brute force algorithm
        long startTime = currentTimeMillis(); 
        
        TS delivery = new TS(distances, n); //declare and create TS object
        TSBruteForce bruteForce = new TSBruteForce(delivery); //declare and create TSBruteForce object
        
        bruteForce.run(); //run the method to get the results
        
        long endTime = currentTimeMillis(); 
        long timeTaken = endTime-startTime;
        System.out.println("\nTime taken by the brute force method: "+timeTaken+" ms");
    }
}
