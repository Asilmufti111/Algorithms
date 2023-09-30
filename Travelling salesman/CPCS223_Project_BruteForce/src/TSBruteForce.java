/* 
CPCS223 Project, Traveling salesman algorithm brute force
*/

import java.util.*;

public class TSBruteForce implements Runnable {
    //data members
    private TS delivery;
    private double minDistance;
    private int countHamil;
    private int countBasicOp;
    private ArrayList<String> listRoutes= new ArrayList<String>(); //store all routes
    private ArrayList<Double> listDistances= new ArrayList<Double>(); //store all distances
//------------------------------------------------------------------------------
    //constructor
    public TSBruteForce(TS delivery) {
        this.delivery = delivery;
    }
//------------------------------------------------------------------------------
    //run the brute force algorithm
    public void run() {
        int[] route = new int[delivery.n]; //route array

        minDistance = Double. MAX_VALUE; 
        countHamil = 0;
        route[0] = 0; //office is zero (starting point) at first position
        
        //go through each area after starting point to get (n-1) permutations of intermediate cities
        //save the area in route array in next position
        for (int i = 1; i < delivery.n; i++) {
            route[1] = i;
            makeRoute(route, 2); //areaAdded is 2 as it is the next position
        }

        //print the information about the tour, distance rounded to 3 decimal places
        System.out.print("\nNumber of Hamiltonian circuits: " + countHamil); 
        
        //find the minimum distance
        for (int i = 0; i < listDistances.size(); i++) {
            if (minDistance < 0 || listDistances.get(i) < minDistance ){
                minDistance = listDistances.get(i);
            }
        }
        
        System.out.println("\n\nDistance of shortest tour: " + String.format("%.3f",minDistance) + " km");
        
        //print the shortest routes that can be taken
        System.out.println("\nThe route(s) that can be taken: ");
        for(int i =0;i< listRoutes.size();i++){
                if(listDistances.get(i)==minDistance){ 
                    System.out.print((i+1)+": ");
                     System.out.print(listRoutes.get(i));
                     System.out.println("  :  "+listDistances.get(i)+" km");
                }               
        }
        
        System.out.println("\nNumber of times the basic operation is executed: "+countBasicOp);
    }
//------------------------------------------------------------------------------
    //recursive method to make the possible routes
    private void makeRoute(int[] route, int areaAdded) {
        //base case
        if (areaAdded == delivery.n) { //saving areas in the route array is completed
            countHamil++;
            System.out.print(countHamil+": ");
            
            listRoutes.add(delivery.stringRoute(route)); //add route
            System.out.print(delivery.stringRoute(route)); //print route
      
            //calculate the total tour distance of the particular route in 3 decimal places
            double distance = Math.round((delivery.calcDistance(route))*1000.0)/1000.0;
            
            System.out.println("  :  "+String.format("%.3f",distance)+" km");
                        
            listDistances.add(distance); //add distance
            return;
        }

        //loop to save the areas in the route array
        loop:
        for (int i = 1; i < delivery.n; i++) {
            for (int j = 0; j < areaAdded; j++) {
                countBasicOp++; //count here because it is the innermost loop
                if (route[j] == i) { 
                    //continue if area is already present in route array
                    continue loop; //go to loop to compare with next area
                }
            }

            route[areaAdded] = i; //save area to route array
            makeRoute(route, areaAdded + 1); //increment offset to save remaining areas
        }
    } 
}
