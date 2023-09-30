/*
CPCS223 Project, Traveling salesman problem with 2-opt algorithm 
*/

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Length {

    public static double routeLength(ArrayList<Point2D> cities) {

        //Calculate the length of a TSP route held in an ArrayList as a set of Points
        double result = 0; //Holds the route length
        //Set the previous city to the last city in the ArrayList as we need to measure the length of the entire loop
        Point2D prev = cities.get(cities.size() - 1);
        //Go through each city in turn
        for (Point2D city : cities) {

            //get distance from the previous city
            result += city.distance(prev);
            //current city will be the previous city next time
            prev = city;

        }
        return result;
    }

}
