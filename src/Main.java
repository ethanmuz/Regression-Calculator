import java.util.*;

public class Main {
    public static void main(String[]args){
        double[] x;
        double[] y;

        Scanner scan = new Scanner(System.in);

        String[] xvalues = {};
        String[] yvalues = {};

        try {
            xvalues = scan.nextLine().replace(" ", "").split(",");
            yvalues = scan.nextLine().replace(" ", "").split(",");
        } catch(Exception e){
            System.err.println("Usage: Make sure to only write numbers with commas to separate the datapoints!");
            System.exit(1);
        }

        if (xvalues.length!=yvalues.length){
            System.err.println("Usage: Make sure the x and y datapoints have the same number of elements!");
            System.exit(1);
        }

        x = new double[xvalues.length];
        y = new double[xvalues.length];

        for (int i = 0; i < xvalues.length; i++){
            x[i] = Double.parseDouble(xvalues[i]);
            y[i] = Double.parseDouble(yvalues[i]);
        }

        // x = {};
        // y = {};

        LinearRegression LR = new LinearRegression(x, y);
        System.out.println("y = " + LR.getSlope() + "x + " + LR.getyIntercept());
        System.out.println("r^2 = " + LR.getR2());
    }
}
