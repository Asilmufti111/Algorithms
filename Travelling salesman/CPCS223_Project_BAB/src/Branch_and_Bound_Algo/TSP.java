
import java.util.Arrays;

public class TSP {

    //distances square matrix
    private double[][] distances;
    //number of areas
    public int n;
    int counter = 0;
    int final_path[];
    boolean visited[];

    // Stores the final minimum weight of shortest tour.
    double final_res = Double.MAX_VALUE;
//------------------------------------------------------------------------------
    //constructor

    public TSP(double[][] distances, int n) {
        this.distances = distances;
        this.n = n;
        // final_path[] stores the final solution ie, the
        // path of the salesman.
        final_path = new int[n + 1];

        // visited[] keeps track of the already visited nodes
        // in a particular path
        visited = new boolean[n];

    }
// This function sets up final_path[]

    public void run() {
        int curr_path[] = new int[n + 1];

        // Calculate initial lower bound for the root node
        // using the formula 1/2 * (sum of first min +
        // second min) for all edges.
        // Also initialize the curr_path and visited array
        int curr_bound = 0;
        Arrays.fill(curr_path, -1);
        Arrays.fill(visited, false);

        // Compute initial bound
        for (int i = 0; i < n; i++) {
            curr_bound += (firstMin(distances, i)
                    + secondMin(distances, i));
        }

        // Rounding off the lower bound to an integer
        curr_bound = (curr_bound == 1) ? curr_bound / 2 + 1
                : curr_bound / 2;

        // We start at vertex 1 so the first vertex
        // in curr_path[] is 0
        visited[0] = true;
        curr_path[0] = 0;

        // Call to TSPRec for curr_weight equal to
        // 0 and level 1
        TSPRec(distances, curr_bound, 0, 1, curr_path);

        System.out.printf("\nDistance of shortest tour: %.3f km\n", final_res);
        System.out.printf("\nPath Taken : ");
        for (int i = 0; i <= n; i++) {
            System.out.printf("%d ", final_path[i]);
        }
        System.out.println("");
    }
    // function that takes as arguments:
    // curr_bound -> lower bound of the root node
    // curr_weight-> stores the weight of the path so far
    // level-> current level while moving in the search
    //		 space tree
    // curr_path[] -> where the solution is being stored which
    //			 would later be copied to final_path[]

    void TSPRec(double[][] distances, double curr_bound, double curr_weight,
            int level, int curr_path[]) {

        // base case is when we have reached level n which
        // means we have covered all the nodes once
        if (level == n) {
            counter++;
            System.out.print(counter + ": ");
            for (int i = 0; i < n; i++) {
                System.out.printf("%d ", curr_path[i]);
            }
            System.out.print(0);

            // check if there is an edge from last vertex in
            // path back to the first vertex
            if (distances[curr_path[level - 1]][curr_path[0]] != 0) {
                // curr_res has the total weight of the
                // solution we got
                double curr_res = curr_weight
                        + distances[curr_path[level - 1]][curr_path[0]];
                System.out.println("  :  " + String.format("%.3f", curr_res) + " km");

                // Update final result and final path if
                // current result is better.
                if (curr_res < final_res) {
                    copyToFinal(curr_path);
                    final_res = curr_res;
                }
            }
            return;
        }

        // for any other level iterate for all vertices to
        // build the search space tree recursively
        for (int i = 0; i < n; i++) {
            // Consider next vertex if it is not same (diagonal
            // entry in adjacency matrix and not visited
            // already)
            if (distances[curr_path[level - 1]][i] != 0
                    && visited[i] == false) {
                double temp = curr_bound;
                curr_weight += distances[curr_path[level - 1]][i];

                // different computation of curr_bound for
                // level 2 from the other levels
                if (level == 1) {
                    curr_bound -= ((firstMin(distances, curr_path[level - 1])
                            + firstMin(distances, i)) / 2);
                } else {
                    curr_bound -= ((secondMin(distances, curr_path[level - 1])
                            + firstMin(distances, i)) / 2);
                }

                // curr_bound + curr_weight is the actual lower bound
                // for the node that we have arrived on
                // If current lower bound < final_res, we need to explore
                // the node further
                if (curr_bound + curr_weight < final_res) {
                    curr_path[level] = i;
                    visited[i] = true;

                    // call TSPRec for the next level
                    TSPRec(distances, curr_bound, curr_weight, level + 1,
                            curr_path);
                }

                // Else we have to prune the node by resetting
                // all changes to curr_weight and curr_bound
                curr_weight -= distances[curr_path[level - 1]][i];
                curr_bound = temp;

                // Also reset the visited array
                Arrays.fill(visited, false);
                for (int j = 0; j <= level - 1; j++) {
                    visited[curr_path[j]] = true;
                }
            }
        }
    }

    // Function to find the minimum edge cost
    // having an end at the vertex i
    double firstMin(double distances[][], int i) {
        double min = Integer.MAX_VALUE;
        for (int k = 0; k < n; k++) {
            if (distances[i][k] < min && i != k) {
                min = distances[i][k];
            }
        }
        return min;
    }

    // function to find the second minimum edge cost
    // having an end at the vertex i
    double secondMin(double distances[][], int i) {
        double first = Double.MAX_VALUE, second = Double.MAX_VALUE;
        for (int j = 0; j < n; j++) {
            if (i == j) {
                continue;
            }

            if (distances[i][j] <= first) {
                second = first;
                first = distances[i][j];
            } else if (distances[i][j] <= second
                    && distances[i][j] != first) {
                second = distances[i][j];
            }
        }
        return second;
    }

    // Function to copy temporary solution to
    // the final solution
    void copyToFinal(int curr_path[]) {
        for (int i = 0; i < n; i++) {
            final_path[i] = curr_path[i];
        }
        final_path[n] = curr_path[0];
    }

}
