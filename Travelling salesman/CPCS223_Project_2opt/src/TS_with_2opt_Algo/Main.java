/* 
CPCS223 Project, Traveling salesman problem with 2-opt algorithm 
*/

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Main {

    static Random rand = new Random();

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // Read in the number of areas
        System.out.print("Enter the number of areas: ");
        int areas = input.nextInt();
        ArrayList<Point2D> cities = new ArrayList<>(areas); //array list for saving the Coordinates .
        ArrayList<Point2D> citiesCoordinates = new ArrayList<>(areas); //anothor array list for saving the Coordinates without changing it.
        ArrayList<Point2D> nearestN;//array list for saving the Coordinates tour with nearest Neighbour.
        ArrayList<Point2D> result;// array list for saving the best tour
        // Read in the lower bound and upper bound for the random coordinates
        System.out.print("Enter the lower bound for the random coordinates: ");
        int lBound = input.nextInt();
        System.out.print("Enter the upper bound for the random coordinates: ");
        int uBound = input.nextInt();
        double startTime = System.currentTimeMillis();
        //generate random coordinates for n areas
        System.out.println("\nThe randomized areas coordinates (x,y): ");
        for (int i = 0; i < areas; i++) {
            double x = Math.round(lBound + rand.nextDouble() * (uBound - lBound) * 1000.0) / 1000.0;
            double y = Math.round(lBound + rand.nextDouble() * (uBound - lBound) * 1000.0) / 1000.0;
            //Use Java's built in Point2D type to hold a area
            Point2D area = new Point2D.Double(x, y);
            //Add this area into the arraylist
            cities.add(i, area);
            citiesCoordinates.add(i, area);
        }
        //print the areas coordinates 
        for (int i = 0; i < cities.size(); i++) {
            System.out.println(i + ": (" + cities.get(i).getX() + ", " + cities.get(i).getY() + ")");
        }
        //print the defult tour
        System.out.println("\nInitial tour:");
        for (int i = 0; i < cities.size(); i++) {
            System.out.print(i + " ");
        }
        System.out.print(0);
        //compute the length of the tour and print it 
        double length = Length.routeLength(cities);
        System.out.println("\nInitial tour distance: " + length + " km");
        //compute the time taken to initialize the coordinates
        double time = System.currentTimeMillis() - startTime;
        System.out.println("Time taken to initialize the coordinates: " + time + " ms");
        startTime = System.currentTimeMillis();
        //apply the Nearest Neighbour tour and print the tour's coorditants
        System.out.println("\nThe Nearest Neighbour tour: ");
        nearestN = Neighbour.nearest(cities);
        for (int i = 0; i < nearestN.size(); i++) {
            int x = citiesCoordinates.indexOf(nearestN.get(i));
            System.out.println(x + ": (" + nearestN.get(i).getX() + ", " + nearestN.get(i).getY() + ")");
        }
        System.out.println(0 + ": (" + nearestN.get(0).getX() + ", " + nearestN.get(0).getY() + ")");
        //compute the length of the route and print it 
        length = Length.routeLength(nearestN);
        System.out.println("\nNearest neighbour tour distance: " + length + " km");
        time = System.currentTimeMillis() - startTime;
        System.out.println("Time taken for finding the Nearest Neighbour: " + time + " ms");
        startTime = System.currentTimeMillis();
        // apply 2-opt algorithm to find the best route and print it 
        System.out.println("\nAttempting 2-opt optimisation...");
        result = TwoOpt.alternate(nearestN);
        System.out.println("\nThe best route is: ");
        for (int i = 0; i < result.size(); i++) {
            int x = citiesCoordinates.indexOf(result.get(i));
            System.out.println(x + ": (" + result.get(i).getX() + ", " + result.get(i).getY() + ")");
        }
        System.out.println(0 + ": (" + result.get(0).getX() + ", " + result.get(0).getY() + ")");
        // compute the length of the best route and print it 
        length = Length.routeLength(result);
        System.out.println("\n2-opt solution complete, distance: " + length + " km");
        time = System.currentTimeMillis() - startTime;
        System.out.println("Time taken for 2 opt optimisation: " + time + " ms");

    }
}
