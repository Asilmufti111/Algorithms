/* 
CPCS223 Project, Traveling salesman algorithm brute force
*/

public class TS {
    //distances square matrix
    private double[][] distances;
    //number of areas
    public int n;
//------------------------------------------------------------------------------
    //constructor
    public TS(double[][] distances, int n) {
        this.distances = distances;
        this.n = n;
    }
//------------------------------------------------------------------------------
    //calculate the total tour distance of the particular route
    public double calcDistance(int[] route) {
        double tourDistance = 0;
        for (int i = 1; i < route.length; i++) {
            tourDistance += distances[route[i - 1]][route[i]];
        }

        //add the office distance again to complete the tour
        tourDistance += distances[route[n - 1]][route[0]];
        return tourDistance;
    }
//------------------------------------------------------------------------------  
    //convert the route to string from the array
    public String stringRoute(int[] route) {
        String stringRoute = "";
        for (int i = 0; i < route.length; i++) {
            stringRoute+=" "+route[i]; 
        }
        stringRoute+=" "+route[0]; //to return back to office
        return stringRoute;
    }  
}
