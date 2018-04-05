import java.util.*;

public class Main {
    public static void main(String[]args){
        Scanner scan = new Scanner(System.in);  // Scanner to retrieve data from command line

        System.out.println("Please enter each datapoint as \"x,y\". When you are done, please type \"done\"");

        ArrayList<Double> x = new ArrayList<>();    // ArrayList to hold the x values of the plot
        ArrayList<Double> y = new ArrayList<>();    // ArrayList to hold the y values of the plot
        String entry = scan.nextLine(); // Get the first datapoint from the user
        while (!entry.equals("done") && !entry.equals("Done")){ // Continue accepting datapoints until the user enters "done"
            x.add(Double.parseDouble(entry.replace(" ","").split(",")[0])); // Parse the x value
            y.add(Double.parseDouble(entry.replace(" ","").split(",")[1])); // Parse the y value
            entry = scan.nextLine();    // Accept a new point
        }

        // x = {};
        // y = {};

        LinearRegression LR = new LinearRegression(x, y);   // Pass the x and y values to LinearRegression class
        System.out.println("y = " + LR.getSlope() + "x + " + LR.getyIntercept());   // Return the regression line y=mx+b
        System.out.println("r^2 = " + LR.getR2());  // Return the r^2 value
    }
}
