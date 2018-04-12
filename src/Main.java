import java.util.*;

/**
 * Main class to calculate linear regression. This contains the main method that creates an instance of the LinearRegression
 * class, which does the statistical calculations for the regression line. This instance of the LinearRegression class can
 * then be used to find the slope and y-intercept of the regression line, and the r^2 value which measures how well the
 * regression line fits the data.
 *
 * @author Ethan Uzarowski (ethanmuz@gmail.com)
 */
public class Main {
    /**
     * Main method to calculate linear regression
     */
    public static void main(String[]args){
        Scanner scan = new Scanner(System.in);  // Scanner to retrieve data from command line

        System.out.println("For each point in your data, enter it as \"x,y\", and then press enter. " +
                "When you are done entering data, please type \"done\".");

        ArrayList<Double> x = new ArrayList<>();    // ArrayList to hold the x values of the plot
        ArrayList<Double> y = new ArrayList<>();    // ArrayList to hold the y values of the plot
        String entry = scan.nextLine(); // Get the first datapoint from the user
        while (!entry.equals("done") && !entry.equals("Done")){ // Continue accepting datapoints until the user enters "done"
            x.add(Double.parseDouble(entry.replace(" ","").split(",")[0])); // Parse the x value
            y.add(Double.parseDouble(entry.replace(" ","").split(",")[1])); // Parse the y value
            entry = scan.nextLine();    // Accept a new point
        }

        // These are here in case you want to put your data points directly into these arrays
        // x = {};
        // y = {};

        LinearRegression LR = new LinearRegression(x, y);   // Pass the x and y values to LinearRegression class
        System.out.println("y = " + LR.getSlope() + "x + " + LR.getyIntercept());   // Return the regression line y=mx+b
        System.out.println("r^2 = " + LR.getR2());  // Return the r^2 value

        QuadraticRegression QR = new QuadraticRegression(x, y);   // Pass the x and y values to QuadraticRegression class
        System.out.println("y = " + QR.getA() + "x^2 + " + QR.getB() + "x + " + QR.getC());   // Return the regression line y=ax^2+bx+c
        System.out.println("r^2 = " + QR.getR2());  // Return the r^2 value

        LogarithmicRegression LogR = new LogarithmicRegression(x, y);   // Pass the x and y values to QuadraticRegression class
        System.out.println("y = " + LogR.getA() + "lnx + " + LogR.getB());   // Return the regression line y=ax^2+bx+c
        System.out.println("r^2 = " + LogR.getR2());  // Return the r^2 value

        eExponentialRegression eExpR = new eExponentialRegression(x, y);   // Pass the x and y values to QuadraticRegression class
        System.out.println("y = " + eExpR.getA() + "e^(" + eExpR.getB() + "x)");   // Return the regression line y=ax^2+bx+c
        System.out.println("r^2 = " + eExpR.getR2());  // Return the r^2 value

        ExponentialRegression ExpR = new ExponentialRegression(x, y);   // Pass the x and y values to QuadraticRegression class
        System.out.println("y = " + ExpR.getA() + " * " + ExpR.getB() + "^x");   // Return the regression line y=ax^2+bx+c
        System.out.println("r^2 = " + ExpR.getR2());  // Return the r^2 value

        PowerRegression PowR = new PowerRegression(x, y);   // Pass the x and y values to QuadraticRegression class
        System.out.println("y = " + PowR.getA() + "x^" + PowR.getB());   // Return the regression line y=ax^2+bx+c
        System.out.println("r^2 = " + PowR.getR2());  // Return the r^2 value

        InverseRegression InvR = new InverseRegression(x, y);   // Pass the x and y values to QuadraticRegression class
        System.out.println("y = " + InvR.getA() + "/x + " + InvR.getB());   // Return the regression line y=ax^2+bx+c
        System.out.println("r^2 = " + InvR.getR2());  // Return the r^2 value
    }
}
