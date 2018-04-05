import java.util.ArrayList;

/**
 * This Linear Regression class calculates and stores all the variables needed to calculate the line of best fit for the
 * given dataset. X and Y values are passed into the constructor, which then calculates all values needed for a linear
 * function that is the line of best fit for the given dataset. The slope and y-intercept can then be retrieved from this
 * class to print the regression line in the format y=mx+b. R-squared can also be retrieved, which is the goodness-of-fit
 * index that measures how well the regression represents the data. R-squared is a floating point number in between 0 and
 * 1 inclusive, with a greater number meaning the regression line fits the data better.
 *
 * @author Ethan Uzarowski (ethanmuz@gmail.com)
 */
public class LinearRegression {
    private ArrayList<Double> x;    // ArrayList for x-values of the dataset
    private ArrayList<Double> y;    // ArrayList for Y-values of the dataset

    private Double slope;   // Calculated slope
    private Double yIntercept;  //Calculated y-intercept
    private Double r;   // r - residual analysis, goodness-of-fit index

    private Double xbar;    // Mean of x values
    private Double ybar;    // Mean of y values
    private Double Sx;  // Standard deviation of x values
    private Double Sy;  // Standard deviation of y values

    LinearRegression(ArrayList<Double> x, ArrayList<Double> y){
        this.x = x; this.y = y; // Store x and y values
        findXbar(); findYbar(); findSx(); findSy(); findR();    // Calculate the variables needed to find slope and Y-intercept
        findSlope(); findYintercept();  // Calculate the slope and Y intercept
    }

    /**
     * Calculates x-bar, which is the mean (average) of all the x values
     */
    private void findXbar(){
        Double total = 0.0;
        for (Double i: x)
            total+=i;   // Adds up all x values
        xbar = total/x.size();  // Divides total by number of elements
    }

    /**
     * Calculates y-bar, which is the mean (average) of all the y values
     */
    private void findYbar(){
        Double total = 0.0;
        for (Double i: y)
            total+=i;   // Adds up all y values
        ybar = total/y.size();  // Divides total by number of elements
    }

    /**
     * Calculates Sx, which is the standard deviation of all the x values
     */
    private void findSx(){
        Double total = 0.0;
        for (Double i: x)
            total+=Math.pow(i-xbar,2);  // Adds up the squares of the residuals (difference between each x value and x-bar)
        Sx = Math.sqrt(total/(x.size()-1)); // Calculates the standard deviation by dividing that total by number of x values
    }                                       // minus 1, and then taking the square root of that

    /**
     * Calculates Sy, which is the standard deviation of all the y values
     */
    private void findSy(){
        Double total = 0.0;
        for (Double i: y)
            total+=Math.pow(i-ybar,2);  // Adds up the squares of the residuals (difference between each y value and y-bar)
        Sy = Math.sqrt(total/(y.size()-1)); // Calculates the standard deviation by dividing that total by number of y values
    }                                       // minus 1, and then taking the square root of that

    /**
     * Calculates r, which is the regression line's goodness-of-fit index for this set of data
     */
    private void findR(){
        Double numerator = 0.0;
        for (int i = 0; i < x.size(); i++)
            numerator += (x.get(i)-xbar)*(y.get(i)-ybar);   // Adds all the x-residual times the y-residuals

        Double xMinusXbar2 = 0.0;
        for (Double i: x)
            xMinusXbar2+=Math.pow(i-xbar,2);    // Calculates the total residual-squared values for x

        Double yMinusYbar2 = 0.0;
        for (Double i: y)
            yMinusYbar2+=Math.pow(i-ybar,2);    // Calculates the total residual-squared values for y

        Double denominator = Math.sqrt(xMinusXbar2 * yMinusYbar2);  // Calculates the square root of x residuals-squared
                                                                    // time y residuals-squared
        r = numerator/denominator;  // Divides the x-residuals times the y-residuals, divided by square root of x residuals-squared
    }                               // times y residuals-squared, which gives us r

    /**
     * Calculates the slope of the regression line, which is r times Sy/Sx
     */
    private void findSlope(){
        slope = r * (Sy/Sx);
    }

    /**
     * Calculates the y-intercept of the regression line, which is y-bar minus slope*xbar
     */
    private void findYintercept(){
        yIntercept = ybar - (slope*xbar);
    }

    /**
     * @return slope: The slope of the regression line
     */
    public Double getSlope(){
        return slope;
    }

    /**
     * @return yIntercept: The y-intercept of the regression line
     */
    public Double getyIntercept(){
        return yIntercept;
    }

    /**
     * @return r^2: the goodness-of-fit index for the given regression line
     */
    public Double getR2(){
        return r*r;
    }
}
